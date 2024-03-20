package com.xgblack.cool.module.system.gateway;

import com.mybatisflex.core.query.QueryChain;
import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.convertor.PostConvertor;
import com.xgblack.cool.module.system.domain.company.post.Post;
import com.xgblack.cool.module.system.domain.gateway.PostGateway;
import com.xgblack.cool.module.system.dto.company.post.PostPageQry;
import com.xgblack.cool.module.system.gateway.database.dataobject.table.PostTableDef;
import com.xgblack.cool.module.system.gateway.database.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class PostGatewayImpl implements PostGateway {

    private final PostMapper postMapper;

    private final PostConvertor convertor = PostConvertor.INSTANCE;


    @Override
    public void insert(Post entity) {
        postMapper.insertSelective(convertor.toDataObject(entity));
    }

    @Override
    public void update(Post entity) {
        postMapper.update(convertor.toDataObject(entity));
    }

    @Override
    public void delete(Long id) {
        postMapper.deleteById(id);
    }

    @Override
    public Post getById(Long id) {
        return postMapper.selectOneWithRelationsByIdAs(id, Post.class);
    }

    @Override
    public PageResult<Post> page(PostPageQry qry) {
        return PageResult.of(QueryChain.of(postMapper)
                .from(PostTableDef.POST)
                .and(PostTableDef.POST.CODE.like(qry.getCode(), StrUtil.isNotBlank(qry.getCode())))
                .and(PostTableDef.POST.NAME.like(qry.getName(), StrUtil.isNotBlank(qry.getName())))
                .and(PostTableDef.POST.STATUS.eq(qry.getStatus(), Objects.nonNull(qry.getStatus())))
                .orderBy(PostTableDef.POST.SORT.asc(), PostTableDef.POST.ID.asc())
                .pageAs(qry.buildPage(), Post.class));
    }

    @Override
    public List<Post> getPostsByIds(Collection<Long> postIds) {
        return QueryChain.of(postMapper)
                .from(PostTableDef.POST)
                .and(PostTableDef.POST.ID.in(postIds))
                .orderBy(PostTableDef.POST.SORT.asc(), PostTableDef.POST.ID.asc())
                .listAs(Post.class);
    }

    @Override
    public List<Post> getEnableList() {
        return QueryChain.of(postMapper)
                .from(PostTableDef.POST)
                .and(PostTableDef.POST.STATUS.eq(Boolean.TRUE))
                .orderBy(PostTableDef.POST.SORT.asc(), PostTableDef.POST.ID.asc())
                .listAs(Post.class);
    }
}
