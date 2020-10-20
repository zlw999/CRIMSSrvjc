package com.crims.apps.dao.alarminfo;

import com.crims.apps.model.alarminfo.AlarmInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AlarmInfoDao {

    List<AlarmInfo> getAlarmInfoList(String nodeName, String deviceName, String ipAddress, String alarmType, String alarmLevel,Date alarmtime, Date alarmdistime, String alarmaffirmtime,
                                     String isendtime,String mpNameOr,String AlarmAffirmUseridOr,String nodeNameOr,String deviceNameOr,
                                     String ipAddressOr,String alarmTypeOr,String alarmLevelOr,String alarmtimeOr,String alarmdistimeOr,String alarmaffirmtimeOr,String orderRule);

}