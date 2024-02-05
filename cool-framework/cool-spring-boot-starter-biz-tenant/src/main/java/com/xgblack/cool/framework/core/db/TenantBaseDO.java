package com.xgblack.cool.framework.core.db;

import com.xgblack.cool.framework.mybatis.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 拓展多租户的 BaseDO 基类
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TenantBaseDO extends BaseDO {

    /**
     * 多租户编号
     */
    private Long tenantId;

}
