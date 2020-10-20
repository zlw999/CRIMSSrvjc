package com.crims.apps.dao.devicemonitor;

import com.crims.apps.model.devicemonitor.ConfMpattribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface ConfMpattributeDao {

    int insert(ConfMpattribute record);

    /**
     * 批量添加
     * @param confMpattributes
     */
    void insertList(@Param("list") ArrayList<ConfMpattribute> confMpattributes);
    /**
     * 根据设备id删除
     * @param deviceId
     */
    void deleteByDeviceId(@Param("deviceId") String deviceId);

}