package com.xgblack.cool.module.system.dto.permission;

import com.xgblack.cool.framework.common.pojo.dto.Command;
import jakarta.validation.constraints.NotNull;
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
public class RoleEditStatusCmd extends Command {

    /**
     * 角色编号
     */
    @NotNull(message = "角色编号不能为空")
    private Long id;

    @NotNull(message = "状态不能为空")
    private Boolean status;

}
