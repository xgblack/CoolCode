package com.xgblack.cool.config;


/**
 * Jackson 全局配置类
 *
 * @author xg black
 * @date 2023/12/20 17:06
 */

//@JsonComponent
public class JacksonConfig {

    //@Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    //private String pattern;

    private final String localDatePattern = "yyyy-MM-dd";
    private final String localTimePattern = "HH:mm:ss";


    /**
     * LocalDateTime 类型全局时间格式化
     * 有特殊需求使用 @JsonFormat 指定
     * @return
     */
    /*@Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {

            *//* 时间返回 自定义格式使用 @JsonFormat *//*
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern)));
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(localDatePattern)));
            builder.serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(localTimePattern)));

            *//* 时间接收 自定义格式使用 @DateTimeFormat *//*
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern)));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(localDatePattern)));
            builder.deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(localTimePattern)));
        };

    }*/
}
