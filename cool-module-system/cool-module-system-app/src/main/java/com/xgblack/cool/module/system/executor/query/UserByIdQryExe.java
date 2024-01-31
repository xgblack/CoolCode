package com.xgblack.cool.module.system.executor.query;

import com.xgblack.cool.module.system.convertor.UserConvertor;
import com.xgblack.cool.module.system.domain.gateway.UserGateway;
import com.xgblack.cool.module.system.dto.user.clientobject.UserCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class UserByIdQryExe {
    private final UserGateway gateway;

    public UserCO execute(Long id) {
        return UserConvertor.INSTANCE.convertEntity2CO(gateway.getById(id));
    }
}
