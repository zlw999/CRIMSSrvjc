package com.crims.apps.service.vqdcamera;

import com.crims.apps.model.confinfo.ConfVqdCamera;

import java.util.List;
import java.util.Map;

public interface ConfVqdcameraService {

    int insertConfVqdCameraInfo(ConfVqdCamera confVqdCamera);

    int updateConfVqdCameraInfo(Integer taskNo);

    int deleteConfVqdCameraInfo(ConfVqdCamera confVqdCamera);

    List<Map<String,Object>> getVqdCameraInfoByChannelId(String taskNo);
}
