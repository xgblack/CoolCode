package com.xgblack.cool.module.system.gateway;

import com.mybatisflex.core.query.QueryChain;
import com.xgblack.cool.module.system.convertor.DeptConvertor;
import com.xgblack.cool.module.system.domain.company.dept.Dept;
import com.xgblack.cool.module.system.domain.gateway.DeptGateway;
import com.xgblack.cool.module.system.dto.company.dept.DeptListQry;
import com.xgblack.cool.module.system.gateway.database.dataobject.table.DeptTableDef;
import com.xgblack.cool.module.system.gateway.database.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class DeptGatewayImpl implements DeptGateway {

    private final DeptMapper deptMapper;

    private final DeptConvertor convertor = DeptConvertor.INSTANCE;

    @Override
    public void insert(Dept entity) {
        deptMapper.insertSelective(convertor.toDataObject(entity));
    }

    @Override
    public void update(Dept entity) {
        deptMapper.update(convertor.toDataObject(entity));
    }

    @Override
    public void delete(Long id) {
        deptMapper.deleteById(id);
    }

    @Override
    public Dept getById(Long id) {
        return deptMapper.selectOneWithRelationsByIdAs(id, Dept.class);
    }

    @Override
    public List<Dept> getList(DeptListQry qry) {
        return QueryChain.of(deptMapper)
                .from(DeptTableDef.DEPT)
                .and(DeptTableDef.DEPT.NAME.like(qry.getName(), StrUtil.isNotBlank(qry.getName())))
                .and(DeptTableDef.DEPT.STATUS.eq(qry.getStatus(), qry.getStatus() != null))
                .listAs(Dept.class);
    }
}
