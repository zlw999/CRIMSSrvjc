package com.crims.apps.service.operlog;

import com.crims.apps.model.operlog.OperateLogInfo;

public interface OperateLogInfoService {

    /**
     * 添加日志
     * @param record
     * @return int
     */
    int add_OperateLog(OperateLogInfo record);

}
