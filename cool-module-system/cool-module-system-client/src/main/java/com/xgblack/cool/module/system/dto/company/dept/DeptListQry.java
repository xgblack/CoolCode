package com.xgblack.cool.module.system.dto.company.dept;

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
public class DeptListQry extends Query {

    /**
     * 部门名称，模糊匹配
     */
    private String name;

    /**
     * 展示状态
     */
    private Boolean status;

}
