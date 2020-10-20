package com.crims.apps.dao.nettop;

import com.crims.apps.model.nettop.ConfDevice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConfDeviceDao {
    List<ConfDevice> findDevice(ConfDevice confDevice);
}
