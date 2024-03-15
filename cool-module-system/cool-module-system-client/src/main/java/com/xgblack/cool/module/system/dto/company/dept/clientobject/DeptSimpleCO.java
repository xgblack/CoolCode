package com.xgblack.cool.module.system.dto.company.dept.clientobject;

import com.xgblack.cool.framework.common.pojo.ClientObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 部门精简信息
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class DeptSimpleCO extends ClientObject {

    /** 部门编号 */
    private Long id;

    /** 部门名称 */
    private String name;

    /** 父部门 ID */
    private Long parentId;

}
