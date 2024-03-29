package com.xgblack.cool.framework.security.core.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.dromara.hutool.core.map.MapUtil;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.endpoint.PkceParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * OAuth2 端点工具
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@UtilityClass
public class CoolOAuth2EndpointUtils {

    public final String ACCESS_TOKEN_REQUEST_ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

    /**
     * 解析Http请求参数
     * <p>认证方式同spring官方方式修改，认证方式改为form-data，不再支持query parameter，
     * 自spring-boot3.2.1版本起修改，参见{@link org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2EndpointUtils#getFormParameters(HttpServletRequest)}</p>
     *
     * @param request
     * @return
     */
    public MultiValueMap<String, String> getParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameterMap.forEach((key, values) -> {
            String queryString = StringUtils.hasText(request.getQueryString()) ? request.getQueryString() : "";
            // If not query parameter then it's a form parameter
            if (!queryString.contains(key) && values.length > 0) {
                for (String value : values) {
                    parameters.add(key, value);
                }
            }
        });
        return parameters;
    }

    /**
     * 判断是否为 授权码+PKCE 模式
     * @param request
     * @return
     */
    public boolean matchesPkceTokenRequest(HttpServletRequest request) {
        return AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(request.getParameter(OAuth2ParameterNames.GRANT_TYPE))
                && request.getParameter(OAuth2ParameterNames.CODE) != null
                && request.getParameter(PkceParameterNames.CODE_VERIFIER) != null;
    }

    /**
     * 抛出 OAuth 2.0 异常
     * @param errorCode 异常码
     * @param parameterName 参数名
     * @param errorUri 错误链接
     */
    public void throwError(String errorCode, String parameterName, String errorUri) {
        OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 Parameter: " + parameterName, errorUri);
        throw new OAuth2AuthenticationException(error);
    }

    /**
     * 格式化输出token 信息
     * @param authentication 用户认证信息
     * @param claims 扩展信息
     * @return
     * @throws IOException
     */
    public OAuth2AccessTokenResponse sendAccessTokenResponse(OAuth2Authorization authentication, Map<String, Object> claims) {

        OAuth2AccessToken accessToken = authentication.getAccessToken().getToken();
        OAuth2RefreshToken refreshToken = authentication.getRefreshToken().getToken();

        OAuth2AccessTokenResponse.Builder builder = OAuth2AccessTokenResponse.withToken(accessToken.getTokenValue())
                .tokenType(accessToken.getTokenType())
                .scopes(accessToken.getScopes());
        if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
            builder.expiresIn(ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
        }
        if (refreshToken != null) {
            builder.refreshToken(refreshToken.getTokenValue());
        }

        if (MapUtil.isNotEmpty(claims)) {
            builder.additionalParameters(claims);
        }
        return builder.build();
    }


}
