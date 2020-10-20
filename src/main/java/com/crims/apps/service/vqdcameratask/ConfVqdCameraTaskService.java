package com.crims.apps.service.vqdcameratask;

import com.crims.apps.model.confinfo.ConfVqdCameraTask;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ConfVqdCameraTaskService {

    List<ConfVqdCameraTask> getALL();

    PageInfo<ConfVqdCameraTask> getConfVqdCameraTaskList(int currentPage, int pageSize);

    int insertConfVqdCameraTaskInfo(ConfVqdCameraTask record);

    int updateConfVqdCameraTaskInfo(ConfVqdCameraTask record);

    int deleteConfVqdCameraTaskInfo(ConfVqdCameraTask record);

    List<Map<String,Object>> getVqdCameraTaskInfoByTaskNo(String taskNo);

    ConfVqdCameraTask getConfVqdCameraTaskInfo();
}
