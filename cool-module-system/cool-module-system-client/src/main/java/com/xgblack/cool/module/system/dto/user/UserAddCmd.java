package com.xgblack.cool.module.system.dto.user;

import com.xgblack.cool.framework.common.pojo.dto.Command;
import com.xgblack.cool.framework.common.validator.Mobile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Data
@Accessors(chain = true)
public class UserAddCmd extends Command {

    /**
     * 用户账号
     */
    @NotBlank(message = "用户账号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,30}$", message = "用户账号由 数字、字母 组成")
    @Size(min = 4, max = 30, message = "用户账号长度为 4-30 个字符")
    private String username;

    /**
     * 加密后的密码
     */
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    /**
     * 用户昵称
     */
    @Size(max = 30, message = "用户昵称长度不能超过30个字符")
    private String nickname;

    /**
     * 手机号码
     */
    @Mobile
    private String phone;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
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
}
