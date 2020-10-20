package com.crims.apps.service.nettop;

import com.crims.apps.dao.nettop.ConfDeviceDao;
import com.crims.apps.model.nettop.ConfDevice;
import com.crims.apps.service.nettop.ConfDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfDeviceServiceImpl implements ConfDeviceService {
    @Autowired
    private ConfDeviceDao confDeviceDao;
    @Override
    public List<ConfDevice> findDevice(ConfDevice confDevice) {
        return confDeviceDao.findDevice(confDevice);
    }
}
