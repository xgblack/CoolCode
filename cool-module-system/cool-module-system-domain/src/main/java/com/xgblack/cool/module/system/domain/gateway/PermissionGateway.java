package com.xgblack.cool.module.system.domain.gateway;

import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface PermissionGateway {

    Set<Long> getRoleIdsByUserId(Long userId);

    void insertUserRole(Long userId, Set<Long> roleIds);

    void deleteUserRole(Long userId, Set<Long> roleIds);

    Set<Long> getMenuIdsByRoleId(Long roleId);

    void insertRoleMenu(Long roleId, Set<Long> menuIds);

    void deleteRoleMenu(Long roleId, Set<Long> menuIds);
}
