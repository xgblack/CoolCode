package com.xgblack.cool.framework.security.core.authentication.support.core;

import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.cool.framework.common.utils.web.WebUtils;
import com.xgblack.cool.framework.security.core.service.CoolUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.extra.spring.SpringUtil;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 授权方式提供者
 * <p>
 *     判断授权有效性，用户有效性，在判断用户是否有效性，它依赖于UserDetailsService实例
 * </p>
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
public class CoolDaoUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    /**
     * The plaintext password used to perform PasswordEncoder#matches(CharSequence, String)} on when the user is not found to avoid SEC-2056.
     */
    private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

    private final static BasicAuthenticationConverter basicConvert = new BasicAuthenticationConverter();

    private PasswordEncoder passwordEncoder;

    /**
     * The password used to perform {@link PasswordEncoder#matches(CharSequence, String)}
     * on when the user is not found to avoid SEC-2056. This is necessary, because some
     * {@link PasswordEncoder} implementations will short circuit if the password is not
     * in a valid format.
     */
    private volatile String userNotFoundEncodedPassword;

    @Setter
    private UserDetailsService userDetailsService;

    @Setter
    private UserDetailsPasswordService userDetailsPasswordService;

    public CoolDaoUserDetailsAuthenticationProvider() {
        setMessageSource(SpringUtil.getBean("securityMessageSource"));
        setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        // app 模式不用校验密码
        String grantType = WebUtils.getRequest().get().getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (StrUtil.equals(SecurityConstants.MOBILE, grantType)) {
            return;
        }

        if (authentication.getCredentials() == null) {
            log.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        String presentedPassword = authentication.getCredentials().toString();

        // 解密密码 TODO:暂不解密
        //presentedPassword = decode(presentedPassword);

        if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            log.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }

    @SneakyThrows
    @Override
    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) {
        prepareTimingAttackProtection();
        HttpServletRequest request = WebUtils.getRequest().orElseThrow((Supplier<Throwable>) () -> new InternalAuthenticationServiceException("web request is empty"));

        String grantType = WebUtils.getRequest().get().getParameter(OAuth2ParameterNames.GRANT_TYPE);
        String clientId = WebUtils.getRequest().get().getParameter(OAuth2ParameterNames.CLIENT_ID);

        if (StrUtil.isBlank(clientId)) {
            clientId = basicConvert.convert(request).getName();
        }

        Map<String, CoolUserDetailsService> userDetailsServiceMap = SpringUtil.getBeansOfType(CoolUserDetailsService.class);

        String finalClientId = clientId;
        Optional<CoolUserDetailsService> optional = userDetailsServiceMap.values()
                .stream()
                .filter(service -> service.support(finalClientId, grantType))
                .max(Comparator.comparingInt(Ordered::getOrder));

        if (optional.isEmpty()) {
            throw new InternalAuthenticationServiceException("UserDetailsService error , not register");
        }

        try {
            UserDetails loadedUser = optional.get().loadUserByUsername(username);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
            }
            return loadedUser;
        } catch (UsernameNotFoundException ex) {
            mitigateAgainstTimingAttack(authentication);
            throw ex;
        } catch (InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        boolean upgradeEncoding = this.userDetailsPasswordService != null && this.passwordEncoder.upgradeEncoding(user.getPassword());
        if (upgradeEncoding) {
            String presentedPassword = authentication.getCredentials().toString();
            String newPassword = this.passwordEncoder.encode(presentedPassword);
            user = this.userDetailsPasswordService.updatePassword(user, newPassword);
        }
        return super.createSuccessAuthentication(principal, authentication, user);
    }

    private void prepareTimingAttackProtection() {
        if (this.userNotFoundEncodedPassword == null) {
            this.userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
        }
    }

    private void mitigateAgainstTimingAttack(UsernamePasswordAuthenticationToken authentication) {
        if (authentication.getCredentials() != null) {
            String presentedPassword = authentication.getCredentials().toString();
            this.passwordEncoder.matches(presentedPassword, this.userNotFoundEncodedPassword);
        }
    }

    /**
     * 设置用于编码和验证密码的PasswordEncoder实例。如果未设置，密码将使用 {@link PasswordEncoderFactories#createDelegatingPasswordEncoder()}
     * <p>DelegatingPasswordEncoder能够根据密码前缀来确定密码编码器</p>
     * @param passwordEncoder must be an instance of one of the {@code PasswordEncoder} types.
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
        this.userNotFoundEncodedPassword = null;
    }

    protected PasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }


    protected UserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }



}
