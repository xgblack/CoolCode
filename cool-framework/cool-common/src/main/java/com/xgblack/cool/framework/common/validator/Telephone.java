package com.xgblack.cool.framework.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 校验手机或座机号码
 * @author xg BLACK
 * @date 2023/12/24 11:32
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
        validatedBy = TelephoneValidator.class
)
public @interface Telephone {
    String message() default "电话格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
