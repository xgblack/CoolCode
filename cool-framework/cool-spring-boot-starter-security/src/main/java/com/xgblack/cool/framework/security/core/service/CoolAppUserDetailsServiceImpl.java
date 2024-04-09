package com.xgblack.cool.framework.security.core.service;

import com.xgblack.cool.framework.common.constants.CacheConstants;
import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.cool.framework.security.dto.LoginUser;
import com.xgblack.cool.framework.security.dto.UserInfo;
import com.xgblack.cool.framework.security.service.RemoteUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详细信息
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@RequiredArgsConstructor
public class CoolAppUserDetailsServiceImpl implements CoolUserDetailsService {

    private final RemoteUserService remoteUserService;

    private final CacheManager cacheManager;

    /**
     * 手机号登录
     * @param phone 手机号
     * @return
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String phone) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(phone) != null) {
            return (LoginUser) cache.get(phone).get();
        }

        UserInfo info = remoteUserService.info(null, phone);

        UserDetails userDetails = getUserDetails(info);
        if (cache != null) {
            cache.put(phone, userDetails);
        }
        return userDetails;
    }

    /**
     * check-token 使用
     * @param loginUser user
     * @return
     */
    @Override
    public UserDetails loadUserByUser(LoginUser loginUser) {
        return this.loadUserByUsername(loginUser.getPhone());
    }

    @Override
    public UserDetails loadUserById(Long userId) {
        // TODO
        return null;
    }

    /**
     * 是否支持此客户端校验
     * @param clientId 目标客户端
     * @return true/false
     */
    @Override
    public boolean support(String clientId, String grantType) {
        return SecurityConstants.MOBILE.equals(grantType);
    }

}
