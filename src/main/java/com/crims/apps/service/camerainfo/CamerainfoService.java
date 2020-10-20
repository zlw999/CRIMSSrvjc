package com.crims.apps.service.camerainfo;

import com.crims.apps.model.camerainfo.ConfCamerainfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CamerainfoService {
    int update(ConfCamerainfo c);

    List<String> findType();

    String findValueById(String deviceid,String s);

    void updateConf(String type, String value,String deviceid);

    String find(String deviceid);

    void addConf(String type, String val, String deviceid);
}
