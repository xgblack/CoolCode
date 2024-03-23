package com.xgblack.cool.framework.operatelog.core.service;

import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
@Service
public class CoolLogRecordServiceImpl implements ILogRecordService {
    @Override
    public void record(LogRecord logRecord) {
        log.info("【logRecord...】log={}", logRecord);
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return List.of();
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return List.of();
    }
}
