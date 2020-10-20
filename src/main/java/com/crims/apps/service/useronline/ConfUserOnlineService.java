package com.crims.apps.service.useronline;

import com.crims.apps.model.useronline.ConfUserOnline;

public interface ConfUserOnlineService {

    ConfUserOnline getUserOnlineInfo(String userId);

    int updateUserOnlineInfo(ConfUserOnline confUserOnline);
}
