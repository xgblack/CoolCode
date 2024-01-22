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

package com.xgblack.cool.framework.security.core.authentication.support.handler;

import cn.hutool.core.util.StrUtil;
import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.cool.framework.common.utils.i18n.MsgUtils;
import com.xgblack.cool.framework.common.utils.response.CoolRespUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录失败 Handler
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
@Component
public class CoolAuthenticationFailureEventHandler implements AuthenticationFailureHandler {

    private final MappingJackson2HttpMessageConverter errorHttpResponseConverter = new MappingJackson2HttpMessageConverter();

    /**
     * 当身份验证尝试失败时调用。
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     *                  request.
     */
    @Override
    @SneakyThrows
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String username = request.getParameter(OAuth2ParameterNames.USERNAME);
        //TODO 登录日志
        log.info("用户：{} 登录失败，异常：{}", username, exception.getLocalizedMessage());
        //SysLog logVo = SysLogUtils.getSysLog();
        //logVo.setTitle("登录失败");
        //logVo.setLogType(LogTypeEnum.ERROR.getType());
        //logVo.setException(exception.getLocalizedMessage());
        // 发送异步日志事件
		/*String startTimeStr = request.getHeader(CommonConstants.REQUEST_START_TIME);
		if (StrUtil.isNotBlank(startTimeStr)) {
			Long startTime = Long.parseLong(startTimeStr);
			Long endTime = System.currentTimeMillis();
			logVo.setTime(endTime - startTime);
		}
		logVo.setCreateBy(username);
		SpringContextHolder.publishEvent(new SysLogEvent(logVo));*/
        // 写出错误信息
        sendErrorResponse(request, response, exception);
    }

    private void sendErrorResponse(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        String errorMessage;

        if (exception instanceof OAuth2AuthenticationException) {
            OAuth2AuthenticationException authorizationException = (OAuth2AuthenticationException) exception;
            errorMessage = StrUtil.isBlank(authorizationException.getError().getDescription()) ? authorizationException.getError().getErrorCode() : authorizationException.getError().getDescription();
        } else {
            errorMessage = exception.getLocalizedMessage();
        }

        // 手机号登录
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (SecurityConstants.MOBILE.equals(grantType)) {
            errorMessage = MsgUtils.getSecurityMessage("AbstractUserDetailsAuthenticationProvider.smsBadCredentials");
        }

        this.errorHttpResponseConverter.write(CoolRespUtils.fail(errorMessage), MediaType.APPLICATION_JSON, httpResponse);
    }

}
