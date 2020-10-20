package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfSrvDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface ConfSrvDeviceDao {

    List<Map<String,Object>> getConfSrvDeviceInfo(String srvnodeid);

    int insertConfSrvDevice(@Param("list") ArrayList<ConfSrvDevice> srvDeviceList);

    int deleteConfSrvDeviceInfo();

}
