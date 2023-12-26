package com.xgblack.cool.framework.response.api;

import java.lang.annotation.*;

/**
 * 使用此注解直接返回controller属性, 不封装
 *
 * @author lihao3
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcludeFromGracefulResponse {
}
