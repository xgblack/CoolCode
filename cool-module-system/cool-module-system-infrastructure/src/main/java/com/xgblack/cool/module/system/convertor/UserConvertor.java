package com.xgblack.cool.module.system.convertor;

import com.xgblack.cool.framework.common.convertor.Convertor;
import com.xgblack.cool.module.system.domain.user.User;
import com.xgblack.cool.module.system.dto.user.UserAddCmd;
import com.xgblack.cool.module.system.dto.user.UserEditCmd;
import com.xgblack.cool.module.system.dto.user.clientobject.UserCO;
import com.xgblack.cool.module.system.dto.user.clientobject.UserProfileDTO;
import com.xgblack.cool.module.system.gateway.database.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Mapper
public interface UserConvertor extends Convertor<UserCO, User, UserDO> {
    UserConvertor INSTANCE = Mappers.getMapper(UserConvertor.class);

    User toEntity(UserAddCmd cmd);
    User toEntity(UserEditCmd cmd);

    UserProfileDTO convertEntity2DTO(User user);
}
