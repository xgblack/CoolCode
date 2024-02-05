package com.xgblack.cool.framework.common.annotation.response;


import com.xgblack.cool.framework.common.constants.DefaultResponseConstants;

import java.lang.annotation.*;

/**
 * 指定参数校验的异常码
 * @author <a href="https://www.xgblack.cn">xg black</a>
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
    long code() default DefaultResponseConstants.DEFAULT_ERROR_CODE;
}
