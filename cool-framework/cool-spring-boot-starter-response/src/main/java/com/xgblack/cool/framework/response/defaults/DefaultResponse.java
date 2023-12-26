package com.xgblack.cool.framework.response.defaults;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgblack.cool.framework.response.data.Response;
import com.xgblack.cool.framework.response.data.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

/**
 * 默认响应体
 * @author <a href="https://www.xgblack.cn">xg black</a>
 * @date 2023/12/25 17:53
 */
@Getter
@Setter
public class DefaultResponse implements Response {
    /**
     * 响应码
     */
    private Long code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data = Collections.EMPTY_MAP;

    /**
     * 响应时间戳
     */
    private Long timestamp = System.currentTimeMillis();


    @Override
    public void setStatus(ResponseStatus statusLine) {
        this.code = statusLine.getCode();
        this.msg = statusLine.getMsg();
    }

    @Override
    @JsonIgnore
    public ResponseStatus getStatus() {
        return null;
    }

    @Override
    public void setPayload(Object payload) {
        this.data = payload;
    }

    @Override
    @JsonIgnore
    public Object getPayload() {
        return null;
    }
}
