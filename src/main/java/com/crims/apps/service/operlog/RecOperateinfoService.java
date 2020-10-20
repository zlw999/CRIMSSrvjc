package com.crims.apps.service.operlog;

import com.crims.apps.model.operlog.RecOperateinfo;

public interface RecOperateinfoService {

    RecOperateinfo getRecOperateinfo(String userId);

    int updateRecOperateinfo(RecOperateinfo recOperateinfo);

}
