/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xgblack.framework.security.core.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgblack.cool.framework.common.response.Response;
import com.xgblack.cool.framework.common.utils.response.CoolRespUtils;
import com.xgblack.framework.security.constans.CommonConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.PrintWriter;

/**
 * @author lengleng
 * @date 2019/2/1
 *
 * 客户端异常处理 AuthenticationException 不同细化异常处理
 * TODO
 */
@RequiredArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	private final MessageSource messageSource;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {
		response.setCharacterEncoding(CommonConstants.UTF8);
		response.setContentType(CommonConstants.CONTENT_TYPE);
		Response result = CoolRespUtils.fail();
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		if (authException != null) {
			result.setPayload(authException.getMessage());
		}


		// 针对令牌过期返回特殊的 424
		if (authException instanceof InvalidBearerTokenException || authException instanceof InsufficientAuthenticationException) {
			response.setStatus(HttpStatus.FAILED_DEPENDENCY.value());
			result.setPayload(this.messageSource.getMessage("OAuth2ResourceOwnerBaseAuthenticationProvider.tokenExpired",
					null, LocaleContextHolder.getLocale()));
		}
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}

}
