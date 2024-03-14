package com.xgblack.cool.module.system.dto.permission;

import com.xgblack.cool.framework.common.pojo.dto.Command;
import com.xgblack.cool.framework.common.validator.InEnum;
import com.xgblack.cool.module.system.common.enums.permission.DataScopeEnum;
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
public class PermissionRoleDataScopeAssignCmd extends Command {

    /** 角色编号 */
    @NotNull(message = "角色编号不能为空")
    private Long roleId;

    /** 数据范围，参见 DataScopeEnum 枚举类 */
    @NotNull(message = "数据范围不能为空")
    @InEnum(value = DataScopeEnum.class, message = "数据范围必须是 {value}")
    private Integer dataScope;

    /** 部门编号列表，只有范围类型为 DEPT_CUSTOM 时，该字段才需要 */
    private Set<Long> dataScopeDeptIds = Collections.emptySet(); // 兜底

}
