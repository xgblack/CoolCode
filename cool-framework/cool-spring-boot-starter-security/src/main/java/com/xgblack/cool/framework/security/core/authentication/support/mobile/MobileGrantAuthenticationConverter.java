package com.xgblack.cool.framework.security.core.authentication.support.mobile;

import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.cool.framework.security.core.authentication.support.base.OAuth2ResourceOwnerBaseAuthenticationConverter;
import com.xgblack.cool.framework.security.core.utils.CoolOAuth2EndpointUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * 短信登录转换器
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public class MobileGrantAuthenticationConverter extends OAuth2ResourceOwnerBaseAuthenticationConverter<MobileGrantAuthenticationToken> {

    /**
     * 是否支持此convert
     *
     * @param grantType 授权类型
     * @return
     */
    @Override
    public boolean support(String grantType) {
        return SecurityConstants.MOBILE.equals(grantType);
    }

    @Override
    public MobileGrantAuthenticationToken buildToken(Authentication clientPrincipal, Set requestedScopes,
                                                     Map additionalParameters) {
        return new MobileGrantAuthenticationToken(new AuthorizationGrantType(SecurityConstants.MOBILE), clientPrincipal, requestedScopes, additionalParameters);
    }

    /**
     * 校验扩展参数
     *
     * @param request 参数列表
     */
    @Override
    public void checkParams(HttpServletRequest request) {
        MultiValueMap<String, String> parameters = CoolOAuth2EndpointUtils.getParameters(request);
        // PHONE (REQUIRED)
        String phone = parameters.getFirst(SecurityConstants.SMS_PARAMETER_NAME);

        if (!StringUtils.hasText(phone) || parameters.get(SecurityConstants.SMS_PARAMETER_NAME).size() != 1) {
            CoolOAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_REQUEST, SecurityConstants.SMS_PARAMETER_NAME, CoolOAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }
    }

}
