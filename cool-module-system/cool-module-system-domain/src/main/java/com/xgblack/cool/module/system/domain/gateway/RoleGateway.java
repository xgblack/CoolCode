package com.xgblack.cool.module.system.domain.gateway;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.domain.permission.Role;
import com.xgblack.cool.module.system.dto.permission.RolePageQry;

import java.util.Collection;
import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface RoleGateway {

    void insert(Role role);

    void delete(Long id);

    void update(Role role);

    Role getById(Long id);

    List<Role> getRolesByIds(Collection<Long> ids);

    PageResult<Role> getPage(RolePageQry qry);

    void updateStatus(Long id, Boolean status);

    List<Role> getEnableList();
}
