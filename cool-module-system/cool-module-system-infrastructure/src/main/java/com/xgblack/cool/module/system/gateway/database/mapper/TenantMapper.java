package com.xgblack.cool.module.system.gateway.database.mapper;

import com.xgblack.cool.module.system.gateway.database.dataobject.TenantDO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户表 映射层。
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Mapper
public interface TenantMapper extends BaseMapper<TenantDO> {


}
