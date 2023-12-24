package com.xgblack.cool.framework.common.validator;

import cn.hutool.core.util.StrUtil;
import com.xgblack.cool.framework.common.utils.validation.ValidationUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author xg BLACK
 * @date 2023/12/24 11:25
 */

public class MobileValidator implements ConstraintValidator<Mobile, String> {
    @Override
    public void initialize(Mobile annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果手机号为空，默认不校验，即校验通过
        if (StrUtil.isEmpty(value)) {
            return true;
        }
        // 校验手机
        return ValidationUtils.isMobile(value);
    }
}
