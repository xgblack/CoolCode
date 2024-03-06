package com.xgblack.cool.module.system.executor.company.dept.query;

import com.xgblack.cool.module.system.convertor.DeptConvertor;
import com.xgblack.cool.module.system.domain.gateway.DeptGateway;
import com.xgblack.cool.module.system.dto.company.dept.clientobject.DeptCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class DeptByIdQryExe {

    private final DeptGateway gateway;

    public DeptCO execute(Long id) {
        return DeptConvertor.INSTANCE.convertEntity2CO(gateway.getById(id));
    }

}
