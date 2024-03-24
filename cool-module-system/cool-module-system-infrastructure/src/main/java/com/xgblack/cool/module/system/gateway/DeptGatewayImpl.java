package com.xgblack.cool.module.system.gateway;

import com.mybatisflex.core.query.QueryChain;
import com.xgblack.cool.module.system.convertor.DeptConvertor;
import com.xgblack.cool.module.system.domain.company.dept.Dept;
import com.xgblack.cool.module.system.domain.gateway.DeptGateway;
import com.xgblack.cool.module.system.dto.company.dept.DeptListQry;
import com.xgblack.cool.module.system.gateway.database.dataobject.DeptDO;
import com.xgblack.cool.module.system.gateway.database.dataobject.table.DeptTableDef;
import com.xgblack.cool.module.system.gateway.database.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Long insert(Dept entity) {
        DeptDO deptDO = convertor.toDataObject(entity);
        deptMapper.insertSelective(deptDO);
        return deptDO.getId();
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
                .orderBy(DeptTableDef.DEPT.SORT.asc(), DeptTableDef.DEPT.ID.asc())
                .listAs(Dept.class);
    }

    @Override
    public List<Dept> getChildDeptList(Long id) {
        List<Dept> children = new LinkedList<>();
        // 遍历每一层
        Collection<Long> parentIds = Collections.singleton(id);
        for (int i = 0; i < Short.MAX_VALUE; i++) { // 使用 Short.MAX_VALUE 避免 bug 场景下，存在死循环
            // 查询当前层，所有的子部门
            List<Dept> depts = QueryChain.of(deptMapper)
                    .from(DeptTableDef.DEPT)
                    .and(DeptTableDef.DEPT.PARENT_ID.in(parentIds))
                    .listAs(Dept.class);
            // 1. 如果没有子部门，则结束遍历
            if (CollUtil.isEmpty(depts)) {
                break;
            }
            // 2. 如果有子部门，继续遍历
            children.addAll(depts);
            parentIds = depts.stream().map(Dept::getId).collect(Collectors.toSet());
        }
        return children;
    }

    @Override
    public List<Dept> getEnableList() {
        return QueryChain.of(deptMapper)
                .from(DeptTableDef.DEPT)
                .and(DeptTableDef.DEPT.STATUS.eq(Boolean.TRUE))
                .orderBy(DeptTableDef.DEPT.SORT.asc(), DeptTableDef.DEPT.ID.asc())
                .listAs(Dept.class);
    }
}
