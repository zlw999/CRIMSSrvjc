package com.crims.apps.service.devicemonitor;

import com.crims.apps.dao.devicemonitor.ConfAlarmthresholdDao;
import com.crims.apps.model.devicemonitor.ConfAlarmthreshold;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfAlarmThreshouldServiceImpl implements  ConfAlarmThreshouldService {

    @Autowired
    private ConfAlarmthresholdDao alarmthresholdDao;

    @Override
    public PageInfo<ConfAlarmthreshold> getThresholdByMpid(int currentPage, int pageSize, String mPid) {
        PageHelper.startPage(currentPage, pageSize);
        List<ConfAlarmthreshold> pageList = alarmthresholdDao.getListByMpid(mPid);
        PageInfo<ConfAlarmthreshold> pageInfo = new PageInfo<ConfAlarmthreshold>(pageList);
        return pageInfo;
    }

    @Override
    public void insertThresholdList(ArrayList<ConfAlarmthreshold> mxList) {
        if(mxList.size()!=0&&!mxList.isEmpty()){
            String deviceid = mxList.get(0).getDeviceid();
            alarmthresholdDao.deleteByDeviceId(deviceid);
            alarmthresholdDao.insertList(mxList);
        }
    }

    @Override
    public List<ConfAlarmthreshold> getListByMpid(String mPid) {
        return alarmthresholdDao.getListByMpid(mPid);
    }

    @Override
    public int deleteAlarmThreshold(String mpid, String upValue, String downValue) {
        Double upValueDou = 0.0;
        Double downValueDou = 0.0;
        if(StringUtils.isNotBlank(upValue)){
            upValueDou = Double.parseDouble(upValue);
        }
        if(StringUtils.isNotBlank(downValue)){
            downValueDou = Double.parseDouble(downValue);
        }
        return alarmthresholdDao.deleteAlarmThreshold(mpid,upValueDou,downValueDou);
    }

    @Override
    public int udpateAlarmThreshold(ConfAlarmthreshold confAlarmthreshold) {
        return alarmthresholdDao.udpateAlarmThreshold(confAlarmthreshold);
    }
}
