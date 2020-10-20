package com.crims.apps.service.vqdcameratask;

import com.crims.apps.dao.confinfo.ConfNodeSrvDao;
import com.crims.apps.dao.confinfo.ConfVqdCameraTaskDao;
import com.crims.apps.model.confinfo.ConfVqdCameraTask;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConfVqdCameraTaskServiceImpl implements ConfVqdCameraTaskService {

    @Autowired
    private ConfVqdCameraTaskDao confVqdCameraTaskDao;

    @Autowired
    private ConfNodeSrvDao confNodeSrvDao;

    /**
     * 查询图像服务任务信息(不分页查询)
     * @return List<ConfVqdCameraTask>
     */
    @Override
    public List<ConfVqdCameraTask> getALL() {
        return confVqdCameraTaskDao.getALL();
    }

    /**
     * 查询图像服务任务信息(分页查询)
     * @return List<ConfVqdCameraTask>
     */
    @Override
    public PageInfo<ConfVqdCameraTask> getConfVqdCameraTaskList(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<ConfVqdCameraTask> pageList = confVqdCameraTaskDao.getALL();
        if(pageList != null && !pageList.isEmpty()){
            for (int i = 0; i < pageList.size(); i++) {
                String nodeName = confNodeSrvDao.getNodeName(pageList.get(i).getVqdsvrid());
                pageList.get(i).setNodename(nodeName);
            }
        }
        PageInfo<ConfVqdCameraTask> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    /**
     * 添加图像服务任务信息
     * @param record
     * @return int
     */
    @Override
    public int insertConfVqdCameraTaskInfo(ConfVqdCameraTask record) {
        return confVqdCameraTaskDao.insertConfVqdCameraTaskInfo(record);
    }

    /**
     * 修改图像服务任务信息
     * @param record
     * @return int
     */
    @Override
    public int updateConfVqdCameraTaskInfo(ConfVqdCameraTask record) {
        return confVqdCameraTaskDao.updateConfVqdCameraTaskInfo(record);
    }

    /**
     * 删除图像服务任务信息
     * @param record
     * @return int
     */
    @Override
    public int deleteConfVqdCameraTaskInfo(ConfVqdCameraTask record) {
        return confVqdCameraTaskDao.deleteConfVqdCameraTaskInfo(record);
    }

    /**
     * 获取某项任务配置下所属通道任务信息
     * @param taskNo
     * @return List<Map<String, Object>>
     */
    @Override
    public List<Map<String, Object>> getVqdCameraTaskInfoByTaskNo(String taskNo) {
        return confVqdCameraTaskDao.getVqdCameraTaskInfoByTaskNo(Integer.parseInt(taskNo));
    }

    /**
     * 获取最新的任务配置信息
     * @return ConfVqdCameraTask
     */
    @Override
    public ConfVqdCameraTask getConfVqdCameraTaskInfo() {
        return confVqdCameraTaskDao.getConfVqdCameraTaskInfo();
    }
}
