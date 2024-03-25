package com.xgblack.cool.module.system.service;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.api.PostServiceI;
import com.xgblack.cool.module.system.dto.company.post.PostAddCmd;
import com.xgblack.cool.module.system.dto.company.post.PostEditCmd;
import com.xgblack.cool.module.system.dto.company.post.PostPageQry;
import com.xgblack.cool.module.system.dto.company.post.clientobject.PostCO;
import com.xgblack.cool.module.system.dto.company.post.clientobject.PostSimpleCO;
import com.xgblack.cool.module.system.executor.company.post.PostAddCmdExe;
import com.xgblack.cool.module.system.executor.company.post.PostEditCmdExe;
import com.xgblack.cool.module.system.executor.company.post.PostRemoveCmdExe;
import com.xgblack.cool.module.system.executor.company.post.query.PostByIdQryExe;
import com.xgblack.cool.module.system.executor.company.post.query.PostPageQryExe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostServiceI {

    private final PostAddCmdExe postAddCmdExe;
    private final PostEditCmdExe postEditCmdExe;
    private final PostRemoveCmdExe postRemoveCmdExe;
    private final PostByIdQryExe postByIdQryExe;
    private final PostPageQryExe postPageQryExe;

    @Override
    public Long add(PostAddCmd cmd) {
        return postAddCmdExe.execute(cmd);
    }

    @Override
    public void edit(PostEditCmd cmd) {
        postEditCmdExe.execute(cmd);
    }

    @Override
    public void remove(Long id) {
        postRemoveCmdExe.execute(id);
    }

    @Override
    public PostCO detail(Long id) {
        return postByIdQryExe.execute(id);
    }

    @Override
    public PageResult<PostCO> page(PostPageQry qry) {
        return postPageQryExe.execute(qry);
    }

    @Override
    public List<PostSimpleCO> getSimpleList() {
        return List.of();
    }
}
