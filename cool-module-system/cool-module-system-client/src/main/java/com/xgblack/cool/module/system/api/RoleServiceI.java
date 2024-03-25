package com.xgblack.cool.module.system.api;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.dto.permission.RoleAddCmd;
import com.xgblack.cool.module.system.dto.permission.RoleEditCmd;
import com.xgblack.cool.module.system.dto.permission.RoleEditStatusCmd;
import com.xgblack.cool.module.system.dto.permission.RolePageQry;
import com.xgblack.cool.module.system.dto.permission.clientobject.RoleCO;
import com.xgblack.cool.module.system.dto.permission.clientobject.RoleSimpleCO;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface RoleServiceI {


    Long add(RoleAddCmd cmd);

    void edit(RoleEditCmd cmd);

    void editStatus(RoleEditStatusCmd cmd);

    void remove(Long id);

    PageResult<RoleCO> page(RolePageQry qry);

    RoleCO detail(Long id);

    List<RoleSimpleCO> getSimpleList();
}
