package com.crims.apps.service.devicemonitor;

import com.crims.apps.model.devicemonitor.ViewMonitorAlarmType;
import com.github.pagehelper.PageInfo;

public interface ViewMonitorAlarmtypeService {
    PageInfo<ViewMonitorAlarmType> list_Records(int currentPage, int pageSize, Integer operate, String locationCode, String mpType, String mpName, String deviceId);
}
