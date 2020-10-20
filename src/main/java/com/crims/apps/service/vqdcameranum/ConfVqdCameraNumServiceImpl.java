package com.crims.apps.service.vqdcameranum;

import com.crims.apps.dao.confinfo.ConfVqdCameraNumDao;
import com.crims.apps.model.confinfo.ConfVqdCameraNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfVqdCameraNumServiceImpl implements ConfVqdCameraNumService {

    @Autowired
    private ConfVqdCameraNumDao confVqdCameraNumDao;

    @Override
    public List<ConfVqdCameraNum> getVqdCameraNumInfo() {
        return confVqdCameraNumDao.getVqdCameraNumInfo();
    }

    @Override
    public ConfVqdCameraNum getVqdCameraNumByTaskNo(Integer taskno,String nodeid) {
        return confVqdCameraNumDao.getVqdCameraNumByTaskNo(taskno,nodeid);
    }

    /**
     * 添加通道数量信息
     * @param confVqdCameraNum
     * @return int
     */
    @Override
    public int insertVqdCameraNumInfo(ConfVqdCameraNum confVqdCameraNum) {
        return confVqdCameraNumDao.insertVqdCameraNumInfo(confVqdCameraNum);
    }

    /**
     * 更新通道数量信息
     * @param confVqdCameraNum
     * @return int
     */
    @Override
    public int updateVqdCameraNumInfo(ConfVqdCameraNum confVqdCameraNum) {
        return confVqdCameraNumDao.updateVqdCameraNumInfo(confVqdCameraNum);
    }

    /**
     * 根据任务编号删除任务通道数量信息
     * @param taskno
     * @return int
     */
    @Override
    public int deleteVqdCameraNumInfo(Integer taskno) {
        return confVqdCameraNumDao.deleteVqdCameraNumInfo(taskno);
    }
}
