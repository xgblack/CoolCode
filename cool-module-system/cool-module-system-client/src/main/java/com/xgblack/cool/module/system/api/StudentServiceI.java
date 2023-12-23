package com.xgblack.cool.module.system.api;


import com.xgblack.cool.module.system.dto.student.StudentAddCmd;
import com.xgblack.cool.module.system.dto.student.clientobject.StudentCO;

public interface StudentServiceI {
    void save(StudentAddCmd cmd);

    StudentCO getDetail(Long id);
}