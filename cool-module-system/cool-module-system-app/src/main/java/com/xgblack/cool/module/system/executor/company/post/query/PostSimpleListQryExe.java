package com.xgblack.cool.module.system.executor.company.post.query;

import com.xgblack.cool.module.system.convertor.PostConvertor;
import com.xgblack.cool.module.system.domain.gateway.PostGateway;
import com.xgblack.cool.module.system.dto.company.post.clientobject.PostSimpleCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class PostSimpleListQryExe {

    private final PostGateway gateway;

    public List<PostSimpleCO> execute() {
        return PostConvertor.INSTANCE.convertEntity2SimpleCOList(gateway.getEnableList());
    }
}
