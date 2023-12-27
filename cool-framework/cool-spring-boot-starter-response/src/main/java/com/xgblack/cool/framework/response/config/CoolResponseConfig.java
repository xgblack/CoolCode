package com.xgblack.cool.framework.response.config;

import com.xgblack.cool.framework.response.exception.NotFoundException;
import com.xgblack.cool.framework.response.handler.CharSequenceOrMappingJackson2HttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Configuration
public class CoolResponseConfig extends AbstractExceptionAliasRegisterConfig {

    /**
     * 注册异常别名
     * @param register
     */
    @Override
    protected void registerAlias(ExceptionAliasRegister register) {
        register.doRegisterExceptionAlias(NotFoundException.class);
    }

    @Bean
    public CharSequenceOrMappingJackson2HttpMessageConverter charSequenceOrMappingJackson2HttpMessageConverter() {
        return new CharSequenceOrMappingJackson2HttpMessageConverter();
    }

}
