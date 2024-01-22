package com.xgblack.cool.framework.response.advice;

import com.xgblack.cool.framework.common.annotation.response.ExcludeFromCoolResponse;
import com.xgblack.cool.framework.common.response.Response;
import com.xgblack.cool.framework.common.response.api.ResponseFactory;
import com.xgblack.cool.framework.response.config.CoolResponseProperties;
import com.xgblack.cool.framework.response.config.CoolWebMvcConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * 非空返回值的处理.
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
@ControllerAdvice
@Order(value = 1000)
public class NotVoidResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Resource
    private ResponseFactory responseFactory;
    @Resource
    private CoolResponseProperties properties;

    /**
     * 路径过滤器
     */
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    /**
     * 只处理不返回void的，并且MappingJackson2HttpMessageConverter支持的类型.<br></br>
     * 包装String类型需要在 {@link CoolWebMvcConfig#extendMessageConverters(List)} 将converters顺序调整一下，
     * 使MappingJackson2HttpMessageConverter早于StringHttpMessageConverter
     *
     * @param methodParameter 方法参数
     * @param clazz           处理器
     * @return 是否支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> clazz) {
        Method method = methodParameter.getMethod();


        //method为空、返回值为void、非JSON，直接跳过
        if (Objects.isNull(method) || method.getReturnType().equals(Void.TYPE) ) {
            //log.trace("Cool Response:method为空、返回值为void，跳过");
            return false;
        }
        if (!MappingJackson2HttpMessageConverter.class.isAssignableFrom(clazz)) {
            log.trace("Cool Response:非JSON、非字符类型，跳过");
            return false;
        }

        //有ExcludeFromCoolResponse注解修饰的，也跳过
        if (method.isAnnotationPresent(ExcludeFromCoolResponse.class)) {
            if (log.isTraceEnabled()) {
                log.trace("Cool Response:方法被@ExcludeFromCoolResponse注解修饰，跳过:methodName={}", method.getName());
            }
            return false;
        }

        //配置了例外包路径，则该路径下的controller都不再处理
        List<String> excludePackages = properties.getExcludePackages();
        if (!CollectionUtils.isEmpty(excludePackages)) {
            // 获取请求所在类的的包名
            String packageName = method.getDeclaringClass().getPackage().getName();
            if (excludePackages.stream().anyMatch(item -> ANT_PATH_MATCHER.match(item, packageName))) {
                log.trace("Cool Response:匹配到excludePackages例外配置，跳过:packageName={},", packageName);
                return false;
            }
        }
        //log.trace("Cool Response:非空返回值，需要进行封装");
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> clazz,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (body == null) {
            return responseFactory.newSuccessInstance();
        } else if (body instanceof Response) {
            return body;
        } else {
            /*if (log.isTraceEnabled()) {
                String path = serverHttpRequest.getURI().getPath();
                log.trace("Cool Response:非空返回值，执行封装:path={}", path);
            }*/
            return responseFactory.newSuccessInstance(body);
        }
    }

}
