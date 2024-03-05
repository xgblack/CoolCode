package com.xgblack.cool.module.system.api;

import com.xgblack.cool.module.system.dto.company.dept.DeptAddCmd;
import com.xgblack.cool.module.system.dto.company.dept.DeptEditCmd;
import com.xgblack.cool.module.system.dto.company.dept.DeptListQry;
import com.xgblack.cool.module.system.dto.company.dept.clientobject.DeptCO;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface DeptServiceI {

    void add(DeptAddCmd cmd);

    void edit(DeptEditCmd cmd);

    void remove(Long id);

    List<DeptCO> list(DeptListQry qry);

    DeptCO detail(Long id);

}
