package com.crims.apps.service.operlog;

import com.crims.apps.dao.operlog.OperateLogInfoDao;
import com.crims.apps.model.operlog.OperateLogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperateLogInfoServiceImpl implements OperateLogInfoService{

    @Autowired
    private OperateLogInfoDao operateLogInfoDao;

    /**
     * 添加日志
     * @param operateLogInfo
     * @return int
     */
    @Override
    public int add_OperateLog(OperateLogInfo operateLogInfo) {
        return operateLogInfoDao.add_OperateLog(operateLogInfo);
    }
}
