package com.xgblack.cool.framework.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 的相关配置
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Configuration
@EnableWebSecurity
public class DefaultSecurityConfig {


    /**
     *Spring Security 过滤链配置（此处是纯Spring Security相关配置）
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
//        http
//                //设置所有请求都需要认证，未认证的请求都被重定向到login页面进行登录
//                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().authenticated()
//                )
//                // 由Spring Security过滤链中UsernamePasswordAuthenticationFilter过滤器拦截处理“login”页面提交的登录信息。
//                .formLogin(Customizer.withDefaults());
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/assets/**", "/webjars/**", "/login").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                );

        return http.build();
    }


    /**
     * 设置用户信息，校验用户名、密码
     * 这里或许有人会有疑问，不是说OAuth 2.1已经移除了密码模式了码？怎么这里还有用户名、密码登录？
     * 例如：某平台app支持微信登录，用户想使用微信账号登录登录该平台app，则用户需先登录微信app，
     * 此处代码的操作就类似于某平台app跳到微信登录界面让用户先登录微信，然后微信校验用户提交的用户名、密码，
     * 登录了微信才对某平台app进行授权，对于微信平台来说，某平台的app就是OAuth 2.1中的客户端。
     * 其实，这一步是Spring Security的操作，纯碎是认证平台的操作，是脱离客户端（第三方平台）的。
     */
    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        //基于内存的用户数据校验
        return new InMemoryUserDetailsManager(userDetails);
    }*/


}
