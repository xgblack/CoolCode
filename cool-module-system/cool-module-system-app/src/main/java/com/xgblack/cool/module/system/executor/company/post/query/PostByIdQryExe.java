package com.xgblack.cool.module.system.executor.company.post.query;

import com.xgblack.cool.module.system.convertor.PostConvertor;
import com.xgblack.cool.module.system.domain.gateway.PostGateway;
import com.xgblack.cool.module.system.dto.company.post.clientobject.PostCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class PostByIdQryExe {

    private final PostGateway gateway;

    public PostCO execute(Long id) {
        return PostConvertor.INSTANCE.convertEntity2CO(gateway.getById(id));
    }

}
