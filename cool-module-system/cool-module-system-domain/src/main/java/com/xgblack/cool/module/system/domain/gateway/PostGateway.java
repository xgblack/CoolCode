package com.xgblack.cool.module.system.domain.gateway;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.domain.company.post.Post;
import com.xgblack.cool.module.system.dto.company.post.PostPageQry;

import java.util.Collection;
import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface PostGateway {

    Long insert(Post entity);

    void update(Post entity);

    void delete(Long id);

    Post getById(Long id);

    PageResult<Post> page(PostPageQry qry);

    List<Post> getPostsByIds(Collection<Long> postIds);

    List<Post> getEnableList();
}
