package com.xgblack.framework.security.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@AutoConfiguration
public class CoolSecurityAutoConfig {

    /**
     * 内置一个userService，用于查询用户信息
     * 后面通过定义接口从数据库查询
     * @return
     */
    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
        users.createUser(User.withUsername("xgblack").password("{noop}123").roles("admin").build());
        users.createUser(User.withUsername("admin").password("{noop}123").roles("admin").build());
        return users;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            //通常是用来放行静态资源的
            web.ignoring().requestMatchers("/test/string");
        };
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain() {

    }*/
}
