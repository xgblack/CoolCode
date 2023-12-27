package com.xgblack.cool.framework.common.utils.response;

import com.xgblack.cool.framework.common.constants.DefaultResponseConstants;
import com.xgblack.cool.framework.common.response.Response;
import com.xgblack.cool.framework.common.response.api.ResponseFactory;
import com.xgblack.cool.framework.common.response.defaults.DefaultResponseStatus;
import lombok.Setter;

/**
 * 响应工具类
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public class CoolRespUtils {
    @Setter
    //@Getter
    private static ResponseFactory responseFactory;

    /**
     * 返回默认成功响应
     * @return Response
     */
    public static Response success() {
        return responseFactory.newSuccessInstance();
    }

    /**
     * 返回成功响应
     * @param data 响应数据
     * @return Response
     */
    public static Response success(Object data) {
        return responseFactory.newSuccessInstance(data);
    }

    /**
     * 返回默认失败
     * @return
     */
    public static Response fail() {
        return responseFactory.newFailInstance();
    }

    /**
     * 返回失败响应
     * @param msg
     * @return
     */
    public static Response fail(String msg) {
        return responseFactory.newInstance(new DefaultResponseStatus(DefaultResponseConstants.DEFAULT_ERROR_CODE, msg));
    }

    /**
     * 自定义响应
     * @param code
     * @param msg
     * @return
     */
    public static Response of(long code, String msg) {
        return responseFactory.newInstance(new DefaultResponseStatus(code, msg));
    }

}
