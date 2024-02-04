package com.xgblack.cool.module.system.dto.user;

import com.xgblack.cool.framework.common.pojo.dto.PageQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.xgblack.cool.framework.common.utils.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserPageQry extends PageQuery {

    /**
     * 用户账号，模糊匹配
     */
    private String username;

    /**
     * 手机号码，模糊匹配
     */
    private String phone;

    /**
     * 账号状态
     */
    private Boolean locked;

    /**
     * 创建时间
     * [2022-07-01 00:00:00, 2022-07-01 23:59:59]
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    /**
     * 部门编号，同时筛选子部门
     */
    private Long deptId;
}
