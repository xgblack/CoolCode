package com.xgblack.cool.framework.security.core.authentication.support.handler;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgblack.cool.framework.common.constants.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 登录前置处理器： 前端密码传输密文解密，验证码处理
 * <p>
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CoolLoginPreFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    //TODO 验证码
    //@Value("${gateway.ignore-clients}")
    //private List<String> ignoreClients;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestUrl = request.getServletPath();

        // 不是登录URL 请求直接跳过
        if (!SecurityConstants.OAUTH_TOKEN_URL.equals(requestUrl)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 如果登录URL 但是刷新token的请求，直接向下执行
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (StrUtil.equals(SecurityConstants.REFRESH_TOKEN, grantType)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 客户端配置跳过验证码 TODO: 验证码相关逻辑
		/*boolean isIgnoreClient = ignoreClients.contains(WebUtils.getClientId());
		if (isIgnoreClient) {
			filterChain.doFilter(request, response);
			return;
		}*/

        // 校验验证码 1. 客户端开启验证码 2. 短信模式
		/*try {
			checkCode();
		}
		catch (ValidateCodeException validateCodeException) {
			response.setCharacterEncoding(CharsetUtil.UTF_8);
			response.setStatus(HttpStatus.PRECONDITION_REQUIRED.value());
			R<String> result = new R<>();
			result.setCode(CommonConstants.FAIL);
			result.setData(validateCodeException.getLocalizedMessage());
			result.setMsg(validateCodeException.getLocalizedMessage());
			PrintWriter printWriter = response.getWriter();
			response.setContentType(ContentType.JSON.getValue());
			printWriter.append(objectMapper.writeValueAsString(result));
			return;
		}*/
        filterChain.doFilter(request, response);
    }

    /**
     * 校验验证码
     */
	/*private void checkCode() throws ValidateCodeException {
		Optional<HttpServletRequest> request = WebUtils.getRequest();
		String code = request.get().getParameter("code");

		if (StrUtil.isBlank(code)) {
			throw new ValidateCodeException("验证码不能为空");
		}

		String randomStr = request.get().getParameter("randomStr");

		// https://gitee.com/log4j/pig/issues/IWA0D
		String mobile = request.get().getParameter("mobile");
		if (StrUtil.isNotBlank(mobile)) {
			randomStr = mobile;
		}

		String key = CacheConstants.DEFAULT_CODE_KEY + randomStr;
		RedisTemplate<String, String> redisTemplate = SpringContextHolder.getBean(RedisTemplate.class);
		if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
			throw new ValidateCodeException("验证码不合法");
		}

		Object codeObj = redisTemplate.opsForValue().get(key);

		if (codeObj == null) {
			throw new ValidateCodeException("验证码不合法");
		}

		String saveCode = codeObj.toString();
		if (StrUtil.isBlank(saveCode)) {
			redisTemplate.delete(key);
			throw new ValidateCodeException("验证码不合法");
		}

		if (!StrUtil.equals(saveCode, code)) {
			redisTemplate.delete(key);
			throw new ValidateCodeException("验证码不合法");
		}

		redisTemplate.delete(key);
	}*/

}
