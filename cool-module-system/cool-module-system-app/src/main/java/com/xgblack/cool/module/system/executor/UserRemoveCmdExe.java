package com.xgblack.cool.module.system.executor;

import com.xgblack.cool.module.system.domain.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRemoveCmdExe {
    private final UserGateway gateway;


    public void execute(Long id) {
        gateway.delete(id);
    }
}
