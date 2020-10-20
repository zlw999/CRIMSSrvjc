package com.crims.apps.service.device;

import java.util.List;

import com.crims.apps.model.confinfo.DeviceInfo;
import com.github.pagehelper.PageInfo;

public interface DeviceInfoService {

  	/**
  	 * 查询所有设备信息
     * @return List<DeviceInfo>对象集转JSON格式输出
     */
	List<DeviceInfo> getDeviceInfoList();

    /**
     * 查询所有设备信息(分页)
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<DeviceInfo>对象集分页形式以JSON格式输出
     */
    PageInfo<DeviceInfo> getAll(int currentPage, int pageSize);

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
