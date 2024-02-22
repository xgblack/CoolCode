package com.xgblack.cool.module.system.common.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色类型
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@AllArgsConstructor
public enum RoleTypeEnum {

    /**
     * 内置角色
     */
    SYSTEM(1),
    /**
     * 自定义角色
     */
    CUSTOM(2);

    private final Integer type;

}
