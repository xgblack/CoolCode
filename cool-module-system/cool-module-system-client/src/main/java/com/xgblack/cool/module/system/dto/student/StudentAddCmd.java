package com.xgblack.cool.module.system.dto.student;

import com.xgblack.cool.framework.common.pojo.dto.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author xg black
 * @date 2023/12/20 17:43
 */
@Data
@Accessors(chain = true)
public class StudentAddCmd extends Command {
    /**
     * 姓名
     */
    //@ValidationStatusCode
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 年龄
     */
    @NotNull
    private Integer age;
    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 日期
     */
    private LocalDate localDate;

    /**
     * 日期时间
     */
    private Date date;

    /**
     * 爱好
     */
    @Size(min = 1)
    private List<String> hobby;
}
