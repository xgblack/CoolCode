package com.xgblack.cool.module.system.api;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.dto.company.post.PostAddCmd;
import com.xgblack.cool.module.system.dto.company.post.PostEditCmd;
import com.xgblack.cool.module.system.dto.company.post.PostPageQry;
import com.xgblack.cool.module.system.dto.company.post.clientobject.PostCO;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface PostServiceI {

    void add(PostAddCmd cmd);

    void edit(PostEditCmd cmd);

    void remove(Long id);

    PostCO detail(Long id);

    PageResult<PostCO> page(PostPageQry qry);

}
