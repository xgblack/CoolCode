package com.xgblack.cool.framework.response.api;


import com.xgblack.cool.framework.response.defaults.DefaultConstants;

import java.lang.annotation.*;

/**
 * 指定参数校验的异常码
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ValidationStatusCode {

    /**
     * 异常对应的错误码.
     *
     * @return 异常对应的错误码
     */
    long code() default DefaultConstants.DEFAULT_ERROR_CODE;
}
