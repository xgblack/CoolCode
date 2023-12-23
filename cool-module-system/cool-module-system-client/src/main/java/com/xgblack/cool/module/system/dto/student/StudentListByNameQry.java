package com.xgblack.cool.module.system.dto.student;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xg black
 * @date 2023/12/20 17:43
 */
@Data
@Accessors(chain = true)
public class StudentListByNameQry {
    private String name;
}
