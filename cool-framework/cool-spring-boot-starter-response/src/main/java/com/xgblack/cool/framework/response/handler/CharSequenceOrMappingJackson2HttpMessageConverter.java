package com.xgblack.cool.framework.response.handler;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 兼容CharSequence和对象类型
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 *
 */

public class CharSequenceOrMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    private static final StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        if (CharSequence.class.isAssignableFrom(clazz) || String.class.isAssignableFrom(clazz)) {
            return super.canWrite(String.class, mediaType);
        } else {
            return super.canWrite(clazz, mediaType);
        }
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (object != null && object instanceof CharSequence) {
            outputMessage.getHeaders().setContentType(MediaType.TEXT_PLAIN);;
            stringHttpMessageConverter.write(object.toString(), MediaType.TEXT_PLAIN, outputMessage);
            return;
        }
        outputMessage.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        super.writeInternal(object, object != null ? object.getClass() : null, outputMessage);
    }

}
