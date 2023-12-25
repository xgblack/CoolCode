
package com.xgblack.cool.module.system.executor;


import com.xgblack.cool.module.system.convertor.StudentConvertor;
import com.xgblack.cool.module.system.domain.gateway.StudentGateway;
import com.xgblack.cool.module.system.dto.student.StudentEditCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentEditCmdExe {

    private final StudentGateway gateway;


    public void execute(StudentEditCmd cmd) {
        gateway.update(StudentConvertor.INSTANCE.toEntity(cmd));
    }
}
