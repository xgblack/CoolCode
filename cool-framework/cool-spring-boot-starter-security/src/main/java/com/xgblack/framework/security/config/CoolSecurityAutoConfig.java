package com.xgblack.framework.security.config;

import com.xgblack.framework.security.core.context.TransmittableThreadLocalSecurityContextHolderStrategy;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Spring Security 自动配置类
 * 注意，不能和 {@link CoolWebSecurityConfigurerAdapter} 用一个，原因是会导致初始化报错。参见 <a href="https://stackoverflow.com/questions/53847050/spring-boot-delegatebuilder-cannot-be-null-on-autowiring-authenticationmanager">文档</a>。
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
//@AutoConfiguration
//@EnableConfigurationProperties(SecurityProperties.class)
public class CoolSecurityAutoConfig {

    //@Resource
    //private SecurityProperties securityProperties;

    /**
     * 内置一个userService，用于查询用户信息
     * 后面通过定义接口从数据库查询
     * @return
     */
    /*@Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
        users.createUser(User.withUsername("xgblack").password("{noop}123").roles("admin").build());
        users.createUser(User.withUsername("admin").password("{noop}123").roles("admin").build());
        return users;
    }*/

    /*@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            //通常是用来放行静态资源的
            web.ignoring().requestMatchers("/static/doc/*.json");
        };
    }*/

    /*@Bean
    public SecurityFilterChain securityFilterChain() {

    }*/


    /**
     * 处理用户未登录拦截的切面的 Bean
     */
    /*@Bean
    public PreAuthenticatedAspect preAuthenticatedAspect() {
        return new PreAuthenticatedAspect();
    }*/

    /**
     * 认证失败处理类 Bean
     */
    /*@Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }*/

    /**
     * 权限不够处理器 Bean
     */
    /*@Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }*/

    /**
     * Spring Security 加密器
     * 考虑到安全性，这里采用 BCryptPasswordEncoder 加密器
     *
     * @see <a href="http://stackabuse.com/password-encoding-with-spring-security/">Password Encoding with Spring Security</a>
     */
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(securityProperties.getPasswordEncoderLength());
    }*/

    /**
     * Token 认证过滤器 Bean
     */
    /*@Bean
    public TokenAuthenticationFilter authenticationTokenFilter(GlobalExceptionHandler globalExceptionHandler,
                                                               OAuth2TokenApi oauth2TokenApi) {
        return new TokenAuthenticationFilter(securityProperties, globalExceptionHandler, oauth2TokenApi);
    }*/

    /*@Bean("ss") // 使用 Spring Security 的缩写，方便使用
    public SecurityFrameworkService securityFrameworkService(PermissionApi permissionApi) {
        return new SecurityFrameworkServiceImpl(permissionApi);
    }*/

    /**
     * 声明调用 {@link SecurityContextHolder#setStrategyName(String)} 方法，
     * 设置使用 {@link TransmittableThreadLocalSecurityContextHolderStrategy} 作为 Security 的上下文策略
     */
    /*@Bean
    public MethodInvokingFactoryBean securityContextHolderMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
        methodInvokingFactoryBean.setTargetMethod("setStrategyName");
        methodInvokingFactoryBean.setArguments(TransmittableThreadLocalSecurityContextHolderStrategy.class.getName());
        return methodInvokingFactoryBean;
    }*/
}
