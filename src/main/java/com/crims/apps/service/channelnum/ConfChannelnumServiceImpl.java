package com.crims.apps.service.channelnum;

import com.crims.apps.dao.confinfo.ConfChannelnumDao;
import com.crims.apps.model.confinfo.ConfChannelnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfChannelnumServiceImpl implements ConfChannelnumService{

    @Autowired
    private ConfChannelnumDao confChannelnumDao;

    /**
     * 根据节点id查询通道数量信息
     * @param nodeid
     * @return ConfChannelnum
     */
    @Override
    public ConfChannelnum getChannelNumByNodeID(String nodeid) {
        return confChannelnumDao.getChannelNumByNodeID(nodeid);
    }

    @Override
    public List<ConfChannelnum> getChannelNumInfo() {
        return confChannelnumDao.getChannelNumInfo();
    }

    /**
     * 添加通道数量信息
     * @param confChannelnum
     * @return int
     */
    @Override
    public int insertChannelNumInfo(ConfChannelnum confChannelnum) {
        return confChannelnumDao.insertChannelNumInfo(confChannelnum);
    }

    /**
     * 更新通道数量信息
     * @param confChannelnum
     * @return int
     */
    @Override
    public int updateChannelNumInfo(ConfChannelnum confChannelnum) {
        return confChannelnumDao.updateChannelNumInfo(confChannelnum);
    }
}
