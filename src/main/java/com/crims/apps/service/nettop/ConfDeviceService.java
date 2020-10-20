package com.crims.apps.service.nettop;

import com.crims.apps.model.nettop.ConfDevice;

import java.util.List;

public interface ConfDeviceService {
    List<ConfDevice> findDevice(ConfDevice confDevice);
}
