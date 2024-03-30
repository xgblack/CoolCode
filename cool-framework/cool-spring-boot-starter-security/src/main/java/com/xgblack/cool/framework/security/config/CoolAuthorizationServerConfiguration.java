package com.xgblack.cool.framework.security.config;

import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.cool.framework.security.core.authentication.support.core.CoolDaoUserDetailsAuthenticationProvider;
import com.xgblack.cool.framework.security.core.authentication.support.core.CoolOAuth2AccessTokenGenerator;
import com.xgblack.cool.framework.security.core.authentication.support.core.CoolOAuth2TokenCustomizer;
import com.xgblack.cool.framework.security.core.authentication.support.handler.*;
import com.xgblack.cool.framework.security.core.authentication.support.mobile.MobileGrantAuthenticationConverter;
import com.xgblack.cool.framework.security.core.authentication.support.mobile.MobileGrantAuthenticationProvider;
import com.xgblack.cool.framework.security.core.authentication.support.password.PasswordGrantAuthenticationConverter;
import com.xgblack.cool.framework.security.core.authentication.support.password.PasswordGrantAuthenticationProvider;
import com.xgblack.cool.framework.security.core.component.CoolBearerTokenExtractor;
import com.xgblack.cool.framework.security.core.component.ResourceAuthExceptionEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2RefreshTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

/**
 * 认证服务器配置
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CoolAuthorizationServerConfiguration {

    private final OAuth2AuthorizationService authorizationService;

    protected final ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

    private final CoolBearerTokenExtractor coolBearerTokenExtractor;

    private final OpaqueTokenIntrospector opaqueTokenIntrospector;

    private final PermitAllUrlProperties permitAllUrl;

    private final CoolLoginPreFilter coolLoginPreFilter;


    /**
     * Spring Authorization Server 相关配置
     * 此处方法与下面defaultSecurityFilterChain都是SecurityFilterChain配置，配置的内容有点区别，
     * 因为Spring Authorization Server是建立在Spring Security 基础上的，defaultSecurityFilterChain方法主要
     * 配置Spring Security相关的东西，而此处authorizationServerSecurityFilterChain方法主要配置OAuth 2.1和OpenID Connect 1.0相关的东西
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http, CoolAuthenticationSuccessEventHandler successEventHandler, CoolAuthenticationFailureEventHandler failureEventHandler) throws Exception {

        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();

        http.addFilterAfter(coolLoginPreFilter, UsernamePasswordAuthenticationFilter.class);
        http.with(authorizationServerConfigurer.tokenEndpoint((tokenEndpoint) -> {
                            // 个性化认证授权端点
                            tokenEndpoint.accessTokenRequestConverter(accessTokenRequestConverter()) // 注入自定义的授权认证Converter
                                    .accessTokenResponseHandler(successEventHandler) // 登录成功处理器
                                    .errorResponseHandler(failureEventHandler);// 登录失败处理器
                        })
                        .clientAuthentication(oAuth2ClientAuthenticationConfigurer -> // 个性化客户端认证
                                oAuth2ClientAuthenticationConfigurer.errorResponseHandler(failureEventHandler))// 处理客户端认证异常
                        .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint// 授权码端点个性化confirm页面
                                .consentPage(SecurityConstants.CUSTOM_CONSENT_PAGE_URI)), Customizer.withDefaults());

        AntPathRequestMatcher[] requestMatchers = permitAllUrl.getUrls()
                .stream()
                .map(AntPathRequestMatcher::new)
                .toList()
                .toArray(new AntPathRequestMatcher[]{});

        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers(requestMatchers)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(
                        oauth2 -> oauth2.opaqueToken(token -> token.introspector(opaqueTokenIntrospector))
                                .authenticationEntryPoint(resourceAuthExceptionEntryPoint)
                                .bearerTokenResolver(coolBearerTokenExtractor))
                .exceptionHandling(configurer -> configurer.authenticationEntryPoint(resourceAuthExceptionEntryPoint))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable);

        http.with(authorizationServerConfigurer.authorizationService(authorizationService)// redis存储token的实现
                        .authorizationServerSettings(AuthorizationServerSettings.builder().build()),
                Customizer.withDefaults());
        DefaultSecurityFilterChain securityFilterChain = http.build();

        // 注入自定义授权模式实现
        addCustomOAuth2GrantAuthenticationProvider(http);

        return securityFilterChain;

    }


    /**
     * 令牌生成规则实现 </br>
     * client:username:uuid
     *
     * @return OAuth2TokenGenerator
     */
    @Bean
    public OAuth2TokenGenerator oAuth2TokenGenerator() {
        CoolOAuth2AccessTokenGenerator accessTokenGenerator = new CoolOAuth2AccessTokenGenerator();
        // 注入Token 增加关联用户信息
        accessTokenGenerator.setAccessTokenCustomizer(new CoolOAuth2TokenCustomizer());
        return new DelegatingOAuth2TokenGenerator(accessTokenGenerator, new OAuth2RefreshTokenGenerator());
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new FormAuthenticationFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new SsoLogoutSuccessHandler();
    }

    /**
     * request -> xToken 注入请求转换器
     *
     * @return DelegatingAuthenticationConverter
     */
    private AuthenticationConverter accessTokenRequestConverter() {
        return new DelegatingAuthenticationConverter(Arrays.asList(
                new PasswordGrantAuthenticationConverter(),
                new MobileGrantAuthenticationConverter(),
                new OAuth2RefreshTokenAuthenticationConverter(),
                new OAuth2ClientCredentialsAuthenticationConverter(),
                new OAuth2AuthorizationCodeAuthenticationConverter(),
                new OAuth2AuthorizationCodeRequestAuthenticationConverter()));
    }

    /**
     * 注入授权模式实现提供方
     * <p>
     * 1. 密码模式 </br>
     * 2. 短信登录 </br>
     */
    @SuppressWarnings("unchecked")
    private void addCustomOAuth2GrantAuthenticationProvider(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);

        PasswordGrantAuthenticationProvider passwordGrantAuthenticationProvider = new PasswordGrantAuthenticationProvider(authenticationManager, authorizationService, oAuth2TokenGenerator());

        MobileGrantAuthenticationProvider mobileGrantAuthenticationProvider = new MobileGrantAuthenticationProvider(authenticationManager, authorizationService, oAuth2TokenGenerator());

        // 处理 UsernamePasswordAuthenticationToken
        http.authenticationProvider(new CoolDaoUserDetailsAuthenticationProvider());
        // 处理 PasswordGrantAuthenticationToken
        http.authenticationProvider(passwordGrantAuthenticationProvider);
        // 处理 MobileGrantAuthenticationToken
        http.authenticationProvider(mobileGrantAuthenticationProvider);
    }


}

