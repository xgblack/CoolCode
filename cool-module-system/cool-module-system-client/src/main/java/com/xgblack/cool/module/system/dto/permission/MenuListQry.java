package com.xgblack.cool.module.system.dto.permission;

import com.xgblack.cool.framework.common.pojo.dto.Query;
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
public class MenuListQry extends Query {

    /**
     * 菜单名称，模糊匹配
     */
    private String name;

    /**
     * 展示状态
     */
    private Boolean status;

}
