package com.xgblack.cool.framework.security.config;

import com.xgblack.cool.framework.security.core.exception.MyAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 的相关配置
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class DefaultSecurityConfig {


    /**
     *Spring Security 过滤链配置（此处是纯Spring Security相关配置）
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/assets/**", "/webjars/**", "/login").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.loginPage("/login")
                );

        http
                .oauth2ResourceServer((resourceServer) -> resourceServer
                        .jwt(Customizer.withDefaults())
                        .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                );

        return http.build();
    }

}
