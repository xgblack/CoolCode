package com.xgblack.cool.framework.security.core.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgblack.cool.framework.common.response.Response;
import com.xgblack.cool.framework.common.utils.response.CoolRespUtils;
import com.xgblack.cool.framework.security.constans.CommonConstants;
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
 * 客户端异常处理 AuthenticationException 不同细化异常处理
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@RequiredArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    private final MessageSource messageSource;

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(CommonConstants.CONTENT_TYPE);
        //fixme
        Response result = CoolRespUtils.fail();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        if (authException != null) {
            result.setPayload(authException.getMessage());
        }


        // 针对令牌过期返回特殊的 424
        if (authException instanceof InvalidBearerTokenException || authException instanceof InsufficientAuthenticationException) {
            response.setStatus(HttpStatus.FAILED_DEPENDENCY.value());
            result.setPayload(this.messageSource.getMessage("OAuth2ResourceOwnerBaseAuthenticationProvider.tokenExpired", null, LocaleContextHolder.getLocale()));
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));
    }

}
