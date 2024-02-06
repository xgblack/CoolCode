package com.xgblack.cool.module.system.common.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dromara.hutool.core.text.StrUtil;

/**
 * 角色标识枚举
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@AllArgsConstructor
public enum RoleCodeEnum {

    SUPER_ADMIN("super_admin", "超级管理员"),
    TENANT_ADMIN("tenant_admin", "租户管理员"),
    ;

    /**
     * 角色编码
     */
    private final String code;
    /**
     * 名字
     */
    private final String name;

    public static boolean isSuperAdmin(String code) {
        return StrUtil.equals(code, SUPER_ADMIN.getCode());
    }

}
