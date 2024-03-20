package com.xgblack.cool.module.system.convertor;

import com.xgblack.cool.framework.common.convertor.Convertor;
import com.xgblack.cool.module.system.domain.permission.Menu;
import com.xgblack.cool.module.system.dto.permission.MenuAddCmd;
import com.xgblack.cool.module.system.dto.permission.MenuEditCmd;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuCO;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuSimpleCO;
import com.xgblack.cool.module.system.gateway.database.dataobject.MenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Mapper
public interface MenuConvertor extends Convertor<MenuCO, Menu, MenuDO> {
    MenuConvertor INSTANCE = Mappers.getMapper(MenuConvertor.class);

    Menu toEntity(MenuAddCmd cmd);

    Menu toEntity(MenuEditCmd cmd);

    MenuSimpleCO convertEntity2SimpleCO(Menu menu);

    List<MenuSimpleCO> convertEntity2SimpleCOList(List<Menu> list);

}
