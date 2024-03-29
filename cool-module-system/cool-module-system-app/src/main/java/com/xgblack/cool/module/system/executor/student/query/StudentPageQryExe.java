package com.xgblack.cool.module.system.executor.student.query;


import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.convertor.StudentConvertor;
import com.xgblack.cool.module.system.domain.gateway.StudentGateway;
import com.xgblack.cool.module.system.dto.student.StudentPageQry;
import com.xgblack.cool.module.system.dto.student.clientobject.StudentCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentPageQryExe {
    private final StudentGateway gateway;

    public PageResult<StudentCO> execute(StudentPageQry qry) {
        //可以直接调用infra层查出DO
        return StudentConvertor.INSTANCE.convertEntity2COPageResult(gateway.getPage(qry));
    }
}
