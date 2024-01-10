package com.xgblack.cool.module.system.gateway;

import com.mybatisflex.core.query.QueryChain;
import com.xgblack.cool.module.system.convertor.OauthClientConvertor;
import com.xgblack.cool.module.system.domain.gateway.OauthClientGateway;
import com.xgblack.cool.module.system.gateway.database.dataobject.OauthClientDO;
import com.xgblack.cool.module.system.gateway.database.mapper.OauthClientMapper;
import com.xgblack.framework.security.dto.SysOauthClientDetails;
import com.xgblack.framework.security.service.RemoteClientDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.xgblack.cool.module.system.gateway.database.dataobject.table.OauthClientTableDef.OAUTH_CLIENT;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OauthClientGatewayImpl implements OauthClientGateway, RemoteClientDetailsService {


    private final OauthClientMapper oauthClientMapper;


    @Override
    public SysOauthClientDetails getClientDetailsById(String clientId) {
        OauthClientDO oauthClientDO = QueryChain.of(oauthClientMapper)
                .from(OAUTH_CLIENT)
                .where(OAUTH_CLIENT.CLIENT_ID.eq(clientId))
                .one();
        return OauthClientConvertor.INSTANCE.convertDO2DTO(oauthClientDO);
    }

    @Override
    public List<SysOauthClientDetails> listClientDetails() {
        return OauthClientConvertor.INSTANCE.convertDO2DTOList(oauthClientMapper.selectAll());
    }
}
