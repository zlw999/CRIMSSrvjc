package com.crims.apps.service.srvdevice;

import com.crims.apps.dao.confinfo.ConfSrvDeviceDao;
import com.crims.apps.model.confinfo.ConfSrvDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ConfSrvDeviceServiceImpl implements ConfSrvDeviceService{

    @Autowired
    private ConfSrvDeviceDao confSrvDeviceDao;

    @Override
    public List<Map<String,Object>> getConfSrvDeviceInfo(String srvnodeid) {
        return confSrvDeviceDao.getConfSrvDeviceInfo(srvnodeid);
    }

    @Override
    public int insertConfSrvDevice(ArrayList<ConfSrvDevice> srvDeviceList) {
        return confSrvDeviceDao.insertConfSrvDevice(srvDeviceList);
    }

    @Override
    public int deleteConfSrvDeviceInfo() {
        return confSrvDeviceDao.deleteConfSrvDeviceInfo();
    }
}
