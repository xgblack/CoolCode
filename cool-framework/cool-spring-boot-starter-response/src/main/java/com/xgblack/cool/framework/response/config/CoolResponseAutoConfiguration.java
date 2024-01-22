package com.xgblack.cool.framework.response.config;


import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.xgblack.cool.framework.common.response.api.ResponseFactory;
import com.xgblack.cool.framework.common.response.api.ResponseStatusFactory;
import com.xgblack.cool.framework.common.utils.response.CoolRespUtils;
import com.xgblack.cool.framework.response.advice.GlobalExceptionAdvice;
import com.xgblack.cool.framework.response.advice.NotVoidResponseBodyAdvice;
import com.xgblack.cool.framework.response.advice.ValidationExceptionAdvice;
import com.xgblack.cool.framework.response.advice.VoidResponseBodyAdvice;
import com.xgblack.cool.framework.response.databind.NumberSerializer;
import com.xgblack.cool.framework.response.defaults.DefaultResponseFactory;
import com.xgblack.cool.framework.response.defaults.DefaultResponseStatusFactoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>全局返回值处理的自动配置.</p>
 * 参考 <a href="https://github.com/feiniaojin/graceful-response">Graceful Response</a> 修改实现，可阅读<a href="https://doc.feiniaojin.com/">文档</a>
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@AutoConfiguration
@EnableConfigurationProperties(CoolResponseProperties.class)
public class CoolResponseAutoConfiguration {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    private final String localDatePattern = "yyyy-MM-dd";
    private final String localTimePattern = "HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        /* 时间返回 自定义格式使用 @JsonFormat */
        return builder -> builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern)))
                .serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(localDatePattern)))
                .serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(localTimePattern)))
                /* 时间接收 自定义格式使用 @DateTimeFormat */
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern)))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(localDatePattern)))
                .deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(localTimePattern)))
                // 新增 Long 类型序列化规则，数值超过 2^53-1，在 JS 会出现精度丢失问题，因此 Long 自动序列化为字符串类型
                .serializerByType(Long.class, NumberSerializer.INSTANCE);
    }

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
