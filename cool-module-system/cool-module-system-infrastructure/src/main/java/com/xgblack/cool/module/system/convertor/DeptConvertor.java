package com.xgblack.cool.module.system.convertor;

import com.xgblack.cool.framework.common.convertor.Convertor;
import com.xgblack.cool.module.system.domain.company.dept.Dept;
import com.xgblack.cool.module.system.dto.company.dept.DeptAddCmd;
import com.xgblack.cool.module.system.dto.company.dept.DeptEditCmd;
import com.xgblack.cool.module.system.dto.company.dept.clientobject.DeptCO;
import com.xgblack.cool.module.system.gateway.database.dataobject.DeptDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Mapper
public interface DeptConvertor extends Convertor<DeptCO, Dept, DeptDO> {

    DeptConvertor INSTANCE = Mappers.getMapper(DeptConvertor.class);

    Dept toEntity(DeptAddCmd cmd);

    Dept toEntity(DeptEditCmd cmd);

}
