package com.xgblack.cool.module.system.dto.student;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
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
public class StudentAddCmd {
    @ValidationStatusCode
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotNull
    private Integer age;
    private LocalDateTime birthday;
    private LocalDate localDate;
    private Date date;
    //@NotEmpty(message = "请至少输入一个")
    @Size(min = 1)
    private List<String> hobby;
}
