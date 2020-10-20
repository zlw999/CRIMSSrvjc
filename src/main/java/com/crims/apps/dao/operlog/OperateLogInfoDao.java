package com.crims.apps.dao.operlog;

import com.crims.apps.model.operlog.OperateLogInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogInfoDao {

    /**
     * 添加日志
     * @param record
     * @return int
     */
    int add_OperateLog(OperateLogInfo record);
}
