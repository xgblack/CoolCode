package com.xgblack.cool.module.system.domain.gateway;


import com.xgblack.cool.module.system.domain.student.Student;

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
}
