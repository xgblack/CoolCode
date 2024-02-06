package com.xgblack.cool.framework.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.dromara.hutool.core.data.PhoneUtil;
import org.dromara.hutool.core.text.CharSequenceUtil;

/**
 * @author xg BLACK
 * @date 2023/12/24 11:32
 */

public class TelephoneValidator implements ConstraintValidator<Telephone, String> {

    @Override
    public void initialize(Telephone annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果手机号为空，默认不校验，即校验通过
        if (CharSequenceUtil.isEmpty(value)) {
            return true;
        }
        // 校验手机
        return PhoneUtil.isTel(value) || PhoneUtil.isPhone(value);
    }
}
