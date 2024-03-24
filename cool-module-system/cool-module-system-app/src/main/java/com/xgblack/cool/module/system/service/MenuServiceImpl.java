package com.xgblack.cool.module.system.service;

import com.xgblack.cool.module.system.api.MenuServiceI;
import com.xgblack.cool.module.system.dto.permission.MenuAddCmd;
import com.xgblack.cool.module.system.dto.permission.MenuEditCmd;
import com.xgblack.cool.module.system.dto.permission.MenuListQry;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuCO;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuSimpleCO;
import com.xgblack.cool.module.system.executor.permission.MenuAddCmdExe;
import com.xgblack.cool.module.system.executor.permission.MenuEditCmdExe;
import com.xgblack.cool.module.system.executor.permission.MenuRemoveCmdExe;
import com.xgblack.cool.module.system.executor.permission.query.MenuByIdQryExe;
import com.xgblack.cool.module.system.executor.permission.query.MenuListQryExe;
import com.xgblack.cool.module.system.executor.permission.query.MenuSimpleListByTenantQryExe;
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
public class MenuServiceImpl implements MenuServiceI {

    private final MenuAddCmdExe menuAddCmdExe;
    private final MenuEditCmdExe menuEditCmdExe;
    private final MenuRemoveCmdExe menuRemoveCmdExe;
    private final MenuListQryExe menuListQryExe;
    private final MenuByIdQryExe menuByIdQryExe;
    private final MenuSimpleListByTenantQryExe menuSimpleListByTenantQryExe;



    @Override
    public Long add(MenuAddCmd cmd) {
        return menuAddCmdExe.execute(cmd);
    }

    @Override
    public void edit(MenuEditCmd cmd) {
        menuEditCmdExe.execute(cmd);
    }

    @Override
    public void remove(Long id) {
        menuRemoveCmdExe.execute(id);
    }

    @Override
    public List<MenuCO> list(MenuListQry qry) {
        return menuListQryExe.execute(qry);
    }

    @Override
    public MenuCO detail(Long id) {
        return menuByIdQryExe.execute(id);
    }

    @Override
    public List<MenuSimpleCO> getSimpleListByTenant() {
        return menuSimpleListByTenantQryExe.execute();
    }
}
