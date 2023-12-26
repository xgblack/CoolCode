package com.xgblack.cool.framework.response.defaults;


import com.xgblack.cool.framework.response.api.ResponseFactory;
import com.xgblack.cool.framework.response.api.ResponseStatusFactory;
import com.xgblack.cool.framework.response.data.Response;
import com.xgblack.cool.framework.response.data.ResponseStatus;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 提供的默认的ResponseBeanFactory实现.
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
public class DefaultResponseFactory implements ResponseFactory {

    @Resource
    private ResponseStatusFactory responseStatusFactory;

    //private final CoolResponseProperties properties;

    @Override
    public Response newEmptyInstance() {
        //返回默认响应体。此处可以增加判断逻辑 通过配置文件配置 responseClassFullName 全限定类名 来达到灵活自定义响应体格式的目的。本项目只需要默认的足够
        return generateDefaultResponse();
    }

    private Response generateDefaultResponse() {
        //Integer responseStyle = properties.getResponseStyle();
        //此处可通过配置 responseStyle 达到灵活切换响应体格式的目的
        return new DefaultResponse();
    }

    @Override
    public Response newInstance(ResponseStatus responseStatus) {
        Response bean = this.newEmptyInstance();
        bean.setStatus(responseStatus);
        return bean;
    }

    @Override
    public Response newSuccessInstance() {
        Response emptyInstance = this.newEmptyInstance();
        emptyInstance.setStatus(responseStatusFactory.defaultSuccess());
        return emptyInstance;
    }

    @Override
    public Response newSuccessInstance(Object payload) {
        Response bean = this.newSuccessInstance();
        bean.setPayload(payload);
        return bean;
    }

    @Override
    public Response newFailInstance() {
        Response bean = this.newEmptyInstance();
        bean.setStatus(responseStatusFactory.defaultError());
        return bean;
    }

}
