package com.crims.apps.service.vqdcameramx;

import com.crims.apps.model.confinfo.ConfVqdCameraMx;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ConfVqdCameraMxService {

    List<ConfVqdCameraMx> getALL();

    PageInfo<ConfVqdCameraMx> getConfVqdCameraMxList(int currentPage, int pageSize);

    int insertConfVqdCameraMxInfo(ConfVqdCameraMx record);

    int updateConfVqdCameraMxInfo(ConfVqdCameraMx record);

    int deleteConfVqdCameraMxInfo(ConfVqdCameraMx record);

}
