package com.xgblack.cool.module.system.dto.company.post;

import com.xgblack.cool.framework.common.pojo.dto.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class PostEditCmd extends Command {

    /** 岗位编号 */
    @NotNull(message = "岗位编号不能为空")
    private Long id;

    /** 岗位名称 */
    @NotBlank(message = "岗位名称不能为空")
    @Size(max = 50, message = "岗位名称长度不能超过 50 个字符")
    private String name;

    /** 岗位编码 */
    @NotBlank(message = "岗位编码不能为空")
    @Size(max = 64, message = "岗位编码长度不能超过64个字符")
    private String code;

    /** 显示顺序 */
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    /** 状态 */
    private Boolean status;

    /** 备注 */
    private String remark;

}
