package com.xgblack.cool.module.system.dto.permission;

import com.xgblack.cool.framework.common.pojo.dto.Command;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class PermissionRoleMenuAssignCmd extends Command {

    /**
     * 角色编号
     */
    @NotNull(message = "角色编号不能为空")
    private Long roleId;

    /**
     * 菜单编号列表
     */
    private Set<Long> menuIds = Collections.emptySet(); // 兜底

}
