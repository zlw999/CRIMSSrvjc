package com.crims.apps.service.devicemonitor;

import com.crims.apps.model.devicemonitor.ConfMonitorPoint;
import com.crims.apps.model.devicemonitor.MonitorItemBody;
import com.github.pagehelper.PageInfo;

public interface ConfMonitorPointService {

    void addMonitor(MonitorItemBody itemBody, String deviceId);

    PageInfo<ConfMonitorPoint> getMonitorPointList(int currentPage, int pageSize, String deviceId);
}
