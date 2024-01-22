package com.xgblack.cool.framework.security.service;

import com.xgblack.cool.framework.security.dto.SysOauthClientDetails;

import java.util.List;

/**
 * 客户端信息接口
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface RemoteClientDetailsService {

    /**
     * 通过clientId 查询客户端信息
     * @param clientId 客户端id
     * @return ClientDetails
     */
    SysOauthClientDetails getClientDetailsById(String clientId);

    /**
     * 查询全部客户端
     * @return ClientDetails
     */
    List<SysOauthClientDetails> listClientDetails();

}
