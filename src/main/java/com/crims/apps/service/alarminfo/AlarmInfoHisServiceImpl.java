package com.crims.apps.service.alarminfo;

import com.crims.apps.dao.alarminfo.AlarmInfoHisDao;
import com.crims.apps.model.alarminfo.AlarmInfoHis;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlarmInfoHisServiceImpl implements AlarmInfoHisService{

    @Autowired
    private AlarmInfoHisDao alarmInfoHisDao;

    /**
     * 查询历史告警信息(分页查询)
     * @param nodeName
     * @param deviceName
     * @param ipAddress
     * @param alarmType
     * @param alarmLevel
     * @param alarmtime
     * @param alarmdistime
     * @param alarmaffirmtime
     * @param currentPage
     * @param pageSize
     * @return PageInfo<AlarmInfoHis>
     */
    @Override
    public PageInfo<AlarmInfoHis> getAlarmInfoHisList(String nodeName, String deviceName, String ipAddress, String alarmType, String alarmLevel,Date alarmtime, Date alarmdistime, String alarmaffirmtime, String isendtime, int currentPage, int pageSize,
                                                      String mpNameOr,String AlarmAffirmUseridOr,String nodeNameOr,String deviceNameOr,
                                                      String ipAddressOr,String alarmTypeOr,String alarmLevelOr,String alarmtimeOr,String alarmdistimeOr,String alarmaffirmtimeOr,String orderRule) {
        PageHelper.startPage(currentPage, pageSize);
        List<AlarmInfoHis> pageHisList = alarmInfoHisDao.getAlarmInfoHisList(nodeName,deviceName,ipAddress,alarmType,alarmLevel,alarmtime,alarmdistime,alarmaffirmtime,isendtime,
                mpNameOr,AlarmAffirmUseridOr,nodeNameOr,deviceNameOr,ipAddressOr,alarmTypeOr,alarmLevelOr,alarmtimeOr,alarmdistimeOr,alarmaffirmtimeOr,orderRule);
        PageInfo<AlarmInfoHis> pageInfo = new PageInfo<>(pageHisList);
        return pageInfo;
    }

}
