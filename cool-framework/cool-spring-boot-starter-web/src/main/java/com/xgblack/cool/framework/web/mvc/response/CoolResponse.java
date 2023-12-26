package com.xgblack.cool.framework.web.mvc.response;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xgblack.cool.framework.response.data.Response;
import com.xgblack.cool.framework.response.data.ResponseStatus;
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

    /**
     * 拓展信息
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
