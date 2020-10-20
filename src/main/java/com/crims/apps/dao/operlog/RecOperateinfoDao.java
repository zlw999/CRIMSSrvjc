package com.crims.apps.dao.operlog;

import com.crims.apps.model.operlog.RecOperateinfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecOperateinfoDao {

    RecOperateinfo getRecOperateinfo(String userId);

    int updateRecOperateinfo(RecOperateinfo recOperateinfo);
}
