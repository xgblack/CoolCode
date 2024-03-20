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
public class MenuSimpleCO extends ClientObject {

    /** 菜单编号 */
    private Long id;

    /** 菜单名称 */
    private String name;

    /** 父菜单 ID */
    private Long parentId;

    /** 类型，参见 MenuTypeEnum 枚举类 */
    private Integer type;


}
