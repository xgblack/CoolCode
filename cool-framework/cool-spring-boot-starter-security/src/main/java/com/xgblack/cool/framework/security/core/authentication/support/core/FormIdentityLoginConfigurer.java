package com.xgblack.cool.framework.security.core.authentication.support.core;

import com.xgblack.cool.framework.security.core.authentication.support.handler.FormAuthenticationFailureHandler;
import com.xgblack.cool.framework.security.core.authentication.support.handler.SsoLogoutSuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

/**
 * 基于授权码模式
 * <p>
 * 统一认证登录 spring security & sas 都可以使用 所以抽取成 HttpConfigurer
 * @author <a href="https://www.xgblack.cn">xg black</a>
 *
 *
 */
public final class FormIdentityLoginConfigurer extends AbstractHttpConfigurer<FormIdentityLoginConfigurer, HttpSecurity> {

	@Override
	public void init(HttpSecurity http) throws Exception {
		http.formLogin(formLogin -> {
			formLogin.loginPage("/token/login");
			formLogin.loginProcessingUrl("/token/form");
			formLogin.failureHandler(new FormAuthenticationFailureHandler());

		})
			.logout(logout -> logout.logoutSuccessHandler(new SsoLogoutSuccessHandler())
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)) // SSO登出成功处理

			.csrf(AbstractHttpConfigurer::disable);
	}

}
