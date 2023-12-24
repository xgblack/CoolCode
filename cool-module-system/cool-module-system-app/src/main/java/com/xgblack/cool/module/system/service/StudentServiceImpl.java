package com.xgblack.cool.module.system.service;

import cn.hutool.core.lang.Assert;
import com.xgblack.cool.framework.common.pojo.PageResult;
import com.xgblack.cool.module.system.api.StudentServiceI;
import com.xgblack.cool.module.system.dto.student.StudentAddCmd;
import com.xgblack.cool.module.system.dto.student.StudentEditCmd;
import com.xgblack.cool.module.system.dto.student.StudentPageQry;
import com.xgblack.cool.module.system.dto.student.clientobject.StudentCO;
import com.xgblack.cool.module.system.executor.StudentAddCmdExe;
import com.xgblack.cool.module.system.executor.StudentEditCmdExe;
import com.xgblack.cool.module.system.executor.StudentRemoveCmdExe;
import com.xgblack.cool.module.system.executor.query.StudentByIdQryExe;
import com.xgblack.cool.module.system.executor.query.StudentPageQryExe;
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
    private final StudentEditCmdExe studentEditCmd;
    private final StudentRemoveCmdExe studentRemoveCmdExe;
    private final StudentPageQryExe studentPageQryExe;


    @Override
    public void save(StudentAddCmd cmd) {
        studentAddCmdExe.execute(cmd);
    }

    @Override
    public StudentCO getDetail(Long id) {
        StudentCO student = studentByIdQryExe.execute(id);
        Assert.notNull(student, "data not found");
        return student;
    }

    @Override
    public void remove(Long id) {
        studentRemoveCmdExe.execute(id);
    }

    @Override
    public void update(StudentEditCmd cmd) {
        studentEditCmd.execute(cmd);
    }

    @Override
    public PageResult<StudentCO> getPage(StudentPageQry qry) {
        return studentPageQryExe.execute(qry);
    }

}
