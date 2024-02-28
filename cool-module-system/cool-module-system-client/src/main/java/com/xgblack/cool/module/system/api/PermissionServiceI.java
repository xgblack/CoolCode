package com.xgblack.cool.module.system.api;

import com.xgblack.cool.module.system.dto.permission.PermissionUserRoleAssignCmd;

import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface PermissionServiceI {

    void assignUserRole(PermissionUserRoleAssignCmd cmd);

    Set<Long> queryUserRoleIdsByUserId(Long userId);
}
