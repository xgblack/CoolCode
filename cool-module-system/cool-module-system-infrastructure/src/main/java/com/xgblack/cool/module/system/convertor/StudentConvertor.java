package com.xgblack.cool.module.system.convertor;

import com.mybatisflex.core.paginate.Page;
import com.xgblack.cool.framework.common.convertor.Convertor;
import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.domain.student.Student;
import com.xgblack.cool.module.system.dto.student.StudentAddCmd;
import com.xgblack.cool.module.system.dto.student.StudentEditCmd;
import com.xgblack.cool.module.system.dto.student.clientobject.StudentCO;
import com.xgblack.cool.module.system.gateway.database.dataobject.StudentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author xg BLACK
 * @date 2023/12/23 19:16
 */
@Mapper
public interface StudentConvertor extends Convertor<StudentCO, Student, StudentDO> {
    StudentConvertor INSTANCE = Mappers.getMapper(StudentConvertor.class);

    Student toEntity(StudentAddCmd cmd);
    Student toEntity(StudentEditCmd cmd);

    PageResult<Student> convertDO2Page(Page<StudentDO> page);


}
