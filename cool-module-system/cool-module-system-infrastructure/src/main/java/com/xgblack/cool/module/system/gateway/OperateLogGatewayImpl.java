package com.xgblack.cool.module.system.gateway;

import com.mybatisflex.core.query.QueryChain;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import com.xgblack.cool.module.system.convertor.OperateLogConvertor;
import com.xgblack.cool.module.system.domain.gateway.OperateLogGateway;
import com.xgblack.cool.module.system.gateway.database.dataobject.table.OperateLogTableDef;
import com.xgblack.cool.module.system.gateway.database.mapper.OperateLogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class OperateLogGatewayImpl implements OperateLogGateway , ILogRecordService {

    private final OperateLogMapper operateLogMapper;

    private final OperateLogConvertor convertor = OperateLogConvertor.INSTANCE;

    @Override
    public void record(LogRecord logRecord) {
        //log.info("【logRecord】log={}", logRecord);
        operateLogMapper.insertSelective(convertor.toDataObject(logRecord));
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return convertor.convertDO2RecordList(
                QueryChain.of(operateLogMapper)
                        .from(OperateLogTableDef.OPERATE_LOG)
                        .and(OperateLogTableDef.OPERATE_LOG.BIZ_NO.eq(bizNo))
                        .and(OperateLogTableDef.OPERATE_LOG.TYPE.eq(type))
                        .list()
        );
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return convertor.convertDO2RecordList(
                QueryChain.of(operateLogMapper)
                        .from(OperateLogTableDef.OPERATE_LOG)
                        .and(OperateLogTableDef.OPERATE_LOG.BIZ_NO.eq(bizNo))
                        .and(OperateLogTableDef.OPERATE_LOG.TYPE.eq(type))
                        .and(OperateLogTableDef.OPERATE_LOG.SUB_TYPE.eq(subType))
                        .list()
        );
    }
}
