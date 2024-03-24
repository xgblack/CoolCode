package com.xgblack.cool.module.system.service;

import com.xgblack.cool.module.system.api.DeptServiceI;
import com.xgblack.cool.module.system.dto.company.dept.DeptAddCmd;
import com.xgblack.cool.module.system.dto.company.dept.DeptEditCmd;
import com.xgblack.cool.module.system.dto.company.dept.DeptListQry;
import com.xgblack.cool.module.system.dto.company.dept.clientobject.DeptCO;
import com.xgblack.cool.module.system.dto.company.dept.clientobject.DeptSimpleCO;
import com.xgblack.cool.module.system.executor.company.dept.DeptAddCmdExe;
import com.xgblack.cool.module.system.executor.company.dept.DeptEditCmdExe;
import com.xgblack.cool.module.system.executor.company.dept.DeptRemoveCmdExe;
import com.xgblack.cool.module.system.executor.company.dept.query.DeptByIdQryExe;
import com.xgblack.cool.module.system.executor.company.dept.query.DeptListQryExe;
import com.xgblack.cool.module.system.executor.company.dept.query.DeptSimpleListQryExe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptServiceI {

    private final DeptAddCmdExe deptAddCmdExe;
    private final DeptEditCmdExe deptEditCmdExe;
    private final DeptRemoveCmdExe deptRemoveCmdExe;
    private final DeptListQryExe deptListQryExe;
    private final DeptByIdQryExe deptByIdQryExe;
    private final DeptSimpleListQryExe deptSimpleListQryExe;

    @Override
    public Long add(DeptAddCmd cmd) {
        return deptAddCmdExe.execute(cmd);
    }

    @Override
    public void edit(DeptEditCmd cmd) {
        deptEditCmdExe.execute(cmd);
    }

    @Override
    public void remove(Long id) {
        deptRemoveCmdExe.execute(id);
    }

    @Override
    public List<DeptCO> list(DeptListQry qry) {
        return deptListQryExe.execute(qry);
    }

    @Override
    public DeptCO detail(Long id) {
        return deptByIdQryExe.execute(id);
    }

    @Override
    public List<DeptSimpleCO> getSimpleList() {
        return deptSimpleListQryExe.execute();
    }


}
