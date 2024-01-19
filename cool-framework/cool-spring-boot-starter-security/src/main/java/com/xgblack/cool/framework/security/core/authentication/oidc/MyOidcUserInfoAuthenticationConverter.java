package com.xgblack.cool.framework.security.core.authentication.oidc;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@RequiredArgsConstructor
public class MyOidcUserInfoAuthenticationConverter implements AuthenticationConverter {

    private final MyOidcUserInfoService oidcUserInfoService;


    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //查询用户信息
        MyOidcUserInfo myOidcUserInfo = oidcUserInfoService.loadUser(authentication.getName());

        //返回自定义的OidcUserInfoAuthenticationToken
        return new OidcUserInfoAuthenticationToken(authentication, myOidcUserInfo);
    }

}
