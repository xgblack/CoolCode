package com.xgblack.cool.module.system.executor.permission.query;

import com.xgblack.cool.module.system.convertor.MenuConvertor;
import com.xgblack.cool.module.system.domain.gateway.MenuGateway;
import com.xgblack.cool.module.system.dto.permission.MenuListQry;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuListQryExe {

    private final MenuGateway gateway;

    public List<MenuCO> execute(MenuListQry qry) {
        return MenuConvertor.INSTANCE.convertEntity2COList(gateway.getList(qry));
    }
}
