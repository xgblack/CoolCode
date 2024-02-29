
package com.xgblack.cool.module.system.executor.permission;


import com.xgblack.cool.module.system.domain.gateway.RoleGateway;
import com.xgblack.cool.module.system.domain.gateway.StudentGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleRemoveCmdExe {

    private final RoleGateway gateway;


    public void execute(Long id) {
        gateway.delete(id);
    }
}
