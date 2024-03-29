package com.xgblack.cool.framework.security.core.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgblack.cool.framework.common.utils.response.CoolRespUtils;
import com.xgblack.cool.framework.common.utils.spring.SpringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.core.endpoint.DefaultOAuth2AccessTokenResponseMapConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;

import java.util.Map;

/**
 * 返回结果消息转换
 * 扩展原生的实现，支持 Long2String
 * TODO：考虑是否优化此处
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public class CoolOAuth2AccessTokenResponseHttpMessageConverter extends OAuth2AccessTokenResponseHttpMessageConverter {

    private static final ParameterizedTypeReference<Map<String, Object>> STRING_OBJECT_MAP = new ParameterizedTypeReference<Map<String, Object>>() {};

    private final Converter<OAuth2AccessTokenResponse, Map<String, Object>> accessTokenResponseParametersConverter = new DefaultOAuth2AccessTokenResponseMapConverter();

    @Override
    protected void writeInternal(OAuth2AccessTokenResponse tokenResponse, HttpOutputMessage outputMessage) throws HttpMessageNotWritableException {
        try {
            Map<String, Object> tokenResponseParameters = this.accessTokenResponseParametersConverter.convert(tokenResponse);

            ObjectMapper objectMapper = SpringUtils.getBean(ObjectMapper.class);
            GenericHttpMessageConverter<Object> jsonMessageConverter = new MappingJackson2HttpMessageConverter(objectMapper);
            //适配CoolResponse
            jsonMessageConverter.write(CoolRespUtils.success(tokenResponseParameters), STRING_OBJECT_MAP.getType(), MediaType.APPLICATION_JSON, outputMessage);

        } catch (Exception ex) {
            throw new HttpMessageNotWritableException("An error occurred writing the OAuth 2.0 Access Token Response: " + ex.getMessage(), ex);
        }
    }

}
