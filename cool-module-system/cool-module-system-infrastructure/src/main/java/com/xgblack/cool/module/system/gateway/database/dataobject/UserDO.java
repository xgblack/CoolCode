package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.xgblack.cool.framework.mybatis.listener.DataInsertListener;
import com.xgblack.cool.framework.mybatis.listener.DataUpdateListener;
import com.xgblack.cool.framework.mybatis.type.LongSetJsonTypeHandler;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 管理后台的用户 DO
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_user", onInsert = DataInsertListener.class, onUpdate = DataUpdateListener.class)
public class UserDO {
    /**
     * 用户ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 加密后的密码
     * <p>
     * 因为目前使用 BCryptPasswordEncoder 加密器，所以无需自己处理 salt 盐
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 部门 ID
     */
    private Long deptId;

    /**
     * 岗位编号数组
     */
    @Column(typeHandler = LongSetJsonTypeHandler.class)
    private Set<Long> postIds;


    /**
     * 用户性别
     * <p>
     * 枚举类 {@link SexEnum}
     */
    private Integer sex;

    /**
     * 是否删除
     */
    @Column(isLogicDelete = true)
    private Boolean deleted;

    /**
     * 是否锁定
     */
    private Boolean locked;

    private Long tenantId;

    private String remark;

    /**
     * 创建者id
     */
    private Long creator;
    /**
     * 更新者id
     */
    private Long updater;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;




    /**
     * 最后登录IP
     */
    //private String loginIp;
    /**
     * 最后登录时间
     */
    //private LocalDateTime loginDate;

    /**
     * 微信openid
     */
    private String wxOpenid;

    /**
     * 微信小程序openId
     */
    private String miniOpenid;

    /**
     * QQ openid
     */
    private String qqOpenid;

    /**
     * 码云唯一标识
     */
    private String giteeLogin;

    /**
     * 开源中国唯一标识
     */
    private String oscId;
}
