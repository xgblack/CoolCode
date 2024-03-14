package com.xgblack.cool.module.system.executor.company.post;

import com.xgblack.cool.module.system.convertor.PostConvertor;
import com.xgblack.cool.module.system.domain.gateway.PostGateway;
import com.xgblack.cool.module.system.dto.company.post.PostEditCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */


@Slf4j
@Component
@RequiredArgsConstructor
public class PostEditCmdExe {

    private final PostGateway gateway;

    public void execute(PostEditCmd cmd) {
        gateway.update(PostConvertor.INSTANCE.toEntity(cmd));
    }

}
