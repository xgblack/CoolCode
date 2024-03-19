package com.xgblack.cool.module.system.executor.permission.query;

import com.xgblack.cool.module.system.convertor.RoleConvertor;
import com.xgblack.cool.module.system.domain.gateway.RoleGateway;
import com.xgblack.cool.module.system.dto.permission.clientobject.RoleSimpleCO;
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
public class RoleSimpleListQryExe {

    private final RoleGateway gateway;

    public List<RoleSimpleCO> execute() {
        return RoleConvertor.INSTANCE.convertEntity2SimpleCOList(gateway.getEnableList());
    }
}
