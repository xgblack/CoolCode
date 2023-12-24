package com.xgblack.cool.module.system.gateway;


import com.xgblack.cool.module.system.convertor.StudentConvert;
import com.xgblack.cool.module.system.domain.gateway.StudentGateway;
import com.xgblack.cool.module.system.domain.student.Student;
import com.xgblack.cool.module.system.gateway.database.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * StudentGatewayImpl
 *
 * @author Frank Zhang
 * @date 2020-07-02 12:32 PM
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class StudentGatewayImpl implements StudentGateway {

    private final StudentMapper studentMapper;

    @Override
    public void create(Student student) {
        studentMapper.insertSelective(StudentConvert.INSTANCE.toDataObject(student));
    }

    @Override
    public Student getById(Long id) {
        return StudentConvert.INSTANCE.convertEntity(studentMapper.selectOneById(id));
    }

    @Override
    public void update(Student student) {
        studentMapper.update(StudentConvert.INSTANCE.toDataObject(student));
    }

    @Override
    public void delete(Long id) {
        studentMapper.deleteById(id);
    }

}
