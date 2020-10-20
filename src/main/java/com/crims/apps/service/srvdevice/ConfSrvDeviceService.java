package com.crims.apps.service.srvdevice;

import com.crims.apps.model.confinfo.ConfSrvDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ConfSrvDeviceService {

    List<Map<String,Object>> getConfSrvDeviceInfo(String srvnodeid);

    int insertConfSrvDevice(ArrayList<ConfSrvDevice> srvDeviceList);

    int deleteConfSrvDeviceInfo();

}
