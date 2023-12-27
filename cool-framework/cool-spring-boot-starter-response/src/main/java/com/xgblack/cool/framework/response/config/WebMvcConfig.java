package com.xgblack.cool.framework.response.config;

import com.xgblack.cool.framework.response.handler.CharSequenceOrMappingJackson2HttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * mvc配置
 * 将CharSequenceOrMappingJackson2HttpMessageConverter排在最前面
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.addFirst(new CharSequenceOrMappingJackson2HttpMessageConverter());
        super.extendMessageConverters(converters);
    }
}
