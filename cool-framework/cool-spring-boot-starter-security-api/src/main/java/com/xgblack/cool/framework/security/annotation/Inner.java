package com.xgblack.cool.framework.security.annotation;

import java.lang.annotation.*;

/**
 * 服务调用不鉴权注解
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Documented
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Inner {

    /**
     * 是否AOP统一处理
     * @return false, true
     */
    boolean value() default true;

    /**
     * 需要特殊判空的字段(预留)
     * @return {}
     */
    String[] field() default {};

}
