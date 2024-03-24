package com.xgblack.cool.module.system.gateway;

import com.mybatisflex.core.query.QueryChain;
import com.xgblack.cool.module.system.convertor.MenuConvertor;
import com.xgblack.cool.module.system.domain.gateway.MenuGateway;
import com.xgblack.cool.module.system.domain.permission.Menu;
import com.xgblack.cool.module.system.dto.permission.MenuListQry;
import com.xgblack.cool.module.system.gateway.database.dataobject.MenuDO;
import com.xgblack.cool.module.system.gateway.database.dataobject.table.MenuTableDef;
import com.xgblack.cool.module.system.gateway.database.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuGatewayImpl implements MenuGateway {

    private final MenuMapper menuMapper;
    private final MenuConvertor convertor = MenuConvertor.INSTANCE;

    @Override
    public Long insert(Menu entity) {
        MenuDO menuDO = convertor.toDataObject(entity);
        menuMapper.insertSelective(menuDO);
        return menuDO.getId();
    }

    @Override
    public void update(Menu entity) {
        menuMapper.update(convertor.toDataObject(entity));
    }

    @Override
    public void delete(Long id) {
        menuMapper.deleteById(id);
    }

    @Override
    public List<Menu> getList(MenuListQry qry) {
        return QueryChain.of(menuMapper)
                .from(MenuTableDef.MENU)
                .and(MenuTableDef.MENU.NAME.like(qry.getName(), StrUtil.isNotBlank(qry.getName())))
                .and(MenuTableDef.MENU.STATUS.eq(qry.getStatus(), qry.getStatus() != null))
                .orderBy(MenuTableDef.MENU.SORT.asc(), MenuTableDef.MENU.ID.asc())
                .listAs(Menu.class);
    }

    @Override
    public Menu getById(Long id) {
        return menuMapper.selectOneWithRelationsByIdAs(id, Menu.class);
    }

    @Override
    public List<Menu> getEnableList() {
        return QueryChain.of(menuMapper)
                .from(MenuTableDef.MENU)
                .and(MenuTableDef.MENU.STATUS.eq(Boolean.TRUE))
                .orderBy(MenuTableDef.MENU.SORT.asc(), MenuTableDef.MENU.ID.asc())
                .listAs(Menu.class);
    }
}
