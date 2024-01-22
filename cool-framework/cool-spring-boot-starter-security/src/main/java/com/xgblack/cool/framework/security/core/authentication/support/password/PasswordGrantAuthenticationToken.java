package com.xgblack.cool.framework.security.core.authentication.support.password;

import com.xgblack.cool.framework.security.core.authentication.support.base.OAuth2ResourceOwnerBaseAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.Map;
import java.util.Set;

/**
 * 密码授权token信息
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public class PasswordGrantAuthenticationToken extends OAuth2ResourceOwnerBaseAuthenticationToken {

    public PasswordGrantAuthenticationToken(AuthorizationGrantType authorizationGrantType, Authentication clientPrincipal, Set<String> scopes, Map<String, Object> additionalParameters) {
        super(authorizationGrantType, clientPrincipal, scopes, additionalParameters);
    }

}
