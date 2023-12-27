package com.xgblack.framework.security.annotation;

import java.lang.annotation.*;

/**
 * 声明用户需要登录<br></br>
 * 为什么不使用 {@link org.springframework.security.access.prepost.PreAuthorize} 注解，原因是不通过时，抛出的是认证不通过，而不是未登录
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreAuthenticated {
}
