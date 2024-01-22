package com.xgblack.cool.module.system.convertor;

import com.xgblack.cool.module.system.gateway.database.dataobject.OauthClientDO;
import com.xgblack.cool.framework.security.dto.SysOauthClientDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Mapper
public interface OauthClientConvertor {

    OauthClientConvertor INSTANCE = Mappers.getMapper(OauthClientConvertor.class);

    SysOauthClientDetails convertDO2DTO(OauthClientDO data);
    List<SysOauthClientDetails> convertDO2DTOList(List<OauthClientDO> list);
}
