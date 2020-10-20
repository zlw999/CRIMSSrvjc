package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfVideotapeSpace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConfVideotapeSpaceDao {

    /**
     * 查询所有通道视频存储信息
     * @return List<ConfVideotapeSpace>对象SON格式输出
     */
    List<ConfVideotapeSpace> getConfVideotapeSpaceList();

    /**
     * 根据通道ID查询通道视频存储信息
     * @param channelID
     * @return List<ConfVideotapeSpace>
     */
    List<ConfVideotapeSpace> getConfVideotapeSpaceByID(String channelID);

    /**
     * 添加通道视频存储信息
     * @param confVideotapeSpace
     * @return int
     */
    int insertConfVideotapeSpaceInfo(ConfVideotapeSpace confVideotapeSpace);

    /**
     * 删除通道视频存储信息
     * @param file_path
     * @return int
     */
    int deleteConfVideotapeSpaceInfo(String file_path);

    /**
     * 压缩通道视频存储信息(删除数据库记录)
     * @param channelID
     * @param num
     * @return int
     */
    int deleteVideotapeSpaceInfoByNum(String channelID,Integer num);

    /**
     * 压缩通道视频存储信息(删除磁盘文件)
     * @param channelID
     * @param num
     * @return List<ConfVideotapeSpace>
     */
    List<ConfVideotapeSpace> getVideotapeSpaceInfoByNum(String channelID,Integer num);

    /**
     * 获取通道存储总的容量
     * @param channelID
     * @return int
     */
    int getChannelSum(String channelID);

    /**
     * 获取通道文件大小类型
     * @param channelID
     * @return List<Integer>
     */
    List<Integer> getFileSizeList(String channelID);

    /**
     * 获取所有通道文件存储信息
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getAllChannelFileInfo();

}
