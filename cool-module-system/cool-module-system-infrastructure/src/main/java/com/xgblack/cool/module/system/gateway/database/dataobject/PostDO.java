package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.xgblack.cool.framework.mybatis.dataobject.TenantBaseDO;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 岗位信息表 实体类。
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_post")
public class PostDO extends TenantBaseDO {

    /**
     * 岗位ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 岗位编码
     */
    @Column(value = "code")
    private String code;

    /**
     * 岗位名称
     */
    @Column(value = "name")
    private String name;

    /**
     * 显示顺序
     */
    @Column(value = "sort")
    private Integer sort;

    /**
     * 部门状态（1正常 0停用）
     */
    @Column(value = "status")
    private Boolean status;

    /**
     * 备注
     */
    @Column(value = "remark")
    private String remark;


    /**
     * 是否删除
     */
    @Column(value = "deleted", isLogicDelete = true)
    private Boolean deleted;



}
