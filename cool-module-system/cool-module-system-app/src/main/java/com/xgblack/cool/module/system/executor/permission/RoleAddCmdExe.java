package com.xgblack.cool.module.system.executor.permission;

import com.xgblack.cool.module.system.convertor.RoleConvertor;
import com.xgblack.cool.module.system.convertor.StudentConvertor;
import com.xgblack.cool.module.system.domain.gateway.RoleGateway;
import com.xgblack.cool.module.system.domain.gateway.StudentGateway;
import com.xgblack.cool.module.system.dto.permission.RoleAddCmd;
import com.xgblack.cool.module.system.dto.student.StudentAddCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleAddCmdExe {

    private final RoleGateway gateway;


    public void execute(RoleAddCmd cmd) {
        gateway.create(RoleConvertor.INSTANCE.toEntity(cmd));
    }

}
