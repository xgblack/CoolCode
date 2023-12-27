package com.xgblack.cool.framework.common.response.api;


import com.xgblack.cool.framework.common.response.Response;
import com.xgblack.cool.framework.common.response.ResponseStatus;

/**
 * ResponseBean的工厂类，用于生成ResponseBean.
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
public interface ResponseFactory {


    /**
     * 创建新的空响应.
     *
     * @return
     */
    Response newEmptyInstance();

    /**
     * 创建新的空响应.
     *
     * @param statusLine 响应行信息.
     * @return
     */
    Response newInstance(ResponseStatus statusLine);

    /**
     * 创建新的响应.
     *
     * @return
     */
    Response newSuccessInstance();

    /**
     * 从数据中创建成功响应.
     *
     * @param data 响应数据.
     * @return
     */
    Response newSuccessInstance(Object data);

    /**
     * 创建新的失败响应.
     *
     * @return
     */
    Response newFailInstance();

}
