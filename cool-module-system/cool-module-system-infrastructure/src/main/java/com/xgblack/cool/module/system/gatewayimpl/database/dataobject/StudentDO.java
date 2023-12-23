package com.xgblack.cool.module.system.gatewayimpl.database.dataobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xg BLACK
 * @date 2023/12/23 19:30
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class StudentDO {
    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime birthday;
    private LocalDate localDate;
    private Date date;
}
