package com.xgblack.cool.module.system.common.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型枚举类
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    DIR(1), // 目录
    MENU(2), // 菜单
    BUTTON(3) // 按钮
    ;

    /**
     * 类型
     */
    private final Integer type;

}
