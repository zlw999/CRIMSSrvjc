package com.crims.apps.service.operlog;

import com.crims.apps.dao.operlog.RecOperateinfoDao;
import com.crims.apps.model.operlog.RecOperateinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecOperateinfoServiceImpl implements RecOperateinfoService{

    @Autowired
    private RecOperateinfoDao recOperateinfoDao;

    @Override
    public RecOperateinfo getRecOperateinfo(String userId) {
        return recOperateinfoDao.getRecOperateinfo(userId);
    }

    @Override
    public int updateRecOperateinfo(RecOperateinfo recOperateinfo) {
        return recOperateinfoDao.updateRecOperateinfo(recOperateinfo);
    }
}
