package com.xgblack.cool.framework.security.config;

import com.xgblack.cool.framework.security.core.component.CoolBearerTokenExtractor;
import com.xgblack.cool.framework.security.core.component.PermitAllUrlProperties;
import com.xgblack.cool.framework.security.core.component.ResourceAuthExceptionEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security 的相关配置<p></p>
 * <p>资源服务器认证授权配置</p>
 *
 * <p>&#064;EnableMethodSecurity </p>
 * <p>1.设置注解属性 jsr250Enabled = true 是为了启用JSR250注解支持，例如@RolesAllowed、@PermitAll和@DenyAll注解</p>
 * <p>2.设置属性securedEnabled = true 是为了启用@Secured注解支持，不设置属性则添加Secured注解无效</p>
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class CoolResourceServerConfiguration {

    /** 客户端异常处理 */
    protected final ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

    /** 资源服务器对外直接暴露URL */
    private final PermitAllUrlProperties permitAllUrl;

    /** 请求令牌的抽取逻辑 */
    private final CoolBearerTokenExtractor coolBearerTokenExtractor;

    /** 资源服务器toke内省处理器 {@link com.xgblack.cool.framework.security.core.component.CoolOpaqueTokenIntrospector} */
    private final OpaqueTokenIntrospector coolOpaqueTokenIntrospector;

    /**
     *Spring Security 过滤链配置（此处是纯Spring Security相关配置）,配置 URL 的安全配置
     * <p>
     * anyRequest          |   匹配所有请求路径<p>
     * access              |   SpringEl表达式结果为true时可以访问<p>
     * anonymous           |   匿名可以访问<p>
     * denyAll             |   用户不能访问<p>
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）<p>
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问<p>
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问<p>
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问<p>
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问<p>
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问<p>
     * permitAll           |   用户可以任意访问<p>
     * rememberMe          |   允许通过remember-me登录的用户访问<p>
     * authenticated       |   用户登录后可访问<p>
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        //默认放行的urls、配置文件中配置的放行的urls、添加了@Inner注解的
        AntPathRequestMatcher[] requestMatchers = permitAllUrl.getUrls()
                .stream()
                .map(AntPathRequestMatcher::new)
                .toList()
                .toArray(new AntPathRequestMatcher[] {});

        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(requestMatchers)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(
                        oauth2 -> oauth2.opaqueToken(token -> token.introspector(coolOpaqueTokenIntrospector))
                                .authenticationEntryPoint(resourceAuthExceptionEntryPoint)
                                .bearerTokenResolver(coolBearerTokenExtractor))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }



    //TODO: 待优化
    /*@Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // 登出
        httpSecurity
                // 开启跨域
                .cors(Customizer.withDefaults())
                // CSRF 禁用，因为不使用 Session
                .csrf(AbstractHttpConfigurer::disable)
                // 基于 token 机制，所以不需要 Session
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(c -> c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                // 一堆自定义的 Spring Security 处理器
                .exceptionHandling(c -> c.authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler));
        // 登录、登录暂时不使用 Spring Security 的拓展点，主要考虑一方面拓展多用户、多种登录方式相对复杂，一方面用户的学习成本较高

        // 获得 @PermitAll 带来的 URL 列表，免登录
        Multimap<HttpMethod, String> permitAllUrls = getPermitAllUrlsFromAnnotations();
        // 设置每个请求的权限
        httpSecurity
                // ①：全局共享规则
                .authorizeHttpRequests(c -> c
                        // 1.1 静态资源，可匿名访问
                        .requestMatchers(HttpMethod.GET, "/*.html", "/*.html", "/*.css", "/*.js").permitAll()
                        // 1.1 设置 @PermitAll 无需认证
                        .requestMatchers(HttpMethod.GET, permitAllUrls.get(HttpMethod.GET).toArray(new String[0])).permitAll()
                        .requestMatchers(HttpMethod.POST, permitAllUrls.get(HttpMethod.POST).toArray(new String[0])).permitAll()
                        .requestMatchers(HttpMethod.PUT, permitAllUrls.get(HttpMethod.PUT).toArray(new String[0])).permitAll()
                        .requestMatchers(HttpMethod.DELETE, permitAllUrls.get(HttpMethod.DELETE).toArray(new String[0])).permitAll()
                        .requestMatchers(HttpMethod.HEAD, permitAllUrls.get(HttpMethod.HEAD).toArray(new String[0])).permitAll()
                        .requestMatchers(HttpMethod.PATCH, permitAllUrls.get(HttpMethod.PATCH).toArray(new String[0])).permitAll()
                        // 1.2 基于 yudao.security.permit-all-urls 无需认证
                        .requestMatchers(securityProperties.getPermitAllUrls().toArray(new String[0])).permitAll()
                        // 1.3 设置 App API 无需认证
                        //.requestMatchers(buildAppApi("/**")).permitAll()
                )
                // ②：每个项目的自定义规则
                //.authorizeHttpRequests(c -> authorizeRequestsCustomizers.forEach(customizer -> customizer.customize(c)))
                // ③：兜底规则，必须认证
                .authorizeHttpRequests(c -> c.anyRequest().authenticated());

        // 添加 Token Filter
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }*/

    /*private String buildAppApi(String url) {
        return webProperties.getAppApi().getPrefix() + url;
    }*/

    /*private Multimap<HttpMethod, String> getPermitAllUrlsFromAnnotations() {
        Multimap<HttpMethod, String> result = HashMultimap.create();
        // 获得接口对应的 HandlerMethod 集合
        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping)
                applicationContext.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();
        // 获得有 @PermitAll 注解的接口
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = entry.getValue();
            if (!handlerMethod.hasMethodAnnotation(PermitAll.class)) {
                continue;
            }
            if (entry.getKey().getPatternsCondition() == null) {
                continue;
            }
            Set<String> urls = entry.getKey().getPatternsCondition().getPatterns();
            // 特殊：使用 @RequestMapping 注解，并且未写 method 属性，此时认为都需要免登录
            Set<RequestMethod> methods = entry.getKey().getMethodsCondition().getMethods();
            if (CollUtil.isEmpty(methods)) { //
                result.putAll(HttpMethod.GET, urls);
                result.putAll(HttpMethod.POST, urls);
                result.putAll(HttpMethod.PUT, urls);
                result.putAll(HttpMethod.DELETE, urls);
                result.putAll(HttpMethod.HEAD, urls);
                result.putAll(HttpMethod.PATCH, urls);
                continue;
            }
            // 根据请求方法，添加到 result 结果
            entry.getKey().getMethodsCondition().getMethods().forEach(requestMethod -> {
                switch (requestMethod) {
                    case GET:
                        result.putAll(HttpMethod.GET, urls);
                        break;
                    case POST:
                        result.putAll(HttpMethod.POST, urls);
                        break;
                    case PUT:
                        result.putAll(HttpMethod.PUT, urls);
                        break;
                    case DELETE:
                        result.putAll(HttpMethod.DELETE, urls);
                        break;
                    case HEAD:
                        result.putAll(HttpMethod.HEAD, urls);
                        break;
                    case PATCH:
                        result.putAll(HttpMethod.PATCH, urls);
                        break;
                }
            });
        }
        return result;
    }*/

}
