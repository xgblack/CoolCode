
package com.xgblack.cool.module.system.executor;


import com.xgblack.cool.module.system.convertor.StudentConvert;
import com.xgblack.cool.module.system.domain.gateway.StudentGateway;
import com.xgblack.cool.module.system.dto.student.StudentAddCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentAddCmdExe {

    private final StudentGateway gateway;


    public void execute(StudentAddCmd cmd) {
        gateway.create(StudentConvert.INSTANCE.convertDTO2Entity(cmd));
    }
}
