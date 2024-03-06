package com.xgblack.cool.module.system.executor.permission;

import com.xgblack.cool.module.system.domain.gateway.MenuGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuRemoveCmdExe {

    private final MenuGateway gateway;

    public void execute(Long id) {
        gateway.delete(id);
    }
}
