package com.xgblack.framework.security.auth.support.core;

import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.framework.security.core.service.LoginUser;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

/**
 * token 输出增强
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
public class CustomOAuth2TokenCustomizer implements OAuth2TokenCustomizer<OAuth2TokenClaimsContext> {

	/**
	 * 自定义OAuth 2.0令牌属性。
	 * @param context 包含OAuth 2.0令牌属性的上下文
	 */
	@Override
	public void customize(OAuth2TokenClaimsContext context) {
		OAuth2TokenClaimsSet.Builder claims = context.getClaims();
		claims.claim(SecurityConstants.DETAILS_LICENSE, SecurityConstants.PROJECT_LICENSE);
		String clientId = context.getAuthorizationGrant().getName();
		claims.claim(SecurityConstants.CLIENT_ID, clientId);
		// 客户端模式不返回具体用户信息
		if (SecurityConstants.CLIENT_CREDENTIALS.equals(context.getAuthorizationGrantType().getValue())) {
			return;
		}

		LoginUser loginUser = (LoginUser) context.getPrincipal().getPrincipal();
		claims.claim(SecurityConstants.DETAILS_USER, loginUser);
		claims.claim(SecurityConstants.DETAILS_USER_ID, loginUser.getId());
		claims.claim(SecurityConstants.USERNAME, loginUser.getUsername());
	}

}
