package com.xgblack.cool.module.system.gateway;

import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.xgblack.cool.module.system.domain.gateway.PermissionGateway;
import com.xgblack.cool.module.system.gateway.database.dataobject.RoleMenuDO;
import com.xgblack.cool.module.system.gateway.database.dataobject.UserRoleDO;
import com.xgblack.cool.module.system.gateway.database.dataobject.table.RoleMenuTableDef;
import com.xgblack.cool.module.system.gateway.database.dataobject.table.UserRoleTableDef;
import com.xgblack.cool.module.system.gateway.database.mapper.RoleMenuMapper;
import com.xgblack.cool.module.system.gateway.database.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.collection.ListUtil;
import org.dromara.hutool.core.collection.set.SetUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionGatewayImpl implements PermissionGateway {

    private final UserRoleMapper userRoleMapper;

    private final RoleMenuMapper roleMenuMapper;


    @Override
    public Set<Long> getRoleIdsByUserId(Long userId) {
        List<Long> roleIds = QueryChain.of(userRoleMapper)
                .select(UserRoleTableDef.USER_ROLE.ROLE_ID)
                .from(UserRoleTableDef.USER_ROLE)
                .and(UserRoleTableDef.USER_ROLE.USER_ID.eq(userId))
                .objListAs(Long.class);
        if (CollUtil.isEmpty(roleIds)) {
            return SetUtil.empty();
        }
        return SetUtil.of(roleIds);
    }

    @Override
    public void insertUserRole(Long userId, Set<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return;
        }
        List<UserRoleDO> list = ListUtil.empty();
        for (Long roleId : roleIds) {
            list.add(new UserRoleDO().setRoleId(roleId).setUserId(userId));
        }
        userRoleMapper.insertBatch(list);
    }

    @Override
    public void deleteUserRole(Long userId, Set<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return;
        }
        QueryWrapper query = QueryWrapper.create()
                .from(UserRoleTableDef.USER_ROLE)
                .and(UserRoleTableDef.USER_ROLE.USER_ID.eq(userId))
                .and(UserRoleTableDef.USER_ROLE.ROLE_ID.in(roleIds));
        userRoleMapper.deleteByQuery(query);
    }

    @Override
    public Set<Long> getMenuIdsByRoleId(Long roleId) {
        List<Long> menuIds = QueryChain.of(roleMenuMapper)
                .select(RoleMenuTableDef.ROLE_MENU.MENU_ID)
                .from(RoleMenuTableDef.ROLE_MENU)
                .and(RoleMenuTableDef.ROLE_MENU.ROLE_ID.eq(roleId))
                .objListAs(Long.class);
        if (CollUtil.isEmpty(menuIds)) {
            return SetUtil.empty();
        }
        return SetUtil.of(menuIds);
    }

    @Override
    public void insertRoleMenu(Long roleId, Set<Long> menuIds) {
        if (CollUtil.isEmpty(menuIds)) {
            return;
        }
        List<RoleMenuDO> list = ListUtil.empty();
        for (Long menuId : menuIds) {
            list.add(new RoleMenuDO().setRoleId(roleId).setMenuId(menuId));
        }
        roleMenuMapper.insertBatch(list);
    }

    @Override
    public void deleteRoleMenu(Long roleId, Set<Long> menuIds) {
        if (CollUtil.isEmpty(menuIds)) {
            return;
        }
        QueryWrapper query = QueryWrapper.create()
                .from(RoleMenuTableDef.ROLE_MENU)
                .and(RoleMenuTableDef.ROLE_MENU.ROLE_ID.eq(roleId))
                .and(RoleMenuTableDef.ROLE_MENU.MENU_ID.in(menuIds));
        userRoleMapper.deleteByQuery(query);
    }


}
