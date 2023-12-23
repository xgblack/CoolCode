package com.xgblack.cool.module.system.gatewayimpl;


import com.xgblack.cool.module.system.domain.gateway.StudentGateway;
import com.xgblack.cool.module.system.domain.student.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * StudentGatewayImpl
 *
 * @author Frank Zhang
 * @date 2020-07-02 12:32 PM
 */
@Component
@Slf4j
public class StudentGatewayImpl implements StudentGateway {
    @Override
    public void create(Student student) {
        log.info("student = {}", student);
    }

    @Override
    public Student getById(Long id) {
        return new Student().setId(2L).setAge(18).setName("张三").setBirthday(LocalDateTime.now()).setDate(new Date()).setLocalDate(LocalDate.now());
    }

}
