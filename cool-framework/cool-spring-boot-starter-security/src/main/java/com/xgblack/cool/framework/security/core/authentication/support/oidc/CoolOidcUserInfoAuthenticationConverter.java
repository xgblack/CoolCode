package com.xgblack.cool.framework.security.core.authentication.support.oidc;

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
public class CoolOidcUserInfoAuthenticationConverter implements AuthenticationConverter {

    private final CoolOidcUserInfoService oidcUserInfoService;


    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //查询用户信息
        CoolOidcUserInfo myOidcUserInfo = oidcUserInfoService.loadUser(authentication.getName());

        //返回自定义的OidcUserInfoAuthenticationToken
        return new OidcUserInfoAuthenticationToken(authentication, myOidcUserInfo);
    }

}
