package com.crims.apps.service.alarminfo;

import com.crims.apps.model.alarminfo.AlarmInfoHis;
import com.github.pagehelper.PageInfo;

import java.util.Date;

public interface AlarmInfoHisService {

    PageInfo<AlarmInfoHis> getAlarmInfoHisList(String nodeName,String deviceName,String ipAddress,String alarmType,String alarmLevel,Date alarmtime,Date alarmdistime,String alarmaffirmtime,String isendtime,int currentPage,int pageSize,
                                               String mpNameOr,String AlarmAffirmUseridOr,String nodeNameOr,String deviceNameOr,
                                               String ipAddressOr,String alarmTypeOr,String alarmLevelOr,String alarmtimeOr,String alarmdistimeOr,String alarmaffirmtimeOr,String orderRule);

}
