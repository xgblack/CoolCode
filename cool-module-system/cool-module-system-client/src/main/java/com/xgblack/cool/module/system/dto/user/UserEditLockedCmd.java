package com.xgblack.cool.module.system.dto.user;

import com.xgblack.cool.framework.common.pojo.dto.Command;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserEditLockedCmd extends Command {
    @NotNull(message = "用户ID不能为空")
    private Long id;

    @NotNull(message = "用户锁定状态不能为空")
    private Boolean locked;
}
