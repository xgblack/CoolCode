package com.xgblack.cool.framework.security.core.service;

import com.xgblack.cool.framework.common.constants.CacheConstants;
import com.xgblack.cool.framework.security.dto.UserInfo;
import com.xgblack.cool.framework.security.service.RemoteUserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * 用户详细信息Service
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class CoolUserDetailsServiceImpl implements CoolUserDetailsService {

    @Resource
    private RemoteUserService remoteUserService;

    private final CacheManager cacheManager;

    /**
     * 用户名密码登录
     * @param username 用户名
     * @return
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        //TODO 优化redis操作
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return (LoginUser) cache.get(username).get();
        }

        UserInfo info = remoteUserService.info(username, null);
        UserDetails userDetails = getUserDetails(info);
        if (cache != null) {
            cache.put(username, userDetails);
        }
        return userDetails;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

}
