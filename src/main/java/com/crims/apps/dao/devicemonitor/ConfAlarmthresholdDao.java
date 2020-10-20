package com.crims.apps.dao.devicemonitor;

import com.crims.apps.model.devicemonitor.ConfAlarmthreshold;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ConfAlarmthresholdDao {

    int insert(ConfAlarmthreshold record);

    /**
     * 根据deviceId批量删除
     * @param deviceId
     */
    void deleteByDeviceId(@Param("deviceId") String deviceId);

    /**
     * 批量添加
     * @param confAlarmthresholds
     */
    void insertList(@Param("list") ArrayList<ConfAlarmthreshold> confAlarmthresholds);

    /**
     * 根据mpid得到list
     * @param mPid
     * @return
     */
    List<ConfAlarmthreshold> getListByMpid(@Param("mPid") String mPid);

    /**
     * 删除监控点门限信息
     * @param mpid
     * @param upValue
     * @param downValue
     * @return
     */
    int deleteAlarmThreshold(String mpid,Double upValue,Double downValue);

    /**
     * 修改门限信息
     * @param confAlarmthreshold
     * @return
     */
    int udpateAlarmThreshold(ConfAlarmthreshold confAlarmthreshold);
}