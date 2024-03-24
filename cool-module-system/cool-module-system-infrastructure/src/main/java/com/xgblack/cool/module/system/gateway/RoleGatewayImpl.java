package com.xgblack.cool.module.system.gateway;

import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.update.UpdateChain;
import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.convertor.RoleConvertor;
import com.xgblack.cool.module.system.domain.gateway.RoleGateway;
import com.xgblack.cool.module.system.domain.permission.Role;
import com.xgblack.cool.module.system.dto.permission.RolePageQry;
import com.xgblack.cool.module.system.gateway.database.dataobject.RoleDO;
import com.xgblack.cool.module.system.gateway.database.dataobject.table.RoleTableDef;
import com.xgblack.cool.module.system.gateway.database.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleGatewayImpl implements RoleGateway {

    private final RoleMapper roleMapper;

    private final static RoleConvertor convertor = RoleConvertor.INSTANCE;

    @Override
    public Long insert(Role role) {
        RoleDO roleDO = convertor.toDataObject(role);
        roleMapper.insertSelective(roleDO);
        return roleDO.getId();
    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteById(id);
    }

    @Override
    public void update(Role role) {
        roleMapper.update(convertor.toDataObject(role));
    }

    @Override
    public Role getById(Long id) {
        return convertor.convertDO2Entity(roleMapper.selectOneById(id));
    }

    @Override
    public List<Role> getRolesByIds(Collection<Long> ids) {
        return QueryChain.of(roleMapper)
                .from(RoleTableDef.ROLE)
                .and(RoleTableDef.ROLE.ID.in(ids))
                .orderBy(RoleTableDef.ROLE.SORT.asc(),RoleTableDef.ROLE.ID.desc())
                .listAs(Role.class);
    }

    @Override
    public PageResult<Role> getPage(RolePageQry qry) {
        return PageResult.of(
                QueryChain.of(roleMapper)
                        .from(RoleTableDef.ROLE)
                        .and(RoleTableDef.ROLE.NAME.like(qry.getName(), StrUtil.isNotBlank(qry.getName())))
                        .and(RoleTableDef.ROLE.CODE.eq(qry.getCode(), StrUtil.isNotBlank(qry.getCode())))
                        .and(RoleTableDef.ROLE.STATUS.eq(qry.getStatus(), qry.getStatus() != null))
                        .and(RoleTableDef.ROLE.CREATE_TIME.between(qry.getCreateTime(), qry.getCreateTime() != null))
                        .orderBy(RoleTableDef.ROLE.SORT.asc(),RoleTableDef.ROLE.ID.desc())
                        .pageAs(qry.buildPage(), Role.class)
        );
    }

    @Override
    public void updateStatus(Long id, Boolean status) {
        UpdateChain.of(roleMapper)
                .set(RoleTableDef.ROLE.STATUS, status)
                .where(RoleTableDef.ROLE.ID.eq(id))
                .update();
    }

    @Override
    public List<Role> getEnableList() {
        return QueryChain.of(roleMapper)
                .from(RoleTableDef.ROLE)
                .and(RoleTableDef.ROLE.STATUS.eq(Boolean.TRUE))
                .orderBy(RoleTableDef.ROLE.SORT.asc(),RoleTableDef.ROLE.ID.desc())
                .listAs(Role.class);
    }
}
