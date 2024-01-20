package com.xgblack.cool.framework.security.core.exception;

import cn.hutool.json.JSONUtil;
import com.xgblack.cool.framework.common.utils.response.CoolRespUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.io.IOException;

import static com.xgblack.cool.framework.security.core.constant.OAuth2Constant.LOGIN_URL;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if(authException instanceof InsufficientAuthenticationException){
            String accept = request.getHeader("accept");
            if(accept.contains(MediaType.TEXT_HTML_VALUE)){
                //如果是html请求类型，则返回登录页
                LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint(LOGIN_URL);
                loginUrlAuthenticationEntryPoint.commence(request,response,authException);
            }else {
                //如果是api请求类型，则返回json
                exceptionResponse(response,"需要带上令牌进行访问");
            }
        }else if(authException instanceof InvalidBearerTokenException){
            exceptionResponse(response,"令牌无效或已过期");
        }else{
            exceptionResponse(response,authException);
        }
    }

    public static void exceptionResponse(HttpServletResponse response, String message) throws AccessDeniedException, AuthenticationException, IOException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(JSONUtil.toJsonStr(CoolRespUtils.fail(message)));

    }

    public static void exceptionResponse(HttpServletResponse response, Exception e) throws AccessDeniedException, AuthenticationException,IOException {

        String message = null;
        if(e instanceof OAuth2AuthenticationException o){
            message = o.getError().getDescription();
        }else{
            message = e.getMessage();
        }
        exceptionResponse(response,message);
    }

}
