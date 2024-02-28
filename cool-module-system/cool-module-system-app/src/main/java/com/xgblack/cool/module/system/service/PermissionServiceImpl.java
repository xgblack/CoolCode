package com.xgblack.cool.module.system.service;

import com.xgblack.cool.module.system.api.PermissionServiceI;
import com.xgblack.cool.module.system.dto.permission.PermissionUserRoleAssignCmd;
import com.xgblack.cool.module.system.executor.permission.PermissionUserRoleAssignCmdExe;
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

    @Override
    public void assignUserRole(PermissionUserRoleAssignCmd cmd) {
        permissionUserRoleAssignCmdExe.execute(cmd);
    }

    @Override
    public Set<Long> queryUserRoleIdsByUserId(Long userId) {
        return permissionRoleIdsByUserIdQryExe.execute(userId);
    }

}
