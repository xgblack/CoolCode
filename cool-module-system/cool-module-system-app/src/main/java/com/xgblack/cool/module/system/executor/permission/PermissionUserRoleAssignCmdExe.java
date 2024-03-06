package com.xgblack.cool.module.system.executor.permission;

import com.xgblack.cool.module.system.domain.gateway.PermissionGateway;
import com.xgblack.cool.module.system.dto.permission.PermissionUserRoleAssignCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.collection.set.SetUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionUserRoleAssignCmdExe {

    private final PermissionGateway gateway;

    @Transactional(rollbackFor = Exception.class)
    public void execute(PermissionUserRoleAssignCmd cmd) {

        // 获得角色拥有角色编号
        Set<Long> dbRoleIds = gateway.getRoleIdsByUserId(cmd.getUserId());
        // 用户要修改拥有的角色编号
        Set<Long> roleIdList = CollUtil.emptyIfNull(cmd.getRoleIds());

        // 计算新增和删除的角色编号
        Set<Long> createRoleIds = SetUtil.of(CollUtil.subtract(roleIdList, dbRoleIds));
        Set<Long> deleteRoleIds = SetUtil.of(CollUtil.subtract(dbRoleIds, roleIdList));

        // 执行新增和删除。对于已经授权的角色，不用做任何处理
        gateway.insertUserRole(cmd.getUserId(), createRoleIds);
        gateway.deleteUserRole(cmd.getUserId(), deleteRoleIds);

    }
}
