package com.crims.apps.dao.devicemonitor;

import com.crims.apps.model.devicemonitor.ConfMonitorPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ConfMonitorPointDao {

    int insert(ConfMonitorPoint record);

    /**
     * 批量添加
     * @param confMonitorPoints
     */
    void insertList(@Param("list") ArrayList<ConfMonitorPoint> confMonitorPoints);

    /**
     * 根据设备id删除
     * @param deviceId
     */
    void deleteByDeviceId(@Param("deviceId") String deviceId);

    /**
     * list all
     * @param operate
     * @param mpName
     * @param locationCode
     * @param mpType
     * @return
     */
    List<ConfMonitorPoint> getList(@Param("operate") Integer operate, @Param("mpName") String mpName, @Param("locationCode") String locationCode, @Param("mpType") String mpType, @Param("deviceId") String deviceId);

    /**
     * 根据设备id查询对应的监控点列表
     * @param deviceId
     * @return
     */
    List<ConfMonitorPoint> getMonitorPointList(String deviceId);
}