package com.xgblack.cool.module.system.service;

import com.xgblack.cool.module.system.api.StudentServiceI;
import com.xgblack.cool.module.system.dto.student.StudentAddCmd;
import com.xgblack.cool.module.system.dto.student.clientobject.StudentCO;
import com.xgblack.cool.module.system.executor.StudentAddCmdExe;
import com.xgblack.cool.module.system.executor.query.StudentByIdQryExe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xg BLACK
 * @date 2023/12/23 19:05
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentServiceI {
    private final StudentAddCmdExe studentAddCmdExe;
    private final StudentByIdQryExe studentByIdQryExe;


    @Override
    public void save(StudentAddCmd cmd) {
        studentAddCmdExe.execute(cmd);
    }

    @Override
    public StudentCO getDetail(Long id) {
        return studentByIdQryExe.execute(id);
    }

}
