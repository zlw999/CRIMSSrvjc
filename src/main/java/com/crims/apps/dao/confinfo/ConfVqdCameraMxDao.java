package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfVqdCameraMx;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfVqdCameraMxDao {

    List<ConfVqdCameraMx> getALL();

    int insertConfVqdCameraMxInfo(ConfVqdCameraMx record);

    int updateConfVqdCameraMxInfo(ConfVqdCameraMx record);

    int deleteConfVqdCameraMxInfo(ConfVqdCameraMx record);

}