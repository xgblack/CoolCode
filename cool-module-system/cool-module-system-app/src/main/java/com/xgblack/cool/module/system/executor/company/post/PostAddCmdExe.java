package com.xgblack.cool.module.system.executor.company.post;

import com.xgblack.cool.module.system.convertor.PostConvertor;
import com.xgblack.cool.module.system.domain.gateway.PostGateway;
import com.xgblack.cool.module.system.dto.company.post.PostAddCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */


@Slf4j
@Component
@RequiredArgsConstructor
public class PostAddCmdExe {

    private final PostGateway gateway;

    public Long execute(PostAddCmd cmd) {
        return gateway.insert(PostConvertor.INSTANCE.toEntity(cmd));
    }
}
