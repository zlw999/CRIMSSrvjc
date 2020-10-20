package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfVqdCameraTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConfVqdCameraTaskDao {

    List<ConfVqdCameraTask> getALL();

    int insertConfVqdCameraTaskInfo(ConfVqdCameraTask record);

    int updateConfVqdCameraTaskInfo(ConfVqdCameraTask record);

    int deleteConfVqdCameraTaskInfo(ConfVqdCameraTask record);

    List<Map<String,Object>> getVqdCameraTaskInfoByTaskNo(Integer taskNo);

    ConfVqdCameraTask getConfVqdCameraTaskInfo();

}