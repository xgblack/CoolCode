package com.xgblack.cool.framework.common.pojo.dto;

import org.dromara.hutool.json.JSONUtil;

import java.io.Serial;

/**
 * 客户端命令请求
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public abstract class Command extends DTO {

    @Serial
    private static final long serialVersionUID = 1L;

    public String toJson() {
        return JSONUtil.toJsonStr(this);
    }

}
