package com.xgblack.cool.module.system.dto.student;

import com.xgblack.cool.framework.common.pojo.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author xg black
 * @date 2023/12/20 17:43
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class StudentPageQry extends PageParam {

    private String name;

    private Integer age;

}
