package com.xgblack.cool.module.system.executor.permission.query;

import com.xgblack.cool.module.system.domain.gateway.PermissionGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionMenuIdsByRoleIdQryExe {

    private final PermissionGateway gateway;

    public Set<Long> execute(Long roleId) {
        return gateway.getMenuIdsByRoleId(roleId);
    }
}
