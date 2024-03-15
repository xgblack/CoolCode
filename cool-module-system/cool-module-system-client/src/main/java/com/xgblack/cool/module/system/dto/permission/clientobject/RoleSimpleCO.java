package com.xgblack.cool.module.system.dto.permission.clientobject;

import com.xgblack.cool.framework.common.pojo.ClientObject;
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
public class RoleSimpleCO extends ClientObject {

    /** 角色编号 */
    private Long id;

    /** 角色名称 */
    private String name;

}
