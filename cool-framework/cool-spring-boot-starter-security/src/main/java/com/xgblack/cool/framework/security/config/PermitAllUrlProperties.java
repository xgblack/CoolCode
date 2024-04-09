package com.xgblack.cool.framework.security.config;

import com.xgblack.cool.framework.security.annotation.Inner;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.regex.ReUtil;
import org.dromara.hutool.extra.spring.SpringUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 资源服务器对外直接暴露URL,如果设置context-path 要特殊处理
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@ConfigurationProperties(prefix = "security.oauth2.ignore")
public class PermitAllUrlProperties implements InitializingBean {

    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    private static final String[] DEFAULT_IGNORE_URLS = new String[] { "/actuator/**", "/error", "/v3/api-docs" };

    /**
     * 配置文件中配置的放行的urls，未配置则默认为空列表
     */
    @Getter
    @Setter
    private List<String> urls = new ArrayList<>();

    @Override
    public void afterPropertiesSet() {
        //添加默认放行的路径
        urls.addAll(Arrays.asList(DEFAULT_IGNORE_URLS));

        RequestMappingHandlerMapping mapping = SpringUtil.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);

            // 获取方法上边的注解 替代path variable 为 *
            Inner method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Inner.class);
            Optional.ofNullable(method)
                    .ifPresent(inner -> Objects.requireNonNull(info.getPathPatternsCondition())
                            .getPatternValues()
                            .forEach(url -> urls.add(ReUtil.replaceAll(url, PATTERN, "*"))));

            // 获取类上边的注解, 替代path variable 为 *
            Inner controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Inner.class);
            Optional.ofNullable(controller)
                    .ifPresent(inner -> Objects.requireNonNull(info.getPathPatternsCondition())
                            .getPatternValues()
                            .forEach(url -> urls.add(ReUtil.replaceAll(url, PATTERN, "*"))));
        });
    }
}
