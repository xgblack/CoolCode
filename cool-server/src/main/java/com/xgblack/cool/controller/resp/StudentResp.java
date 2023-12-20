package com.xgblack.cool.controller.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xg black
 * @date 2023/12/20 16:55
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class StudentResp {
    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime birthday;
    private LocalDate localDate;
    private Date date;
}
