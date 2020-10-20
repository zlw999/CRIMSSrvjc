package com.crims.apps.service.camerainfo;

import com.crims.apps.dao.camerainfo.CamerainfoDao;
import com.crims.apps.model.camerainfo.ConfCamerainfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CamerainfoServiceImpl implements CamerainfoService {
    @Autowired
    private CamerainfoDao confCamerainfoDao;

    @Override
    public int update(ConfCamerainfo c) {
        return confCamerainfoDao.update(c);
    }

    @Override
    public List<String> findType() {
        return confCamerainfoDao.findType();
    }

    @Override
    public String findValueById(String deviceid,String s) {
        return confCamerainfoDao.findValueById(deviceid,s);
    }

    @Override
    public void updateConf(String type, String value,String deviceid) {
        confCamerainfoDao.updateConf(type,value,deviceid);
    }

    @Override
    public String find(String deviceid) {
        return confCamerainfoDao.find(deviceid);
    }

    @Override
    public void addConf(String type, String val, String deviceid) {
        confCamerainfoDao.addConf(type,val,deviceid);
    }

}
