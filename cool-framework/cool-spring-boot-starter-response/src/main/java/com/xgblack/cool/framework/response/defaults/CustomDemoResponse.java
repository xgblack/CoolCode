package com.xgblack.cool.framework.response.defaults;


import com.xgblack.cool.framework.response.data.Response;
import com.xgblack.cool.framework.response.data.ResponseStatus;

import java.util.Collections;

/**
 * 默认的Response实现
 * 包装成统一响应的JavaBean.
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
public class CustomDemoResponse implements Response {

    private ResponseStatus status;
    
    private Object payload = Collections.emptyMap();

    public CustomDemoResponse() {
    }

    public CustomDemoResponse(Object payload) {
        this.payload = payload;
    }

    @Override
    public void setStatus(ResponseStatus responseStatus) {
        this.status = responseStatus;
    }

    @Override
    public ResponseStatus getStatus() {
        return status;
    }

    @Override
    public void setPayload(Object obj) {
        this.payload = obj;
    }

    @Override
    public Object getPayload() {
        return payload;
    }
}
