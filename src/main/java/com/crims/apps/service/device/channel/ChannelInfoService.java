package com.crims.apps.service.device.channel;

import com.crims.apps.model.confinfo.ConfChannel;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ChannelInfoService {

    /**
     * 查询所有通道信息
     * @return List<ConfChannel>对象SON格式输出
     */
    List<ConfChannel> getConfChannelList();

    /**
     * 添加设备通道信息
     * @param confChannel
     * @return int
     */
    int insertChannelInfo(ConfChannel confChannel);

    /**
     * 修改设备通道信息
     * @param confChannel
     * @return int
     */
    int updateChannelInfo(ConfChannel confChannel);

    /**
     * 删除设备通道信息
     * @param confChannel
     * @return int
     */
    int deleteChannelInfo(ConfChannel confChannel);

    /**
     * 根据通道类型查询最大通道ID
     * @param typeID
     * @return List<ConfChannel>
     */
    List<ConfChannel> getMaxChannelID(String typeID);

    /**
     * 根据通道类型和设备id获取通道数量
     * @param typeID
     * @param deviceID
     * @return int
     */
    int getChannelNum(String typeID,String deviceID);

    /**
     * 获取通道名称
     * @param channelID
     * @return String
     */
    String getChannelName(String channelID);

    /**
     * 根据节点编号模糊查询通道信息(分页查询)
     * @param channelid
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfChannel>对象集分页形式以JSON格式输出
     */
    PageInfo<ConfChannel> getConfChannelByCId(String channelid, int currentPage, int pageSize);

    /**
     * 根据节点编号模糊查询通道信息(不分页查询)
     * @return List<ConfChannel>对象SON格式输出
     */
    List<ConfChannel> getConfChannelByCId(String channelid);

    /**
     * 根据设备id查询通道数量
     * @param deviceid
     * @return int
     */
    int getChannelSizeByDeviceID(String deviceid);

    int updateChannelInfoByCID(String deviceid,String channelid);
}
