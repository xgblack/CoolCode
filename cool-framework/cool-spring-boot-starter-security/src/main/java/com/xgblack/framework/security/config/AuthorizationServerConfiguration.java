package com.xgblack.framework.security.config;

import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.framework.security.auth.support.CustomOAuth2AccessTokenGenerator;
import com.xgblack.framework.security.auth.support.core.CoolDaoAuthenticationProvider;
import com.xgblack.framework.security.auth.support.core.CustomOAuth2TokenCustomizer;
import com.xgblack.framework.security.auth.support.handler.*;
import com.xgblack.framework.security.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationConverter;
import com.xgblack.framework.security.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import com.xgblack.framework.security.auth.support.sms.OAuth2ResourceOwnerSmsAuthenticationConverter;
import com.xgblack.framework.security.auth.support.sms.OAuth2ResourceOwnerSmsAuthenticationProvider;
import com.xgblack.framework.security.core.component.CoolBearerTokenExtractor;
import com.xgblack.framework.security.core.component.PermitAllUrlProperties;
import com.xgblack.framework.security.core.component.ResourceAuthExceptionEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
 * @author lengleng
 * @date 2018/6/22 认证服务器配置
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfiguration {

	private final OAuth2AuthorizationService authorizationService;

	protected final ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

	private final CoolBearerTokenExtractor pigBearerTokenExtractor;

	private final OpaqueTokenIntrospector customOpaqueTokenIntrospector;

	private final PermitAllUrlProperties permitAllUrl;

	private final CoolLoginPreFilter pigLoginPreFilter;

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http,
			CoolAuthenticationSuccessEventHandler successEventHandler,
			CoolAuthenticationFailureEventHandler failureEventHandler) throws Exception {
		OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();

		http.addFilterAfter(pigLoginPreFilter, UsernamePasswordAuthenticationFilter.class);
		http.with(authorizationServerConfigurer.tokenEndpoint((tokenEndpoint) -> {// 个性化认证授权端点
			tokenEndpoint.accessTokenRequestConverter(accessTokenRequestConverter()) // 注入自定义的授权认证Converter
				.accessTokenResponseHandler(successEventHandler) // 登录成功处理器
				.errorResponseHandler(failureEventHandler);// 登录失败处理器
		}).clientAuthentication(oAuth2ClientAuthenticationConfigurer -> // 个性化客户端认证
		oAuth2ClientAuthenticationConfigurer.errorResponseHandler(failureEventHandler))// 处理客户端认证异常
			.authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint// 授权码端点个性化confirm页面
				.consentPage(SecurityConstants.CUSTOM_CONSENT_PAGE_URI)), Customizer.withDefaults())
			.with(authorizationServerConfigurer.authorizationService(authorizationService)// redis存储token的实现
				.authorizationServerSettings(
						AuthorizationServerSettings.builder().issuer(SecurityConstants.PROJECT_LICENSE).build()),
					Customizer.withDefaults());

		AntPathRequestMatcher[] requestMatchers = permitAllUrl.getUrls()
			.stream()
			.map(AntPathRequestMatcher::new)
			.toList()
			.toArray(new AntPathRequestMatcher[] {});

		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers(requestMatchers)
			.permitAll()
			.anyRequest()
			.authenticated())
			.oauth2ResourceServer(
					oauth2 -> oauth2.opaqueToken(token -> token.introspector(customOpaqueTokenIntrospector))
						.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
						.bearerTokenResolver(pigBearerTokenExtractor))
			.exceptionHandling(configurer -> configurer.authenticationEntryPoint(resourceAuthExceptionEntryPoint))
			.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
			.csrf(AbstractHttpConfigurer::disable);

		http.with(authorizationServerConfigurer.authorizationService(authorizationService)// redis存储token的实现
			.authorizationServerSettings(
					AuthorizationServerSettings.builder().issuer(SecurityConstants.PROJECT_LICENSE).build()),
				Customizer.withDefaults());
		DefaultSecurityFilterChain securityFilterChain = http.build();

		// 注入自定义授权模式实现
		addCustomOAuth2GrantAuthenticationProvider(http);
		return securityFilterChain;
	}

	/**
	 * 令牌生成规则实现 </br>
	 * client:username:uuid
	 * @return OAuth2TokenGenerator
	 */
	@Bean
	public OAuth2TokenGenerator oAuth2TokenGenerator() {
		CustomOAuth2AccessTokenGenerator accessTokenGenerator = new CustomOAuth2AccessTokenGenerator();
		// 注入Token 增加关联用户信息
		accessTokenGenerator.setAccessTokenCustomizer(new CustomOAuth2TokenCustomizer());
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
	 * @return DelegatingAuthenticationConverter
	 */
	private AuthenticationConverter accessTokenRequestConverter() {
		return new DelegatingAuthenticationConverter(Arrays.asList(
				new OAuth2ResourceOwnerPasswordAuthenticationConverter(),
				new OAuth2ResourceOwnerSmsAuthenticationConverter(), new OAuth2RefreshTokenAuthenticationConverter(),
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

		OAuth2ResourceOwnerPasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider = new OAuth2ResourceOwnerPasswordAuthenticationProvider(
				authenticationManager, authorizationService, oAuth2TokenGenerator());

		OAuth2ResourceOwnerSmsAuthenticationProvider resourceOwnerSmsAuthenticationProvider = new OAuth2ResourceOwnerSmsAuthenticationProvider(
				authenticationManager, authorizationService, oAuth2TokenGenerator());

		// 处理 UsernamePasswordAuthenticationToken
		http.authenticationProvider(new CoolDaoAuthenticationProvider());
		// 处理 OAuth2ResourceOwnerPasswordAuthenticationToken
		http.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);
		// 处理 OAuth2ResourceOwnerSmsAuthenticationToken
		http.authenticationProvider(resourceOwnerSmsAuthenticationProvider);
	}

}
