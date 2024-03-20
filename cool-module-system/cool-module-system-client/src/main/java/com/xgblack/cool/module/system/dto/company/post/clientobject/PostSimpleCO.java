package com.xgblack.cool.module.system.dto.company.post.clientobject;

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
public class PostSimpleCO extends ClientObject {

    /** 岗位序号 */
    //@ExcelProperty("岗位序号")
    private Long id;

    /** 岗位名称 */
    //@ExcelProperty("岗位名称")
    private String name;

}
