package com.crims.apps.service.videotapedrive;

import com.crims.apps.dao.confinfo.ConfVideotapeDriveDao;
import com.crims.apps.model.confinfo.ConfVideotapeDrive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfVideotapeDriveServiceImpl implements ConfVideotapeDriveService{

    @Autowired
    private ConfVideotapeDriveDao ConfVideotapeDriveDao;

    /**
     * 获取数据库存在的盘符
     * @return List<ConfVideotapeDrive>
     */
    public List<ConfVideotapeDrive> getVideotapeDriveList(){
        return ConfVideotapeDriveDao.getVideotapeDriveList();
    }

    /**
     * 获取本地盘符并添加到数据库
     * @param confVideotapeDrive
     * @return int
     */
    public int insertVideotapeDriveInfo(ConfVideotapeDrive confVideotapeDrive){
        return ConfVideotapeDriveDao.insertVideotapeDriveInfo(confVideotapeDrive);
    }
}
