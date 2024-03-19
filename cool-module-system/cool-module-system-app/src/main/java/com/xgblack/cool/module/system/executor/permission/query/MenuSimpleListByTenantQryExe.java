package com.xgblack.cool.module.system.executor.permission.query;

import com.xgblack.cool.module.system.convertor.MenuConvertor;
import com.xgblack.cool.module.system.domain.gateway.MenuGateway;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuSimpleCO;
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
public class MenuSimpleListByTenantQryExe {

    private final MenuGateway gateway;

    public List<MenuSimpleCO> execute(){
        //TODO 过滤租户
        return MenuConvertor.INSTANCE.convertEntity2SimpleCOList(gateway.getEnableList());
    }

}
