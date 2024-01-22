package com.xgblack.cool.framework.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgblack.cool.framework.security.core.component.CoolBearerTokenExtractor;
import com.xgblack.cool.framework.security.core.component.CoolOpaqueTokenIntrospector;
import com.xgblack.cool.framework.security.core.component.PermitAllUrlProperties;
import com.xgblack.cool.framework.security.core.component.ResourceAuthExceptionEntryPoint;
import com.xgblack.cool.framework.security.core.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

/**
 * 资源服务器自动配置类
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@RequiredArgsConstructor
@EnableConfigurationProperties(PermitAllUrlProperties.class)
public class CoolResourceServerAutoConfiguration {
    /**
     * 鉴权具体的实现逻辑
     * @return （#pms.xxx）
     */
    @Bean("pms")
    public PermissionService permissionService() {
        return new PermissionService();
    }

    /**
     * 请求令牌的抽取逻辑
     * @param urlProperties 对外暴露的接口列表
     * @return BearerTokenExtractor
     */
    @Bean
    public CoolBearerTokenExtractor bearerTokenExtractor(PermitAllUrlProperties urlProperties) {
        return new CoolBearerTokenExtractor(urlProperties);
    }

    /**
     * 资源服务器异常处理
     * @param objectMapper jackson 输出对象
     * @param securityMessageSource 自定义国际化处理器
     * @return ResourceAuthExceptionEntryPoint
     */
    @Bean
    public ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint(ObjectMapper objectMapper, MessageSource securityMessageSource) {
        return new ResourceAuthExceptionEntryPoint(objectMapper, securityMessageSource);
    }

    /**
     * 资源服务器toke内省处理器
     * @param authorizationService token 存储实现
     * @return TokenIntrospect
     */
    @Bean
    public OpaqueTokenIntrospector opaqueTokenIntrospector(OAuth2AuthorizationService authorizationService) {
        return new CoolOpaqueTokenIntrospector(authorizationService);
    }
}
