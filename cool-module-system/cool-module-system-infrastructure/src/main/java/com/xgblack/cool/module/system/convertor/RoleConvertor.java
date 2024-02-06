package com.xgblack.cool.module.system.convertor;

import com.xgblack.cool.framework.common.convertor.Convertor;
import com.xgblack.cool.module.system.domain.permission.Role;
import com.xgblack.cool.module.system.dto.permission.clientobject.RoleCO;
import com.xgblack.cool.module.system.gateway.database.dataobject.RoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Mapper
public interface RoleConvertor extends Convertor<RoleCO, Role, RoleDO> {
    RoleConvertor INSTANCE = Mappers.getMapper(RoleConvertor.class);


}
