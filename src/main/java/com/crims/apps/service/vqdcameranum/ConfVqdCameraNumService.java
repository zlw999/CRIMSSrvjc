package com.crims.apps.service.vqdcameranum;

import com.crims.apps.model.confinfo.ConfVqdCameraNum;

import java.util.List;

public interface ConfVqdCameraNumService {

    List<ConfVqdCameraNum> getVqdCameraNumInfo();

    ConfVqdCameraNum getVqdCameraNumByTaskNo(Integer taskno,String nodeid);

    int insertVqdCameraNumInfo(ConfVqdCameraNum confVqdCameraNum);

    int updateVqdCameraNumInfo(ConfVqdCameraNum confVqdCameraNum);

    int deleteVqdCameraNumInfo(Integer taskno);
}
