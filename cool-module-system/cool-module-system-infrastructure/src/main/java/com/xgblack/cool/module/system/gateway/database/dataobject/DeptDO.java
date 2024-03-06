package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.xgblack.cool.framework.mybatis.dataobject.TenantBaseDO;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 部门表 实体类。
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_dept")
public class DeptDO extends TenantBaseDO {

    /**
     * 部门id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 部门名称
     */
    @Column(value = "name")
    private String name;

    /**
     * 父部门id
     */
    @Column(value = "parent_id")
    private Long parentId;

    /**
     * 显示顺序
     */
    @Column(value = "sort")
    private Integer sort;

    /**
     * 负责人
     */
    @Column(value = "leader_user_id")
    private Long leaderUserId;

    /**
     * 联系电话
     */
    @Column(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @Column(value = "email")
    private String email;

    /**
     * 部门状态（1正常 0停用）
     */
    @Column(value = "status")
    private Boolean status;

    /**
     * 是否删除
     */
    @Column(value = "deleted", isLogicDelete = true)
    private Boolean deleted;


}
