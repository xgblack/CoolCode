package com.xgblack.cool.framework.security.core.component;

import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.cool.framework.security.config.MockSecurityProperties;
import com.xgblack.cool.framework.security.core.service.CoolUserDetailsService;
import com.xgblack.cool.framework.security.dto.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.convert.Convert;
import org.dromara.hutool.core.math.NumberUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.extra.spring.SpringUtil;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

import java.security.Principal;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 资源服务器toke内省处理器
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@RequiredArgsConstructor
public class CoolOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private final OAuth2AuthorizationService authorizationService;

    private final MockSecurityProperties mockSecurityProperties;

    private final CoolUserDetailsService userDetailsService;

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        OAuth2Authorization oldAuthorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);

        if (Objects.isNull(oldAuthorization)) {
            if (mockSecurityProperties.getMockEnable() && StrUtil.isNotBlank(token) && token.startsWith(mockSecurityProperties.getMockSecret())) {
                // 判断 为模拟登录
                // 构建模拟用户
                String userIdStr = StrUtil.subAfter(token, mockSecurityProperties.getMockSecret(), true).trim();
                if (StrUtil.isBlank(userIdStr) || !NumberUtil.isNumber(userIdStr)) {
                    log.error("mock模式token错误，请使用固定token+数字(用户id)");
                    throw new InvalidBearerTokenException(token);
                }
                Long userId = Convert.toLong(userIdStr);
                try {
                    UserDetails userDetails = userDetailsService.loadUserById(userId);
                    if (Objects.isNull(userDetails)) {
                        log.error("mock模式token错误，加载用户失败，用户id={}", userId);
                        throw new InvalidBearerTokenException(token);
                    }
                    return (LoginUser) userDetails;
                } catch (Exception e) {
                    log.error("mock模式token错误，加载用户失败，用户id={}", userId);
                    throw new InvalidBearerTokenException(token);
                }
            }
            throw new InvalidBearerTokenException(token);
        }

        // 客户端模式默认返回
        if (AuthorizationGrantType.CLIENT_CREDENTIALS.equals(oldAuthorization.getAuthorizationGrantType())) {
            return new DefaultOAuth2AuthenticatedPrincipal(oldAuthorization.getPrincipalName(),
                    Objects.requireNonNull(oldAuthorization.getAccessToken().getClaims()),
                    AuthorityUtils.NO_AUTHORITIES);
        }

        Map<String, CoolUserDetailsService> userDetailsServiceMap = SpringUtil
                .getBeansOfType(CoolUserDetailsService.class);

        Optional<CoolUserDetailsService> optional = userDetailsServiceMap.values()
                .stream()
                .filter(service -> service.support(Objects.requireNonNull(oldAuthorization).getRegisteredClientId(),
                        oldAuthorization.getAuthorizationGrantType().getValue()))
                .max(Comparator.comparingInt(Ordered::getOrder));

        UserDetails userDetails = null;
        try {
            Object principal = Objects.requireNonNull(oldAuthorization).getAttributes().get(Principal.class.getName());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
            Object tokenPrincipal = usernamePasswordAuthenticationToken.getPrincipal();
            userDetails = optional.get().loadUserByUser((LoginUser) tokenPrincipal);
        }
        catch (UsernameNotFoundException notFoundException) {
            log.warn("用户不不存在 {}", notFoundException.getLocalizedMessage());
            throw notFoundException;
        }
        catch (Exception ex) {
            log.error("资源服务器 introspect Token error {}", ex.getLocalizedMessage());
        }

        // 注入客户端信息，方便上下文中获取
        LoginUser loginUser = (LoginUser) userDetails;
        Objects.requireNonNull(loginUser)
                .getAttributes()
                .put(SecurityConstants.CLIENT_ID, oldAuthorization.getRegisteredClientId());
        return loginUser;
    }

}
