package com.xgblack.cool.framework.common.address;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * ip地址信息
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class IPInfo {
    /**
     * 是否本地ip
     */
    private boolean local;

    /**
     * ip地址
     */
    private String ip;

    private String formatterAddress;

    private Integer code;

    /**
     * 原始信息
     */
    private String originalInfo;

    /**
     * 运营商信息
     */
    private String isp;
}
