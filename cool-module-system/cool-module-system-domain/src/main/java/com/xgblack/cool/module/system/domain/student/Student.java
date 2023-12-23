package com.xgblack.cool.module.system.domain.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xg BLACK
 * @date 2023/12/23 18:54
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Student {
    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime birthday;
    private LocalDate localDate;
    private Date date;
}
