package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfVqdCamera;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConfVqdCameraDao {

    List<Map<String,Object>> getVqdCameraInfoByChannelId(Integer taskNo);

    int insertConfVqdCameraInfo(ConfVqdCamera confVqdCamera);

    int updateConfVqdCameraInfo(Integer taskNo);

    int deleteConfVqdCameraInfo(ConfVqdCamera confVqdCamera);

}
