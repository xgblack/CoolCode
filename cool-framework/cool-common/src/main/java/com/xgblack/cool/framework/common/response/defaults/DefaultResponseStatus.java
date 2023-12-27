package com.xgblack.cool.framework.common.response.defaults;


import com.xgblack.cool.framework.common.response.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 默认的ResponseStatus实现
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 * @version 0.1
 * @since 0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponseStatus implements ResponseStatus {

    /**
     * 响应码.
     */
    private Long code;

    /**
     * 响应信息.
     */
    private String msg;

}
