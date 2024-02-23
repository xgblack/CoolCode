package com.xgblack.cool.module.system.dto.user.clientobject;

import com.xgblack.cool.framework.common.pojo.dto.DTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserProfileDTO extends DTO {

    private Long id;

    private String username;

    private String nickname;

    private String email;

    private String phone;

    private Integer sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 最后登录 IP
     */
    //private String loginIp;

    /**
     * 最后登录时间
     */
    //private LocalDateTime loginDate;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 所属角色 TODO
     */
    //private List<RoleSimpleRespVO> roles;

    /**
     * 所在部门 TODO
     */
    //private DeptSimpleRespVO dept;

    /**
     * 所属岗位数组 TODO
     */
    //private List<PostSimpleRespVO> posts;

    /**
     * 社交用户数组
     */
    private List<SocialUser> socialUsers;

    /**
     * 社交用户
     */
    @Data
    public static class SocialUser {

        /**
         * 社交平台的类型，参见 SocialTypeEnum 枚举类
         */
        private Integer type;

        /**
         * 社交用户的 openid
         */
        private String openid;

    }

}
