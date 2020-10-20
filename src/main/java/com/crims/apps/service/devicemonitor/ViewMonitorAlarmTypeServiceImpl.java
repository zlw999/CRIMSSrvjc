package com.crims.apps.service.devicemonitor;

import com.crims.apps.dao.devicemonitor.ViewMonitorAlaramTypeDao;
import com.crims.apps.model.devicemonitor.ViewMonitorAlarmType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewMonitorAlarmTypeServiceImpl implements  ViewMonitorAlarmtypeService {
    @Autowired
    private ViewMonitorAlaramTypeDao viewMonitorAlaramTypeDao;

    @Override
    public PageInfo<ViewMonitorAlarmType> list_Records(int currentPage, int pageSize, Integer operate, String locationCode, String mpType, String mpName, String deviceId) {
        PageHelper.startPage(currentPage, pageSize);
        List<ViewMonitorAlarmType> pageList = viewMonitorAlaramTypeDao.getList(operate, mpName, locationCode, mpType, deviceId);
        PageInfo<ViewMonitorAlarmType> pageInfo = new PageInfo<ViewMonitorAlarmType>(pageList);
        return pageInfo;
    }
}
