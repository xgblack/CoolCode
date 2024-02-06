package com.xgblack.cool.framework.common.validator;

import com.xgblack.cool.framework.common.utils.validation.ValidationUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.dromara.hutool.core.text.StrUtil;

/**
 * @author xg BLACK
 * @date 2023/12/24 11:25
 */

public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private boolean required = false;
    @Override
    public void initialize(Mobile annotation) {
        this.required = annotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required) {
            return true;
        }
        // 如果手机号为空，默认不校验，即校验通过
        if (StrUtil.isEmpty(value)) {
            return true;
        }
        // 校验手机
        return ValidationUtils.isMobile(value);
    }
}
