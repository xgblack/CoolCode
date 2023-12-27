package com.xgblack.cool.framework.response;


import com.xgblack.cool.framework.common.response.api.ResponseFactory;
import com.xgblack.cool.framework.common.response.api.ResponseStatusFactory;
import com.xgblack.cool.framework.common.utils.response.CoolRespUtils;
import com.xgblack.cool.framework.response.advice.GlobalExceptionAdvice;
import com.xgblack.cool.framework.response.advice.NotVoidResponseBodyAdvice;
import com.xgblack.cool.framework.response.advice.ValidationExceptionAdvice;
import com.xgblack.cool.framework.response.advice.VoidResponseBodyAdvice;
import com.xgblack.cool.framework.response.defaults.DefaultResponseFactory;
import com.xgblack.cool.framework.response.defaults.DefaultResponseStatusFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>全局返回值处理的自动配置.</p>
 * 参考 <a href="https://github.com/feiniaojin/graceful-response">Graceful Response</a> 修改实现，可阅读<a href="https://doc.feiniaojin.com/">文档</a>
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@AutoConfiguration
@EnableConfigurationProperties(CoolResponseProperties.class)
public class CoolResponseAutoConfig {

    @Bean
    @ConditionalOnMissingBean(value = GlobalExceptionAdvice.class)
    public GlobalExceptionAdvice globalExceptionAdvice() {
        return new GlobalExceptionAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(value = ValidationExceptionAdvice.class)
    public ValidationExceptionAdvice validationExceptionAdvice() {
        return new ValidationExceptionAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(NotVoidResponseBodyAdvice.class)
    public NotVoidResponseBodyAdvice notVoidResponseBodyAdvice() {
        return new NotVoidResponseBodyAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(VoidResponseBodyAdvice.class)
    public VoidResponseBodyAdvice voidResponseBodyAdvice() {
        return new VoidResponseBodyAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(value = {ResponseFactory.class})
    public ResponseFactory responseBeanFactory() {
        DefaultResponseFactory defaultResponseFactory = new DefaultResponseFactory();
        CoolRespUtils.setResponseFactory(defaultResponseFactory);
        return defaultResponseFactory;
    }

    @Bean
    @ConditionalOnMissingBean(value = {ResponseStatusFactory.class})
    public ResponseStatusFactory responseStatusFactory() {
        return new DefaultResponseStatusFactoryImpl();
    }

    @Bean
    public ExceptionAliasRegister exceptionAliasRegister() {
        return new ExceptionAliasRegister();
    }

}
