package com.xgblack.cool.framework.common.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

/**
 * 手机号码校验
 * @author xg BLACK
 * @date 2023/12/24 11:25
 */

@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER,
        ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = MobileValidator.class
)
public @interface Mobile {
}
