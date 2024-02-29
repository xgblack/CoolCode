package com.xgblack.cool.module.system.executor.permission.query;


import com.xgblack.cool.module.system.convertor.RoleConvertor;
import com.xgblack.cool.module.system.domain.gateway.RoleGateway;
import com.xgblack.cool.module.system.dto.permission.clientobject.RoleCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleByIdQryExe {

    private final RoleGateway gateway;

    public RoleCO execute(Long id) {
        return RoleConvertor.INSTANCE.convertEntity2CO(gateway.getById(id));
    }
}
