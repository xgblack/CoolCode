package com.xgblack.cool.module.system.dto.company.post.clientobject;

import com.xgblack.cool.framework.common.pojo.ClientObject;

import java.time.LocalDateTime;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public class PostCO extends ClientObject {

    /**
     * 岗位ID
     */
    private Long id;

    /**
     * 岗位编码
     */
    private String code;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 部门状态（1正常 0停用）
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;


    private LocalDateTime createTime;

}
