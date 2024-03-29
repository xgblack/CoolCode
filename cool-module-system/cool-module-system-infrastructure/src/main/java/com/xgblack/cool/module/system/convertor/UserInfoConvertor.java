package com.xgblack.cool.module.system.convertor;

import com.xgblack.cool.module.system.gateway.database.dataobject.UserDO;
import com.xgblack.cool.framework.security.dto.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Mapper
public interface UserInfoConvertor {
    UserInfoConvertor INSTANCE = Mappers.getMapper(UserInfoConvertor.class);

    SysUser convertDO2DTO(UserDO data);
}
