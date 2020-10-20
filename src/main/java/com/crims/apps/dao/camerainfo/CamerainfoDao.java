package com.crims.apps.dao.camerainfo;

import com.crims.apps.model.camerainfo.ConfCamerainfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CamerainfoDao {
    int update(ConfCamerainfo c);

    List<String> findType();

    String findValueById(@Param("deviceid") String deviceid, @Param("s")String s);

    void updateConf(@Param("type") String type, @Param("value") String value,@Param("deviceid") String deviceid);

    String find(String deviceid);

    void addConf(@Param("type") String type, @Param("val") String val,@Param("deviceid") String deviceid);
}
