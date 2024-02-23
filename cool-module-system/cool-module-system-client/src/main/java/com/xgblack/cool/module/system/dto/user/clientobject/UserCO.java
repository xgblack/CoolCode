package com.xgblack.cool.module.system.dto.user.clientobject;

import com.xgblack.cool.framework.common.pojo.ClientObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserCO extends ClientObject {

    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户账号
     */
    private String username;

    /**
     * 加密后的密码
     */
    //private String password;

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
     * 是否锁定
     */
    private Boolean locked;

    private Long tenantId;



    /**
     * 微信openid
     */
    //private String wxOpenid;

    /**
     * 微信小程序openId
     */
    //private String miniOpenid;

    /**
     * QQ openid
     */
    //private String qqOpenid;

    /**
     * 码云唯一标识
     */
    //private String giteeLogin;

    /**
     * 开源中国唯一标识
     */
    //private String oscId;

}
