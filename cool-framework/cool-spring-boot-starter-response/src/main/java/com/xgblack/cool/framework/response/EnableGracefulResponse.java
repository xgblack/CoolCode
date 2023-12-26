package com.xgblack.cool.framework.response;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * 注解启动全局结果处理的入口.
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AutoConfig.class)
public @interface EnableGracefulResponse {
}
