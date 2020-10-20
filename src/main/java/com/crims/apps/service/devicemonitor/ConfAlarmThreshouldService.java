package com.crims.apps.service.devicemonitor;

import com.crims.apps.model.devicemonitor.ConfAlarmthreshold;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface ConfAlarmThreshouldService {

    /**
     * 根据mpid取得相对得门限
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<ConfAlarmthreshold> getThresholdByMpid(int currentPage, int pageSize, String mPid);

    /**
     * 批量添加
     * @param mxList
     */
    void insertThresholdList(ArrayList<ConfAlarmthreshold> mxList);

    /**
     * 根据mpid得到list
     * @param mPid
     * @return
     */
    List<ConfAlarmthreshold> getListByMpid(String mPid);

    /**
     * 删除监控点门限信息
     * @param mpid
     * @param upValue
     * @param downValue
     * @return
     */
    int deleteAlarmThreshold(String mpid,String upValue,String downValue);

    /**
     * 修改门限信息
      * @param confAlarmthreshold
     * @return
     */
    int udpateAlarmThreshold(ConfAlarmthreshold confAlarmthreshold);
}
