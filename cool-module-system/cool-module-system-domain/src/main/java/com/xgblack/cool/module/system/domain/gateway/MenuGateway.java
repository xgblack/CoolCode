package com.xgblack.cool.module.system.domain.gateway;

import com.xgblack.cool.module.system.domain.permission.Menu;
import com.xgblack.cool.module.system.dto.permission.MenuListQry;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuCO;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface MenuGateway {

    void insert(Menu entity);

    void update(Menu entity);

    void delete(Long id);

    List<MenuCO> getList(MenuListQry qry);

    MenuCO getById(Long id);
}
