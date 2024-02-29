package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.xgblack.cool.framework.mybatis.dataobject.BaseDO;
import com.xgblack.cool.framework.mybatis.type.LongSetJsonTypeHandler;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 租户套餐表 实体类。
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_tenant_package")
public class TenantPackageDO extends BaseDO {

    /**
     * 套餐编号
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 套餐名
     */
    @Column(value = "name")
    private String name;

    /**
     * 租户状态（1正常 0停用）
     */
    @Column(value = "status")
    private Integer status;

    /**
     * 备注
     */
    @Column(value = "remark")
    private String remark;

    /**
     * 关联的菜单编号
     */
    @Column(value = "menu_ids", typeHandler = LongSetJsonTypeHandler.class)
    private Set<Long> menuIds;


    /**
     * 是否删除
     */
    @Column(value = "deleted", isLogicDelete = true)
    private Boolean deleted;


}
