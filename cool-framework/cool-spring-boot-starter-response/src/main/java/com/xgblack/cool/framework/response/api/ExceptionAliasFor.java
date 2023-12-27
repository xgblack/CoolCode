package com.xgblack.cool.framework.response.api;


import com.xgblack.cool.framework.common.constants.DefaultResponseConstants;

import java.lang.annotation.*;

/**
 * 异常映射别名，把某个异常设置为外部异常的别名，以便自定义错误码和提示信息
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExceptionAliasFor {

    /**
     * 异常对应的错误码.
     *
     * @return 异常对应的错误码
     */
    long code() default DefaultResponseConstants.DEFAULT_ERROR_CODE;

    /**
     * 异常信息.
     *
     * @return 异常对应的提示信息
     */
    String msg() default DefaultResponseConstants.DEFAULT_ERROR_MSG;

    /**
     * 作为某些异常的别名
     *
     * @return
     */
    Class<? extends Throwable>[] aliasFor();
}
