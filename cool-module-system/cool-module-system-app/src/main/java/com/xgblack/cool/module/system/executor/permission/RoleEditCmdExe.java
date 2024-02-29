
package com.xgblack.cool.module.system.executor.permission;


import com.xgblack.cool.module.system.convertor.RoleConvertor;
import com.xgblack.cool.module.system.domain.gateway.RoleGateway;
import com.xgblack.cool.module.system.dto.permission.RoleEditCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleEditCmdExe {

    private final RoleGateway gateway;


    public void execute(RoleEditCmd cmd) {
        gateway.update(RoleConvertor.INSTANCE.toEntity(cmd));
    }
}
