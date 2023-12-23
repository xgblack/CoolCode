package com.xgblack.cool.module.system.dto.student;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xg black
 * @date 2023/12/20 17:43
 */
@Data
@Accessors(chain = true)
public class StudentAddCmd {
    private String name;
    private Integer age;
    private LocalDateTime birthday;
    private LocalDate localDate;
    private Date date;
}
