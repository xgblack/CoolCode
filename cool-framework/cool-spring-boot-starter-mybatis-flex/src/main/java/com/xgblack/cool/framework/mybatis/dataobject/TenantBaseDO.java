package com.xgblack.cool.framework.mybatis.dataobject;

import lombok.*;
import lombok.experimental.FieldNameConstants;

/**
 * 拓展多租户的 BaseDO 基类
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString(callSuper = true)
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public abstract class TenantBaseDO extends BaseDO {

    /**
     * 多租户编号
     */
    private Long tenantId;

}
