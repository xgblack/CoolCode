package com.xgblack.cool.module.system.executor.permission.query;

import com.xgblack.cool.module.system.convertor.MenuConvertor;
import com.xgblack.cool.module.system.domain.gateway.MenuGateway;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuByIdQryExe {

    private final MenuGateway gateway;

    public MenuCO execute(Long id) {
        return MenuConvertor.INSTANCE.convertEntity2CO(gateway.getById(id));
    }

}
