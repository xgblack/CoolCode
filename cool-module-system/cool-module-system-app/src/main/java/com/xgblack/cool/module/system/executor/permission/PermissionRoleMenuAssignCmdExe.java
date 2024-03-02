package com.xgblack.cool.module.system.executor.permission;

import com.xgblack.cool.module.system.domain.gateway.PermissionGateway;
import com.xgblack.cool.module.system.dto.permission.PermissionRoleMenuAssignCmd;
import com.xgblack.cool.module.system.gateway.database.dataobject.RoleMenuDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.collection.set.SetUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionRoleMenuAssignCmdExe {

    private final PermissionGateway gateway;

    @Transactional(rollbackFor = Exception.class)
    public void execute(PermissionRoleMenuAssignCmd cmd) {


        // 获得角色拥有菜单编号
        Set<Long> dbMenuIds = gateway.getMenuIdsByRoleId(cmd.getRoleId());
        // 计算新增和删除的菜单编号
        Set<Long> menuIdList = CollUtil.emptyIfNull(cmd.getMenuIds());
        Set<Long> createMenuIds = SetUtil.of(CollUtil.subtract(menuIdList, dbMenuIds));
        Set<Long> deleteMenuIds = SetUtil.of(CollUtil.subtract(dbMenuIds, menuIdList));

        // 执行新增和删除。对于已经授权的菜单，不用做任何处理
        gateway.insertRoleMenu(cmd.getRoleId(), createMenuIds);
        gateway.deleteRoleMenu(cmd.getRoleId(), deleteMenuIds);

    }


}
