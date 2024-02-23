package com.xgblack.cool.module.system.common.enums.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别的枚举值
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@AllArgsConstructor
public enum SexEnum {

    /** 男 */
    MALE(1),
    /** 女 */
    FEMALE(2),
    /* 未知 */
    UNKNOWN(3);

    /**
     * 性别
     */
    private final Integer sex;

}
