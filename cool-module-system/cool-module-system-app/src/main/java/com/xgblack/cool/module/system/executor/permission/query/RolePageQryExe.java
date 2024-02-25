package com.xgblack.cool.module.system.executor.permission.query;


import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.convertor.RoleConvertor;
import com.xgblack.cool.module.system.domain.gateway.RoleGateway;
import com.xgblack.cool.module.system.dto.permission.RolePageQry;
import com.xgblack.cool.module.system.dto.permission.clientobject.RoleCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RolePageQryExe {

    private final RoleGateway gateway;

    public PageResult<RoleCO> execute(RolePageQry qry) {
        return RoleConvertor.INSTANCE.convertEntity2COPageResult(gateway.getPage(qry));
    }
}
