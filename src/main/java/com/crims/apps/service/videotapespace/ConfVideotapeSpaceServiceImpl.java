package com.crims.apps.service.videotapespace;

import com.crims.apps.dao.confinfo.ConfVideotapeSpaceDao;
import com.crims.apps.model.confinfo.ConfVideotapeSpace;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConfVideotapeSpaceServiceImpl implements ConfVideotapeSpaceService{

    @Autowired
    private ConfVideotapeSpaceDao confVideotapeSpaceDao;

    /**
     * 查询所有通道视频存储信息
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfVideotapeSpace>对象集分页形式以JSON格式输出
     */
    @Override
    public PageInfo<ConfVideotapeSpace> getConfVideotapeSpaceList(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<ConfVideotapeSpace> pageList = confVideotapeSpaceDao.getConfVideotapeSpaceList();
        PageInfo<ConfVideotapeSpace> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    /**
     * 根据通道ID查询通道视频存储信息
     * @param channelID
     * @return List<ConfVideotapeSpace>
     */
    public List<ConfVideotapeSpace> getConfVideotapeSpaceByID(String channelID){
        return confVideotapeSpaceDao.getConfVideotapeSpaceByID(channelID);
    }

    /**
     * 添加通道视频存储信息
     * @param confVideotapeSpace
     * @return int
     */
    public int insertConfVideotapeSpaceInfo(ConfVideotapeSpace confVideotapeSpace){
        return confVideotapeSpaceDao.insertConfVideotapeSpaceInfo(confVideotapeSpace);
    }

    /**
     * 删除通道视频存储信息
     * @param file_path
     * @return int
     */
    public int deleteConfVideotapeSpaceInfo(String file_path){
        return confVideotapeSpaceDao.deleteConfVideotapeSpaceInfo(file_path);
    }

    /**
     * 压缩通道视频存储信息(删除数据库记录)
     * @param channelID
     * @param num
     * @return int
     */
    public int deleteVideotapeSpaceInfoByNum(String channelID,Integer num){
        return confVideotapeSpaceDao.deleteVideotapeSpaceInfoByNum(channelID,num);
    }

    /**
     * 压缩通道视频存储信息(删除磁盘文件)
     * @param channelID
     * @param num
     * @return List<ConfVideotapeSpace>
     */
    public List<ConfVideotapeSpace> getVideotapeSpaceInfoByNum(String channelID,Integer num){
        return confVideotapeSpaceDao.getVideotapeSpaceInfoByNum(channelID,num);
    }

    /**
     * 获取通道存储总的容量
     * @param channelID
     * @return int
     */
    public int getChannelSum(String channelID){
        return confVideotapeSpaceDao.getChannelSum(channelID);
    }

    /**
     * 获取通道文件大小类型
     * @param channelID
     * @return List<Integer>
     */
    public List<Integer> getFileSizeList(String channelID){
        return confVideotapeSpaceDao.getFileSizeList(channelID);
    }

    /**
     * 获取所有通道文件存储信息
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getAllChannelFileInfo(){
        return confVideotapeSpaceDao.getAllChannelFileInfo();
    }

}
