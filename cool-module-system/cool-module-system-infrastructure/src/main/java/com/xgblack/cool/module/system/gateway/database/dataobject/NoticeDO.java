package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.xgblack.cool.framework.mybatis.dataobject.TenantBaseDO;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 通知公告表 实体类。
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_notice")
public class NoticeDO extends TenantBaseDO {

    /**
     * 公告ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 公告标题
     */
    @Column(value = "title")
    private String title;

    /**
     * 公告内容
     */
    @Column(value = "content")
    private String content;

    /**
     * 公告类型（1通知 2公告）
     */
    @Column(value = "type")
    private Integer type;

    /**
     * 公告状态（1正常 0关闭）
     */
    @Column(value = "status")
    private Boolean status;

    /**
     * 是否删除
     */
    @Column(value = "deleted", isLogicDelete = true)
    private Boolean deleted;



}
