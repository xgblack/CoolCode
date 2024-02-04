package com.xgblack.cool.module.system.dto.student;

import com.xgblack.cool.framework.common.pojo.dto.Query;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xg black
 * @date 2023/12/20 17:43
 */
@Data
@Accessors(chain = true)
public class StudentListByNameQry extends Query {
    private String name;
}
