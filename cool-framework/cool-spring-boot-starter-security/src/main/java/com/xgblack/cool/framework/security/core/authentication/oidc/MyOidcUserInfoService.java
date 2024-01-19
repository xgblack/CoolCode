package com.xgblack.cool.framework.security.core.authentication.oidc;

import com.xgblack.cool.framework.security.dto.SysUser;
import com.xgblack.cool.framework.security.dto.UserInfo;
import com.xgblack.cool.framework.security.service.RemoteUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Service
public class MyOidcUserInfoService {
    @Resource
    private RemoteUserService userService;

    public MyOidcUserInfo loadUser(String username) {
        UserInfo info = userService.info(username, null);
        return new MyOidcUserInfo(this.createUser(info.getSysUser()));
    }

    private Map<String, Object> createUser(SysUser sysUser) {
        //TODO: 自定义
        return MyOidcUserInfo.myBuilder()
                .name(sysUser.getNickname())
                .username(sysUser.getUsername())
                .description("11111")
                .status(sysUser.getLocked() ? 0 : 1)
                .phoneNumber(sysUser.getUsername())
                .email(sysUser.getUsername() + "@example.com")
                .profile("https://example.com/" + sysUser.getUsername())
                .address("XXX共和国XX省XX市XX区XXX街XXX号")
                .build()
                .getClaims();
    }
}
