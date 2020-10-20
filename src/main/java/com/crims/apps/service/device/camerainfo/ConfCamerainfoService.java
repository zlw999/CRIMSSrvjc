package com.crims.apps.service.device.camerainfo;

import com.crims.apps.common.struct.CommonColumn;

import java.util.List;

public interface ConfCamerainfoService {

    List<String> getColumnName();

    void insertConfCameraInfo(List<CommonColumn> list);

}
