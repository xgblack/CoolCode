package com.xgblack.cool.framework.response.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Iterator;
import java.util.List;

/**
 * mvc配置
 * 将MappingJackson2HttpMessageConverter排在最前面
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Configuration
public class CoolWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 使用迭代器遍历 converters，将 MappingJackson2HttpMessageConverter 放在前面
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter<?> converter = iterator.next();
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                // 移除 MappingJackson2HttpMessageConverter
                iterator.remove();
                // 将 MappingJackson2HttpMessageConverter 放在第一个位置
                converters.addFirst(converter);
                break;
            }
        }
        WebMvcConfigurer.super.extendMessageConverters(converters);
    }
}
