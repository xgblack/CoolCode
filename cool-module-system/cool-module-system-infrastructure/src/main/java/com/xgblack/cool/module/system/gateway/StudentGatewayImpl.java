package com.xgblack.cool.module.system.gateway;


import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryChain;
import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.convertor.StudentConvertor;
import com.xgblack.cool.module.system.domain.gateway.StudentGateway;
import com.xgblack.cool.module.system.domain.student.Student;
import com.xgblack.cool.module.system.dto.student.StudentPageQry;
import com.xgblack.cool.module.system.gateway.database.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.xgblack.cool.module.system.gateway.database.dataobject.table.StudentTableDef.STUDENT;

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
        studentMapper.insertSelective(StudentConvertor.INSTANCE.toDataObject(student));
    }

    @Override
    public Student getById(Long id) {
        return StudentConvertor.INSTANCE.convertDO2Entity(studentMapper.selectOneById(id));
    }

    @Override
    public void update(Student student) {
        studentMapper.update(StudentConvertor.INSTANCE.toDataObject(student));
    }

    @Override
    public void delete(Long id) {
        studentMapper.deleteById(id);
    }

    public PageResult<Student> getPage(StudentPageQry qry) {

        return StudentConvertor.INSTANCE.convertDO2EntityPage(
                QueryChain.of(studentMapper)
                        .from(STUDENT)
                        .and(STUDENT.NAME.like(qry.getName(), StrUtil.isNotBlank(qry.getName()))) //可以用where也可以直接用and
                        .and(STUDENT.AGE.eq(qry.getAge(), qry.getAge() != null))
                        .page(qry.buildPage())
        );
    }

}
