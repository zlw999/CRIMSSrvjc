package com.crims.apps.controller.devicemonitor;

import com.crims.apps.common.Result;
import com.crims.apps.model.devicemonitor.*;
import com.crims.apps.service.devicemonitor.ConfAlarmThreshouldService;
import com.crims.apps.service.devicemonitor.ConfMonitorPointService;
import com.crims.apps.service.devicemonitor.ViewMonitorAlarmtypeService;
import com.crims.common.constants.BaseConst;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/conf/monitor")
public class MonitorController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConfMonitorPointService confMonitorPointService;

    @Autowired
    private ConfAlarmThreshouldService confAlarmThreshouldService;

    @Autowired
    private ViewMonitorAlarmtypeService viewMonitorAlarmtypeService;

    @PostMapping("addMonitor")
    @ResponseBody
    @ApiOperation(value = "添加监控点")
    public Result addMonitor(@RequestBody MonitorItemBody itemBody, String deviceId) {
        Result result = new Result();
        try {
            confMonitorPointService.addMonitor(itemBody, deviceId);
        } catch (Exception e) {
            logger.error("添加监控点失败", e);
            result.setCode("1");
            result.setMsg("必要参数缺失,或参数错误");
        }
        return result;
    }

    @GetMapping("list_Records_View")
    @ResponseBody
    @ApiOperation(value = "主页面-视图-alarmtype")
    public PageInfo<ViewMonitorAlarmType> list_Records_View(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                                            @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                            String deviceId, Integer operate, String locationCode, String mpType, String mpName) {
        return viewMonitorAlarmtypeService.list_Records(currentPage, pageSize, operate, locationCode, mpType, mpName,deviceId);
    }

    @GetMapping("getThresholdByMpid")
    @ResponseBody
    @ApiOperation(value = "根据监控点id得到门限list")
    public PageInfo<ConfAlarmthreshold> getThresholdByMpid(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                                           @RequestParam(required = false, defaultValue = "10") int pageSize, String mPid) {
        return confAlarmThreshouldService.getThresholdByMpid(currentPage, pageSize, mPid);
    }


    @PostMapping("insertThreshouldList")
    @ResponseBody
    @ApiOperation(value = "批量添加门限list")
    public Result insertThreshouldList(@RequestBody ArrayList<ConfAlarmthreshold> mxList, String userId, String userName) {
        Result result = new Result();
        try {
            boolean b = true;
            if (mxList != null && !mxList.isEmpty()) {
                boolean state=true;
               outter: for (int i = 0; i < mxList.size(); i++) {
                    mxList.get(i).setOperateaction(OperateCmdType.INSERT.getTypeVal());
                    ConfAlarmthreshold confAlarmthreshold = mxList.get(i);
                    Double downvalue = confAlarmthreshold.getDownvalue();
                    Double upvalue = confAlarmthreshold.getUpvalue();
                   inner: for(int j=i+1;j<mxList.size();j++){
                        ConfAlarmthreshold confAlarmthreshold1 = mxList.get(j);
                        Double downvalue1 = confAlarmthreshold1.getDownvalue();
                        Double upvalue1 = confAlarmthreshold1.getUpvalue();
                        if(upvalue>downvalue1&&downvalue<upvalue1){
                            state=false;
                            break outter;
                        }
                    }
                }
                if (!state) {
                    result.setMsg("同一监控点下门限不能重叠,请检查后重新添加");
                    result.setCode("1");
                    return result;
                }}
                confAlarmThreshouldService.insertThresholdList(mxList);
            }catch(Exception e){
                e.printStackTrace();
                logger.error("批量添加门限失败", e);
                result.setMsg("必要参数缺失,或参数错误");
                result.setCode("1");
            }
            return result;
    }

    /**
     * 根据：监控点id,上门限值，下门限值，删除监控点门限信息
     * @param mpid
     * @param upValue
     * @param downValue
     * @return
     */
    @PostMapping("deleteAlarmThreshold")
    @ResponseBody
    @ApiOperation(value = "根据：监控点id,上门限值，下门限值，删除监控点门限信息")
    public Result deleteAlarmThreshold(String mpid,String upValue,String downValue) {
        Result result = new Result();
        try {
            confAlarmThreshouldService.deleteAlarmThreshold(mpid, upValue, downValue);
        } catch (Exception e) {
            logger.error("删除监控点门限信息失败", e);
            result.setCode("1");
            result.setMsg("必要参数缺失,或参数错误");
        }
        return result;
    }

    /**
     * 根据：监控点id,下门限值，修改监控点门限信息
     * @param confAlarmthreshold
     * @return
     */
    @PostMapping("udpateAlarmThreshold")
    @ResponseBody
    @ApiOperation(value = "根据：监控点id,下门限值，修改监控点门限信息")
    public Result udpateAlarmThreshold(ConfAlarmthreshold confAlarmthreshold) {
        Result result = new Result();
        try {
            boolean flag = true;
            List<ConfAlarmthreshold> confAlarmthresholdList = confAlarmThreshouldService.getListByMpid(confAlarmthreshold.getMpid());
            if (confAlarmthresholdList != null && !confAlarmthresholdList.isEmpty()) {
                for (int i = 0; i <confAlarmthresholdList.size() ; i++) {
                    Double downValue = confAlarmthresholdList.get(i).getDownvalue();
                    Double upValue = confAlarmthresholdList.get(i).getUpvalue();
                    Double inputVal = confAlarmthreshold.getUpvalue();
                    if(inputVal>downValue && inputVal<upValue){
                        result.setCode("1");
                        result.setMsg("此门限设置重叠，已存在此类门限，请重新设置");
                        flag = false;
                        break;
                    }
                }
            }

            if(flag){
                confAlarmthreshold.setOperateaction(BaseConst.UPDATEACTION);
                confAlarmthreshold.setLastmodifytime(new Date());
                confAlarmThreshouldService.udpateAlarmThreshold(confAlarmthreshold);
            }
        } catch (Exception e) {
            result.setCode("1");
            result.setMsg("必要参数缺失,或参数错误");
        }
        return result;
    }

    @GetMapping("getMonitorPointList")
    @ResponseBody
    @ApiOperation(value = "根据设备id获取监控点信息")
    public PageInfo<ConfMonitorPoint> getMonitorPointList(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                                            @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                            String deviceId) {
        return confMonitorPointService.getMonitorPointList(currentPage, pageSize, deviceId);
    }

}