package com.xgblack.cool.module.system.service;

import com.xgblack.cool.module.system.api.PermissionServiceI;
import com.xgblack.cool.module.system.dto.permission.PermissionRoleDataScopeAssignCmd;
import com.xgblack.cool.module.system.dto.permission.PermissionRoleMenuAssignCmd;
import com.xgblack.cool.module.system.dto.permission.PermissionUserRoleAssignCmd;
import com.xgblack.cool.module.system.executor.permission.PermissionRoleDataScopeAssignCmdExe;
import com.xgblack.cool.module.system.executor.permission.PermissionRoleMenuAssignCmdExe;
import com.xgblack.cool.module.system.executor.permission.PermissionUserRoleAssignCmdExe;
import com.xgblack.cool.module.system.executor.permission.query.PermissionMenuIdsByRoleIdQryExe;
import com.xgblack.cool.module.system.executor.permission.query.PermissionRoleIdsByUserIdQryExe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionServiceI {

    private final PermissionUserRoleAssignCmdExe permissionUserRoleAssignCmdExe;
    private final PermissionRoleIdsByUserIdQryExe permissionRoleIdsByUserIdQryExe;
    private final PermissionMenuIdsByRoleIdQryExe permissionMenuIdsByRoleIdQryExe;
    private final PermissionRoleMenuAssignCmdExe permissionRoleMenuAssignCmdExe;
    private final PermissionRoleDataScopeAssignCmdExe permissionRoleDataScopeAssignCmdExe;

    @Override
    public void assignUserRole(PermissionUserRoleAssignCmd cmd) {
        permissionUserRoleAssignCmdExe.execute(cmd);
    }

    @Override
    public Set<Long> queryUserRoleIdsByUserId(Long userId) {
        return permissionRoleIdsByUserIdQryExe.execute(userId);
    }

    @Override
    public Set<Long> getRoleMenuListByRoleId(Long roleId) {
        return permissionMenuIdsByRoleIdQryExe.execute(roleId);
    }

    @Override
    public void assignRoleMenu(PermissionRoleMenuAssignCmd cmd) {
        permissionRoleMenuAssignCmdExe.execute(cmd);
    }

    @Override
    public void assignRoleDataScope(PermissionRoleDataScopeAssignCmd cmd) {
        permissionRoleDataScopeAssignCmdExe.execute(cmd);
    }

}
