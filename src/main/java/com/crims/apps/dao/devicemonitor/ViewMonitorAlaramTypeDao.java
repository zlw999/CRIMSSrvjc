package com.crims.apps.dao.devicemonitor;

import com.crims.apps.model.devicemonitor.ViewMonitorAlarmType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ViewMonitorAlaramTypeDao {

    /**
     * list all
     * @param operate
     * @param mpName
     * @param locationCode
     * @param mpType
     * @return
     */
    List<ViewMonitorAlarmType> getList(@Param("operate") Integer operate, @Param("mpName") String mpName, @Param("locationCode") String locationCode, @Param("mpType") String mpType, @Param("deviceId") String deviceId);

}
