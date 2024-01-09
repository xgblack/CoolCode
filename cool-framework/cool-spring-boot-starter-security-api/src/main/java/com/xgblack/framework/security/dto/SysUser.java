package com.xgblack.framework.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * 用户DTO
 * TODO：封装到公共api中
 * TODO：可以与DTO合并
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class SysUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
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

    //TODO
    private String salt;

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
    private Set<Long> postIds;


    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 最后登录IP
     */
    //private String loginIp;
    /**
     * 最后登录时间
     */
    //private LocalDateTime loginDate;

    /**
     * 创建时间
     */
    //private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
    //private LocalDateTime updateTime;
    /**
     * 创建者id
     */
    //private Long creator;
    /**
     * 更新者id
     */
    //private Long updater;

    /**
     * 是否删除
     */
    //private Boolean deleted;

    /**
     * 是否锁定
     */
    private Boolean locked;

    private Long tenantId;

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
