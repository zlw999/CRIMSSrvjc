package com.crims.apps.dao.confinfo;

import com.crims.apps.common.struct.CommonColumn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfCamerainfoDao {

    List<String> getColumnName();


    void insertConfCameraInfo(CommonColumn commonColumn);

}
