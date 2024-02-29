package com.xgblack.cool.module.system.dto.permission;

import com.xgblack.cool.framework.common.pojo.dto.Command;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.dromara.hutool.core.collection.set.SetUtil;

import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class PermissionUserRoleAssignCmd extends Command {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /**
     * 角色编号列表
     */
    private Set<Long> roleIds = SetUtil.empty(); // 兜底

}
