package com.crims.apps.service.nodesrv;

import com.crims.apps.model.confinfo.ConfNodeSrv;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ConfNodeSrvService {

    List<ConfNodeSrv> getALL();

    PageInfo<ConfNodeSrv> getConfNodeSrvList(int currentPage, int pageSize);

    int insertConfNodeSrvInfo(ConfNodeSrv record);

    int updateConfNodeSrvInfo(ConfNodeSrv record);

    int deleteConfNodeSrvInfo(ConfNodeSrv record);

}
