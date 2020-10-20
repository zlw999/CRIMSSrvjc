package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfVqdCameraNum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfVqdCameraNumDao {

    List<ConfVqdCameraNum> getVqdCameraNumInfo();

    ConfVqdCameraNum getVqdCameraNumByTaskNo(Integer taskno,String nodeid);

    int insertVqdCameraNumInfo(ConfVqdCameraNum confVqdCameraNum);

    int updateVqdCameraNumInfo(ConfVqdCameraNum confVqdCameraNum);

    int deleteVqdCameraNumInfo(Integer taskno);
}
