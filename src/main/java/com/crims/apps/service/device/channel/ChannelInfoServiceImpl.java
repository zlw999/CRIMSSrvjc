package com.crims.apps.service.device.channel;

import com.crims.apps.dao.confinfo.ChannelInfoDao;
import com.crims.apps.model.confinfo.ConfChannel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelInfoServiceImpl implements ChannelInfoService{

    @Autowired
    private ChannelInfoDao channelInfoDao;

    /**
     * 查询所有通道信息
     * @return List<ConfChannel>对象SON格式输出
     */
    public List<ConfChannel> getConfChannelList(){
        return channelInfoDao.getConfChannelList();
    }

    /**
     * 添加设备通道信息
     * @param confChannel
     * @return int
     */
    public int insertChannelInfo(ConfChannel confChannel){
        return channelInfoDao.insertChannelInfo(confChannel);
    }
    /**
     * 修改设备通道信息
     * @param confChannel
     * @return int
     */
    public int updateChannelInfo(ConfChannel confChannel){
        return channelInfoDao.updateChannelInfo(confChannel);
    }

    /**
     * 删除设备通道信息
     * @param confChannel
     * @return int
     */
    public int deleteChannelInfo(ConfChannel confChannel){
        return channelInfoDao.deleteChannelInfo(confChannel);
    }

    /**
     * 根据通道类型查询最大通道ID
     * @param typeID
     * @return List<ConfChannel>
     */
    public List<ConfChannel> getMaxChannelID(String typeID){
        return channelInfoDao.getMaxChannelID(typeID);
    }

    /**
     * 根据通道类型和设备id获取通道数量
     * @param typeID
     * @param deviceID
     * @return int
     */
    public int getChannelNum(String typeID,String deviceID){
        return channelInfoDao.getChannelNum(typeID,deviceID);
    }

    /**
     * 获取通道名称
     * @param channelID
     * @return String
     */
    public String getChannelName(String channelID){
        return channelInfoDao.getChannelName(channelID);
    }

    /**
     * 根据节点编号模糊查询通道信息(分页查询)
     * @param channelid
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfChannel>对象集分页形式以JSON格式输出
     */
    @Override
    public PageInfo<ConfChannel> getConfChannelByCId(String channelid, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        StringBuilder channelIDStr = new StringBuilder("%");
        if(channelid == null){
            channelid = "";
        }
        channelIDStr.append(channelid);
        channelIDStr.append("%");
        List<ConfChannel> pageList = channelInfoDao.getConfChannelByCId(channelIDStr.toString());
        PageInfo<ConfChannel> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    /**
     * 根据节点编号模糊查询通道信息(不分页查询)
     * @param channelid
     * @return List<ConfChannel>
     */
    @Override
    public List<ConfChannel> getConfChannelByCId(String channelid) {
        StringBuilder channelIDStr = new StringBuilder("%");
        if(channelid == null){
            channelid = "";
        }
        channelIDStr.append(channelid);
        channelIDStr.append("%");
        return channelInfoDao.getConfChannelByCId(channelIDStr.toString());
    }

    /**
     * 根据设备id查询通道数量
     * @param deviceid
     * @return int
     */
    public int getChannelSizeByDeviceID(String deviceid){
        return channelInfoDao.getChannelSizeByDeviceID(deviceid);
    }

    @Override
    public int updateChannelInfoByCID(String deviceid, String channelid) {
        return channelInfoDao.updateChannelInfoByCID(deviceid,channelid);
    }
}
