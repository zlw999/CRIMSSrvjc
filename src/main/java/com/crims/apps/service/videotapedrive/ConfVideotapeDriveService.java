package com.crims.apps.service.videotapedrive;

import com.crims.apps.model.confinfo.ConfVideotapeDrive;

import java.util.List;

public interface ConfVideotapeDriveService {

    /**
     * 获取数据库存在的盘符
     */
    List<ConfVideotapeDrive> getVideotapeDriveList();

    /**
     * 获取本地盘符并添加到数据库
     * @param confVideotapeDrive
     * @return int
     */
    int insertVideotapeDriveInfo(ConfVideotapeDrive confVideotapeDrive);

}
