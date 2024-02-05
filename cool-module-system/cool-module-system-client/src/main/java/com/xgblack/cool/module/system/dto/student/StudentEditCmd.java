package com.xgblack.cool.module.system.dto.student;

import com.xgblack.cool.framework.common.pojo.dto.Command;
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
public class StudentEditCmd extends Command {

    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime birthday;
    private LocalDate localDate;
    private Date date;
    private List<String> hobby;
}
