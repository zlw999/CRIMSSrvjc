package com.crims.apps.service.vqdcamera;

import com.crims.apps.dao.confinfo.ConfVqdCameraDao;
import com.crims.apps.model.confinfo.ConfVqdCamera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConfVqdcameraServiceImpl implements ConfVqdcameraService {

    @Autowired
    private ConfVqdCameraDao confVqdCameraDao;

    /**
     * 添加图诊任务配置通道信息
     * @param confVqdCamera
     * @return int
     */
    @Override
    public int insertConfVqdCameraInfo(ConfVqdCamera confVqdCamera) {
        return confVqdCameraDao.insertConfVqdCameraInfo(confVqdCamera);
    }

    /**
     * 修改图诊任务配置通道信息
     * @param taskNo
     * @return int
     */
    @Override
    public int updateConfVqdCameraInfo(Integer taskNo) {
        return confVqdCameraDao.updateConfVqdCameraInfo(taskNo);
    }

    /**
     * 删除图诊任务配置通道信息
     * @param confVqdCamera
     * @return int
     */
    @Override
    public int deleteConfVqdCameraInfo(ConfVqdCamera confVqdCamera) {
        return confVqdCameraDao.deleteConfVqdCameraInfo(confVqdCamera);
    }

    /**
     * 根据任务编号获取通道ID和通道名称
     * @param taskNo
     * @return List<Map<String, Object>>
     */
    @Override
    public List<Map<String, Object>> getVqdCameraInfoByChannelId(String taskNo) {
        return confVqdCameraDao.getVqdCameraInfoByChannelId(Integer.parseInt(taskNo));
    }
}
