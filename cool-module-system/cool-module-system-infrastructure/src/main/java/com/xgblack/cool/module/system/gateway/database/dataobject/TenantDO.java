package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.xgblack.cool.framework.mybatis.dataobject.BaseDO;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 租户表 实体类。
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_tenant")
public class TenantDO extends BaseDO {

    /**
     * 租户编号
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 租户名
     */
    @Column(value = "name")
    private String name;

    /**
     * 联系人的用户编号
     */
    @Column(value = "contact_user_id")
    private Long contactUserId;

    /**
     * 联系人
     */
    @Column(value = "contact_name")
    private String contactName;

    /**
     * 联系手机
     */
    @Column(value = "contact_mobile")
    private String contactMobile;

    /**
     * 租户状态（1正常 0停用）
     */
    @Column(value = "status")
    private Boolean status;

    /**
     * 绑定域名
     */
    @Column(value = "website")
    private String website;

    /**
     * 租户套餐编号
     */
    @Column(value = "package_id")
    private Long packageId;

    /**
     * 过期时间
     */
    @Column(value = "expire_time")
    private LocalDateTime expireTime;

    /**
     * 账号数量
     */
    @Column(value = "account_count")
    private Integer accountCount;


    /**
     * 是否删除
     */
    @Column(value = "deleted", isLogicDelete = true)
    private Boolean deleted;


}
