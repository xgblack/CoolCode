package com.xgblack.cool.framework.common.annotation.response;


import java.lang.annotation.*;


/**
 * 异常映射注解.
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExceptionMapper {

    /**
     * 异常对应的错误码.
     *
     * @return 异常对应的错误码
     */
    long code() default -1;

    /**
     * 异常信息.
     *
     * @return 异常对应的提示信息
     */
    String msg() default "server error!";

    /**
     * 异常信息是否支持替换
     * 仅当msgReplaceable==ture，且异常实例的message不为空时才能替换
     *
     * @return
     */
    boolean msgReplaceable() default false;
}
