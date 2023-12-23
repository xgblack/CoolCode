package com.xgblack.cool.module.system.executor.query;


import com.xgblack.cool.module.system.convertor.StudentConvert;
import com.xgblack.cool.module.system.domain.gateway.StudentGateway;
import com.xgblack.cool.module.system.dto.student.clientobject.StudentCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentByIdQryExe {
    private final StudentGateway gateway;

    public StudentCO execute(Long id) {
        //可以直接调用infra层查出DO
        return StudentConvert.INSTANCE.convertClientObject(gateway.getById(id));
    }
}
