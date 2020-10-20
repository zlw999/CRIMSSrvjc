package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfVideotapeDrive;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ConfVideotapeDriveDao {

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
