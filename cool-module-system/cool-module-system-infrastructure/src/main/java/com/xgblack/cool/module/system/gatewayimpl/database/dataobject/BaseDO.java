package com.xgblack.cool.module.system.gatewayimpl.database.dataobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * BaseDO
 *
 * @author Frank Zhang
 * @date 2020-07-02 10:03 AM
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class BaseDO {
    private String creator;
    private String modifier;
}
