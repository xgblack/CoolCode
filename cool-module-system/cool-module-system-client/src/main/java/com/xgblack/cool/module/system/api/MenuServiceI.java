package com.xgblack.cool.module.system.api;

import com.xgblack.cool.module.system.dto.permission.MenuAddCmd;
import com.xgblack.cool.module.system.dto.permission.MenuEditCmd;
import com.xgblack.cool.module.system.dto.permission.MenuListQry;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuCO;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuSimpleCO;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface MenuServiceI {

    Long add(MenuAddCmd cmd);

    void edit(MenuEditCmd cmd);

    void remove(Long id);

    List<MenuCO> list(MenuListQry qry);

    MenuCO detail(Long id);

    List<MenuSimpleCO> getSimpleListByTenant();
}
