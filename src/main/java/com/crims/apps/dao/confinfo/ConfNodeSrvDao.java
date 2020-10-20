package com.crims.apps.dao.confinfo;

import com.crims.apps.model.confinfo.ConfNodeSrv;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfNodeSrvDao {

    List<ConfNodeSrv> getALL();

    String getNodeName(String nodeId);

    int insertConfNodeSrvInfo(ConfNodeSrv record);

    int updateConfNodeSrvInfo(ConfNodeSrv record);

    int deleteConfNodeSrvInfo(ConfNodeSrv record);

}