package com.xgblack.cool.module.system.gateway;

import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.xgblack.cool.module.system.domain.gateway.PermissionGateway;
import com.xgblack.cool.module.system.gateway.database.dataobject.UserRoleDO;
import com.xgblack.cool.module.system.gateway.database.dataobject.table.UserRoleTableDef;
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
                .and(UserRoleTableDef.USER_ROLE.ROLE_ID.in(roleIds));
        userRoleMapper.deleteByQuery(query);
    }


}
