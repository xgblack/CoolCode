package com.xgblack.cool.framework.mvc.response;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.feiniaojin.gracefulresponse.data.Response;
import com.feiniaojin.gracefulresponse.data.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

/**
 * @author xg black
 * @date 2023/12/25 17:53
 */
@Getter
@Setter
public class CoolResponse implements Response {
    private Long code;

    private Long timestamp = System.currentTimeMillis();

    private String msg;

    private Object data = Collections.EMPTY_MAP;

    /**
     * 拓展字段
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object extend;

    @Override
    public void setStatus(ResponseStatus statusLine) {
        if (NumberUtil.isNumber(statusLine.getCode())) {
            this.code = Convert.toLong(statusLine.getCode());
        } else {
            this.code = -1L;
            this.extend = statusLine.getCode();
        }
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
