package com.crims.apps.service.useronline;

import com.crims.apps.dao.useronline.ConfUserOnlineDao;
import com.crims.apps.model.useronline.ConfUserOnline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfUserOnlineServiceImpl implements ConfUserOnlineService {

    @Autowired
    private ConfUserOnlineDao confUserOnlineDao;

    @Override
    public ConfUserOnline getUserOnlineInfo(String userId) {
        return confUserOnlineDao.getUserOnlineInfo(userId);
    }

    @Override
    public int updateUserOnlineInfo(ConfUserOnline confUserOnline) {
        return confUserOnlineDao.updateUserOnlineInfo(confUserOnline);
    }
}
