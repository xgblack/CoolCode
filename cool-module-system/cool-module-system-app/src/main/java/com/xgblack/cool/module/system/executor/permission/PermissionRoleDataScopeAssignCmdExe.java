package com.xgblack.cool.module.system.executor.permission;

import com.xgblack.cool.framework.common.utils.response.CoolThrowable;
import com.xgblack.cool.module.system.domain.gateway.RoleGateway;
import com.xgblack.cool.module.system.domain.permission.Role;
import com.xgblack.cool.module.system.dto.permission.PermissionRoleDataScopeAssignCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.lang.Assert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionRoleDataScopeAssignCmdExe {

    private final RoleGateway roleGateway;

    @Transactional(rollbackFor = Exception.class)
    public void execute(PermissionRoleDataScopeAssignCmd cmd) {

        // 校验是否可以更新
        Role role = roleGateway.getById(cmd.getRoleId());
        CoolThrowable.wrapAssert(() -> Assert.notNull(role, "角色不存在"));

        // 更新数据范围
        Role updateObject = new Role()
                .setId(cmd.getRoleId())
                .setDataScope(cmd.getDataScope())
                .setDataScopeDeptIds(cmd.getDataScopeDeptIds());
        roleGateway.update(updateObject);
    }

}
