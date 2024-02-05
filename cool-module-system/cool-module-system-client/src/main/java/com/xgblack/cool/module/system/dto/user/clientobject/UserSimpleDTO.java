package com.xgblack.cool.module.system.dto.user.clientobject;

import com.xgblack.cool.framework.common.pojo.dto.DTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户精简信息
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserSimpleDTO extends DTO {

    /**
     * 用户编号
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

}
