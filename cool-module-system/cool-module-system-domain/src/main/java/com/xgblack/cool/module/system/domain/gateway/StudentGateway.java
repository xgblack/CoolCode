package com.xgblack.cool.module.system.domain.gateway;


import com.xgblack.cool.framework.common.pojo.PageResult;
import com.xgblack.cool.module.system.domain.student.Student;
import com.xgblack.cool.module.system.dto.student.StudentPageQry;

/**
 * StudentGateway
 *
 * @author Frank Zhang
 * @date 2020-07-02 12:16 PM
 */
public interface StudentGateway {
    void create(Student student);
    Student getById(Long id);

    void update(Student student);

    void delete(Long id);

    PageResult<Student> getPage(StudentPageQry qry);
}
