package com.xgblack.cool.module.system.domain.gateway;

import com.xgblack.cool.module.system.domain.company.dept.Dept;
import com.xgblack.cool.module.system.dto.company.dept.DeptListQry;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface DeptGateway {

    void insert(Dept entity);

    void update(Dept entity);

    void delete(Long id);

    Dept getById(Long id);

    List<Dept> getList(DeptListQry qry);

    List<Dept> getChildDeptList(Long id);

    List<Dept> getEnableList();

}
