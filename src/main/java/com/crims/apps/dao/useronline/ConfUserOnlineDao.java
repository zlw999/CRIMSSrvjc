package com.crims.apps.dao.useronline;

import com.crims.apps.model.useronline.ConfUserOnline;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfUserOnlineDao {

    ConfUserOnline getUserOnlineInfo(String userId);

    int updateUserOnlineInfo(ConfUserOnline confUserOnline);
}
