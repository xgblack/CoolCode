package com.xgblack.cool.module.system.convertor;

import com.mzt.logapi.beans.CodeVariableType;
import com.mzt.logapi.beans.LogRecord;
import com.xgblack.cool.framework.common.convertor.Convertor;
import com.xgblack.cool.module.system.domain.logger.OperateLog;
import com.xgblack.cool.module.system.dto.logger.clientobject.OperateLogCO;
import com.xgblack.cool.module.system.gateway.database.dataobject.OperateLogDO;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.convert.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Mapper(imports = {Convert.class})
public interface OperateLogConvertor extends Convertor<OperateLogCO, OperateLog, OperateLogDO> {
    OperateLogConvertor INSTANCE = Mappers.getMapper(OperateLogConvertor.class);

    @Mapping(target = "id", expression = "java(Convert.toLong(e.getId()))")
    @Mapping(target = "methodName", expression = "java(toMethodName(e.getCodeVariable()))")
    @Mapping(target = "className", expression = "java(toClassName(e.getCodeVariable()))")
    OperateLogDO toDataObject(LogRecord e);

    @Mapping(target = "codeVariable", expression = "java(toCodeVariable(d.getClassName(),d.getMethodName()))")
    LogRecord convertDO2Record(OperateLogDO d);

    List<LogRecord> convertDO2RecordList(List<OperateLogDO> list);

    default String toMethodName(Map<CodeVariableType, Object> codeVariable){
        if (CollUtil.isEmpty(codeVariable) || !codeVariable.containsKey(CodeVariableType.MethodName)) {
            return null;
        }
        return Convert.toStr(codeVariable.get(CodeVariableType.MethodName));
    }

    default String toClassName(Map<CodeVariableType, Object> codeVariable){
        if (CollUtil.isEmpty(codeVariable) || !codeVariable.containsKey(CodeVariableType.ClassName)) {
            return null;
        }
        return Convert.toStr(codeVariable.get(CodeVariableType.ClassName));
    }

    default Map<CodeVariableType, Object> toCodeVariable(String className, String methodName){
        return Map.of(CodeVariableType.ClassName, className, CodeVariableType.MethodName, methodName);
    }

}
