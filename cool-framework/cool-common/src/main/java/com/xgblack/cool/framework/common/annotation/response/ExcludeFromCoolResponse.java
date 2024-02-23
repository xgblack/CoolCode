package com.xgblack.cool.framework.common.annotation.response;

import java.lang.annotation.*;

/**
 * 使用此注解直接返回controller属性, 不封装
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcludeFromCoolResponse {
}
