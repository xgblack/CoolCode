package com.xgblack.cool.module.system.service;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.api.RoleServiceI;
import com.xgblack.cool.module.system.dto.permission.RoleAddCmd;
import com.xgblack.cool.module.system.dto.permission.RoleEditCmd;
import com.xgblack.cool.module.system.dto.permission.RoleEditStatusCmd;
import com.xgblack.cool.module.system.dto.permission.RolePageQry;
import com.xgblack.cool.module.system.dto.permission.clientobject.RoleCO;
import com.xgblack.cool.module.system.dto.permission.clientobject.RoleSimpleCO;
import com.xgblack.cool.module.system.executor.permission.RoleAddCmdExe;
import com.xgblack.cool.module.system.executor.permission.RoleEditCmdExe;
import com.xgblack.cool.module.system.executor.permission.RoleRemoveCmdExe;
import com.xgblack.cool.module.system.executor.permission.query.RoleByIdQryExe;
import com.xgblack.cool.module.system.executor.permission.query.RolePageQryExe;
import com.xgblack.cool.module.system.executor.permission.query.RoleSimpleListQryExe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleServiceI {

    private final RoleAddCmdExe roleAddCmdExe;
    private final RoleByIdQryExe roleByIdQryExe;
    private final RoleRemoveCmdExe roleRemoveCmdExe;
    private final RoleEditCmdExe roleEditCmdExe;
    private final RolePageQryExe rolePageQryExe;
    private final RoleSimpleListQryExe roleSimpleListQryExe;


    @Override
    public Long add(RoleAddCmd cmd) {
        return roleAddCmdExe.execute(cmd);
    }

    @Override
    public void edit(RoleEditCmd cmd) {
        roleEditCmdExe.execute(cmd);
    }

    @Override
    public void editStatus(RoleEditStatusCmd cmd) {

    }

    @Override
    public void remove(Long id) {
        roleRemoveCmdExe.execute(id);
    }

    @Override
    public PageResult<RoleCO> page(RolePageQry qry) {
        return rolePageQryExe.execute(qry);
    }

    @Override
    public RoleCO detail(Long id) {
        return roleByIdQryExe.execute(id);
    }

    @Override
    public List<RoleSimpleCO> getSimpleList() {
        return roleSimpleListQryExe.execute();
    }
}
