package com.xgblack.cool.framework.common.response.customs;

import com.xgblack.cool.framework.common.response.Response;
import com.xgblack.cool.framework.common.response.ResponseStatus;
import lombok.experimental.Accessors;

import java.util.Collections;

/**
 * 自定义响应体
 * 作为示例，并无实际作用
 * @author <a href="https://www.xgblack.cn">xg black</a>
 * @date 2023/12/26 23:19
 */

@Accessors(chain = true)
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
