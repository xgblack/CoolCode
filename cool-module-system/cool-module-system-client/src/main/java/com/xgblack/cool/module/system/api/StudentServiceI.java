package com.xgblack.cool.module.system.api;


import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.dto.student.StudentAddCmd;
import com.xgblack.cool.module.system.dto.student.StudentEditCmd;
import com.xgblack.cool.module.system.dto.student.StudentPageQry;
import com.xgblack.cool.module.system.dto.student.clientobject.StudentCO;

public interface StudentServiceI {
    void save(StudentAddCmd cmd);

    StudentCO getDetail(Long id);

    void remove(Long id);

    void update(StudentEditCmd cmd);

    PageResult<StudentCO> getPage(StudentPageQry qry);
}
