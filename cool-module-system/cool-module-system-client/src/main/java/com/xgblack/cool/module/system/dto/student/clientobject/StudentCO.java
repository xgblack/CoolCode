package com.xgblack.cool.module.system.dto.student.clientobject;

import com.xgblack.cool.framework.common.pojo.ClientObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author xg BLACK
 * @date 2023/12/23 18:54
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class StudentCO extends ClientObject {
    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime birthday;
    private LocalDate localDate;
    private Date date;

    private List<String> hobby;
}
