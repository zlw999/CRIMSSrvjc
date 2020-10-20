package com.crims.apps.controller.vqdcameramx;

import com.crims.apps.common.Result;
import com.crims.apps.model.confinfo.ConfVqdCameraMx;
import com.crims.apps.model.operlog.OperateLogInfo;
import com.crims.apps.service.operlog.OperateLogInfoService;
import com.crims.apps.service.vqdcameramx.ConfVqdCameraMxService;
import com.crims.common.constants.BaseConst;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/confvqdcameramx")
public class ConfVqdCameraMxController {

    public static Logger logger = LoggerFactory.getLogger(ConfVqdCameraMxController.class);

    @Autowired
    private ConfVqdCameraMxService confVqdCameraMxService;

    @Autowired
    private OperateLogInfoService operateLogInfoService;

    /**
     * 查询设备门限配置信息(不分页)
     * @return List<ConfVqdCameraMx>对象集以JSON格式输出
     */
    @GetMapping("/getALL")
    @ResponseBody
    @ApiOperation(value = "查询设备门限配置信息(不分页查询)", notes = "confvqdcameramx_info")
    public List<ConfVqdCameraMx> getALL(){
        return confVqdCameraMxService.getALL();
    }

    /**
     * 查询设备门限配置信息(分页)
     * @param loginUserID 用户ID
     * @param loginUserName 用户名称
     * @param currentPage 第几页
     * @param pageSize 每页条数
     * @return List<ConfVqdCameraMx>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getConfVqdCameraMxList")
    @ResponseBody
    @ApiOperation(value = "查询设备门限配置信息(分页查询)")
    public PageInfo<ConfVqdCameraMx> getConfVqdCameraMxList(String loginUserID, String loginUserName,
                                                                @RequestParam(required = false, defaultValue = "0") int currentPage,
                                                                @RequestParam(required = false, defaultValue = "10") int pageSize) {
        // 添加日志
        OperateLogInfo operateLogInfo = new OperateLogInfo();
        operateLogInfo.setUserid(loginUserID);
        operateLogInfo.setUsername(loginUserName);
        operateLogInfo.setOperatetime(new Date());
        operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfVqdCameraMxInfo);
        operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfVqdCameraMxInfo_Query);
        operateLogInfo.setOperatedsp("查询设备门限配置信息");
        operateLogInfoService.add_OperateLog(operateLogInfo);
        return confVqdCameraMxService.getConfVqdCameraMxList(currentPage,pageSize);
    }

    /**
     * 添加设备门限配置信息
     * @param confVqdCameraMx
     * @return Result结果集
     */
    @RequestMapping(value = "/insertConfVqdCameraMxInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加设备门限配置信息")
    public Result<Object> insertConfVqdCameraMxInfo(String loginUserID, String loginUserName, ConfVqdCameraMx confVqdCameraMx){
        Result<Object> result = new Result<Object>();
        try{
            confVqdCameraMx.setOperateaction(BaseConst.INSERTACTION);//操作标志 1 添加
            confVqdCameraMx.setLastmodifytime(new Date());
            confVqdCameraMxService.insertConfVqdCameraMxInfo(confVqdCameraMx);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfVqdCameraMxInfo);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfVqdCameraMxInfo_Add);
            operateLogInfo.setOperatedsp("添加设备门限配置信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch(Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 修改设备门限配置信息
     * @param confVqdCameraMx
     * @return Result结果集
     */
    @PostMapping("/updateConfVqdCameraMxInfo")
    @ResponseBody
    @ApiOperation("修改设备门限配置信息")
    public Result<Object> updateConfVqdCameraMxInfo(String loginUserID, String loginUserName, ConfVqdCameraMx confVqdCameraMx){
        Result<Object> result = new Result<>();
        try {
            confVqdCameraMx.setOperateaction(BaseConst.UPDATEACTION);//操作标志 2 修改
            confVqdCameraMx.setLastmodifytime(new Date());
            confVqdCameraMxService.updateConfVqdCameraMxInfo(confVqdCameraMx);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfVqdCameraMxInfo);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfVqdCameraMxInfo_Update);
            operateLogInfo.setOperatedsp("修改设备门限配置信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 删除设备门限配置信息
     * @param confVqdCameraMx
     * @return Result结果集
     */
    @PostMapping("/deleteConfVqdCameraMxInfo")
    @ResponseBody
    @ApiOperation("删除设备门限配置信息")
    public Result<Object> deleteConfVqdCameraMxInfo(String loginUserID,String loginUserName,ConfVqdCameraMx confVqdCameraMx){
        Result<Object> result = new Result<>();
        try {
            confVqdCameraMx.setOperateaction(BaseConst.DELETEACTION);//操作标志 3 删除
            confVqdCameraMx.setUsestate(BaseConst.UNUSESTATE);
            confVqdCameraMx.setLastmodifytime(new Date());
            confVqdCameraMxService.deleteConfVqdCameraMxInfo(confVqdCameraMx);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfVqdCameraMxInfo);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfVqdCameraMxInfo_Delete);
            operateLogInfo.setOperatedsp("删除设备门限配置信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }
}
