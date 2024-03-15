package com.xgblack.cool.module.system.convertor;

import com.xgblack.cool.framework.common.convertor.Convertor;
import com.xgblack.cool.module.system.domain.company.post.Post;
import com.xgblack.cool.module.system.dto.company.post.PostAddCmd;
import com.xgblack.cool.module.system.dto.company.post.PostEditCmd;
import com.xgblack.cool.module.system.dto.company.post.clientobject.PostCO;
import com.xgblack.cool.module.system.dto.company.post.clientobject.PostSimpleCO;
import com.xgblack.cool.module.system.gateway.database.dataobject.PostDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Mapper
public interface PostConvertor extends Convertor<PostCO, Post, PostDO> {
    PostConvertor INSTANCE = Mappers.getMapper(PostConvertor.class);

    Post toEntity(PostAddCmd cmd);

    Post toEntity(PostEditCmd cmd);

    PostSimpleCO convertEntity2SimpleCO(Post post);

    List<PostSimpleCO> convertEntity2SimpleCOList(List<Post> list);
}
