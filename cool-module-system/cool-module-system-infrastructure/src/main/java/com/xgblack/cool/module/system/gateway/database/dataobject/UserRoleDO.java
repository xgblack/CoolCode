package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.xgblack.cool.framework.mybatis.dataobject.TenantBaseDO;
import com.xgblack.cool.framework.mybatis.listener.DataInsertListener;
import com.xgblack.cool.framework.mybatis.listener.DataUpdateListener;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 用户角色关联
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_user_role", onInsert = DataInsertListener.class, onUpdate = DataUpdateListener.class)
public class UserRoleDO extends TenantBaseDO {
    /**
     * 自增主键
     */
    @Id(keyType = KeyType.Auto)
    private Long id;
    /**
     * 用户 ID
     */
    private Long userId;
    /**
     * 角色 ID
     */
    private Long roleId;

    /**
     * 是否删除
     */
    @Column(isLogicDelete = true)
    private Boolean deleted;

}
