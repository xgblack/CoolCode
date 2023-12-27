package com.xgblack.cool.framework.response;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 启动
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
@Component
public class Init {

    @PostConstruct
    public void init() {
        log.info("CC Response: started");
    }
}
