package com.xgblack.cool.module.system.executor.company.dept.query;

import com.xgblack.cool.module.system.convertor.DeptConvertor;
import com.xgblack.cool.module.system.domain.gateway.DeptGateway;
import com.xgblack.cool.module.system.dto.company.dept.clientobject.DeptSimpleCO;
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
public class DeptSimpleListQryExe {

    private final DeptGateway gateway;

    public List<DeptSimpleCO> execute() {
        return DeptConvertor.INSTANCE.convertEntity2SimpleCOList(gateway.getEnableList());
    }

}
