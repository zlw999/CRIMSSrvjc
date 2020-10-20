package com.crims.apps.dao.confinfo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.crims.apps.model.confinfo.DeviceInfo;

@Mapper
public interface DeviceInfoDao {

    /**
     * 查询所有设备信息
     * @return List<DeviceInfo>对象集转JSON格式输出
     */
	List<DeviceInfo> getDeviceInfoList();

    /**
     * 添加设备信息
     * @param deviceInfo
     * @return int
     */
    int insertDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 修改设备信息
     * @param deviceInfo
     * @return int
     */
    int updateDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 删除设备信息
     * @param deviceInfo
     * @return int
     */
    int deleteDeviceInfo(DeviceInfo deviceInfo);

    DeviceInfo getDeviceInfo(String deviceid);

    List<DeviceInfo> getDeviceInfoByDType(String deviceType,String nodeId);

}