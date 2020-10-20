package com.crims.apps.controller.alarminfo;

import com.crims.apps.model.alarminfo.AlarmInfo;
import com.crims.apps.service.alarminfo.AlarmInfoService;
import com.crims.dbconfig.DataSourceContextHolder;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/alarminfo")
public class AlarmInfoController {

    public static Logger logger = LoggerFactory.getLogger(AlarmInfoController.class);

    @Autowired
    private AlarmInfoService alarmInfoService;

    /**
     * 查询告警信息(分页查询)
     * @param nodeNameStr 节点名称
     * @param deviceName 设备名称
     * @param ipAddress ip
     * @param alarmType 告警类型
     * @param alarmLevel 告警级别
     * @param mpNameOr 测点名称
     * @param AlarmAffirmUseridOr 确认人
     * @param alarmtime 告警产生时间
     * @param alarmdistime 告警结束时间
     * @param alarmaffirmtime 告警确认时间
     * @param currentPage
     * @param pageSize
     * @return PageInfo<AlarmInfo>
     */
    @PostMapping("/getAlarmInfoList")
    @ResponseBody
    @ApiOperation(value = "查询告警信息(分页查询)", notes = "AlarmInfo")
    public PageInfo<AlarmInfo> getAlarmInfoList(String nodeNameStr,String deviceName,String ipAddress,String alarmType,String alarmLevel,
                                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date alarmtime,
                                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date alarmdistime,
                                                String alarmaffirmtime,
                                                String isendtime,
                                                @RequestParam(required = false, defaultValue = "0") int currentPage,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                String mpNameOr,String AlarmAffirmUseridOr,String nodeNameOr,String deviceNameOr,
                                                String ipAddressOr,String alarmTypeOr,String alarmLevelOr,String alarmtimeOr,String alarmdistimeOr,String alarmaffirmtimeOr,String orderRule) {
        DataSourceContextHolder.setKey("crimsrec");
        PageInfo<AlarmInfo> alarmInfoPageInfo = alarmInfoService.getAlarmInfoList(nodeNameStr,deviceName,ipAddress,alarmType,alarmLevel,alarmtime,alarmdistime,alarmaffirmtime,isendtime,currentPage,pageSize,
                mpNameOr,AlarmAffirmUseridOr,nodeNameOr,deviceNameOr,ipAddressOr,alarmTypeOr,alarmLevelOr,alarmtimeOr,alarmdistimeOr,alarmaffirmtimeOr,orderRule);
        DataSourceContextHolder.clearKey();
        return alarmInfoPageInfo;
    }

}
