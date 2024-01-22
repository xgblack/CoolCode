package com.xgblack.cool.framework.security.service;

import com.xgblack.cool.framework.security.dto.UserInfo;

/**
 * 用户信息接口
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface RemoteUserService {

    /**
     * 查询用户信息
     * @param username 用户名
     * @param phone 手机号
     * @return UserInfo
     */
    UserInfo info(String username, String phone);

    /**
     * 锁定用户
     * @param username 用户名
     * @return 是否成功
     */
    boolean lockUser(String username);


}
