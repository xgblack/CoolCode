package com.xgblack.cool.framework.security.core.authentication.mobile;

import com.xgblack.cool.framework.security.core.constant.OAuth2Constant;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public class MobileGrantAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
    public MobileGrantAuthenticationToken(Authentication clientPrincipal, @Nullable Map<String, Object> additionalParameters) {
        super(new AuthorizationGrantType(OAuth2Constant.GRANT_TYPE_MOBILE), clientPrincipal, additionalParameters);
    }
}
