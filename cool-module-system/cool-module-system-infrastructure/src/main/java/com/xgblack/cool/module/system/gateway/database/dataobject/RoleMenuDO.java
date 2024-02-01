package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.xgblack.cool.framework.core.db.TenantBaseDO;
import com.xgblack.cool.framework.mybatis.listener.DataInsertListener;
import com.xgblack.cool.framework.mybatis.listener.DataUpdateListener;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 角色菜单关联
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_role_menu", onInsert = DataInsertListener.class, onUpdate = DataUpdateListener.class)
public class RoleMenuDO extends TenantBaseDO {

    /**
     * 自增主键
     */
    @Id(keyType = KeyType.Auto)
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;

}
