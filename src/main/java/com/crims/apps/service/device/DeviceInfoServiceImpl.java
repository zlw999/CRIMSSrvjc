package com.crims.apps.service.device;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crims.apps.dao.confinfo.DeviceInfoDao;
import com.crims.apps.model.confinfo.DeviceInfo;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

	@Autowired
	private DeviceInfoDao deviceInfoDao;

    /**
     * 查询所有设备信息
     * @return List<DeviceInfo>对象集转JSON格式输出
     */
	@Override
	public List<DeviceInfo> getDeviceInfoList() {
		return deviceInfoDao.getDeviceInfoList();
	}

    /**
     * 查询所有设备信息(分页)
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<DeviceInfo>对象集分页形式以JSON格式输出
     */
    @Override
    public PageInfo<DeviceInfo> getAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<DeviceInfo> pageList = deviceInfoDao.getDeviceInfoList();
        PageInfo<DeviceInfo> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    /**
     * 添加设备信息
     * @param deviceInfo
     * @return int
     */
    public int insertDeviceInfo(DeviceInfo deviceInfo){
        return deviceInfoDao.insertDeviceInfo(deviceInfo);
    }

    /**
     * 修改设备信息
     * @param deviceInfo
     * @return int
     */
    public int updateDeviceInfo(DeviceInfo deviceInfo){
        return deviceInfoDao.updateDeviceInfo(deviceInfo);
    }

    /**
     * 删除设备信息
     * @param deviceInfo
     * @return int
     */
    public int deleteDeviceInfo(DeviceInfo deviceInfo){
        return deviceInfoDao.deleteDeviceInfo(deviceInfo);
    }

    @Override
    public DeviceInfo getDeviceInfo(String deviceid) {
        return deviceInfoDao.getDeviceInfo(deviceid);
    }

    @Override
    public List<DeviceInfo> getDeviceInfoByDType(String deviceType,String nodeId) {
        return deviceInfoDao.getDeviceInfoByDType(deviceType,nodeId);
    }
}
