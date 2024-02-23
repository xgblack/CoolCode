package com.xgblack.cool.module.system.dto.user;

import com.xgblack.cool.framework.common.pojo.dto.Command;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * 用户个人信息更新
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserProfileEditCmd extends Command {

    /**
     * 用户昵称
     */
    @Size(max = 30, message = "用户昵称长度不能超过 30 个字符")
    private String nickname;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    private String email;

    /**
     * 手机号码
     */
    @Length(min = 11, max = 11, message = "手机号长度必须 11 位")
    private String phone;

    /**
     * 用户性别
     */
    private Integer sex;
}
