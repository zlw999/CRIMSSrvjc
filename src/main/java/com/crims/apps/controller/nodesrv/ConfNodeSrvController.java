package com.crims.apps.controller.nodesrv;

import com.crims.apps.common.Result;
import com.crims.apps.model.confinfo.ConfNodeSrv;
import com.crims.apps.model.operlog.OperateLogInfo;
import com.crims.apps.service.nodesrv.ConfNodeSrvService;
import com.crims.apps.service.operlog.OperateLogInfoService;
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
@RequestMapping("/nodeconfsrv")
public class ConfNodeSrvController {

    public static Logger logger = LoggerFactory.getLogger(ConfNodeSrvController.class);

    @Autowired
    private ConfNodeSrvService confNodeSrvService;

    @Autowired
    private OperateLogInfoService operateLogInfoService;

    /**
     * 查询节点服务信息(不分页查询)
     * @return List<ConfNodeSrv>对象集以JSON格式输出
     */
    @GetMapping("/getALL")
    @ResponseBody
    @ApiOperation(value = "查询节点服务信息(不分页查询)", notes = "confnodesrv_info")
    public List<ConfNodeSrv> getALL(){
        return confNodeSrvService.getALL();
    }

    /**
     * 查询节点服务信息(分页查询)
     * @param loginUserID 用户ID
     * @param loginUserName 用户名称
     * @param currentPage 第几页
     * @param pageSize 每页条数
     * @return List<ConfNodeSrv>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getConfNodeSrvList")
    @ResponseBody
    @ApiOperation(value = "查询节点服务信息(分页查询)")
    public PageInfo<ConfNodeSrv> getConfNodeSrvList(String loginUserID, String loginUserName,
                                              @RequestParam(required = false, defaultValue = "0") int currentPage,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize) {
        // 添加日志
        OperateLogInfo operateLogInfo = new OperateLogInfo();
        operateLogInfo.setUserid(loginUserID);
        operateLogInfo.setUsername(loginUserName);
        operateLogInfo.setOperatetime(new Date());
        operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfNodeSrvInfo);
        operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfNodeSrvInfo_Query);
        operateLogInfo.setOperatedsp("查询节点服务信息");
        operateLogInfoService.add_OperateLog(operateLogInfo);
        return confNodeSrvService.getConfNodeSrvList(currentPage,pageSize);
    }

    /**
     * 添加节点服务信息
     * @param confNodeSrv
     * @return Result结果集
     */
    @RequestMapping(value = "/insertConfNodeSrvInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加节点服务信息")
    public Result<Object> insertConfNodeSrvInfo(String loginUserID, String loginUserName, ConfNodeSrv confNodeSrv){
        Result<Object> result = new Result<Object>();
        try{
            confNodeSrv.setOperateaction(BaseConst.INSERTACTION);//操作标志 1 添加
            confNodeSrv.setLastmodifytime(new Date());
            confNodeSrvService.insertConfNodeSrvInfo(confNodeSrv);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfNodeSrvInfo);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfNodeSrvInfo_Add);
            operateLogInfo.setOperatedsp("添加节点服务信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch(Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 修改节点服务信息
     * @param confNodeSrv
     * @return Result结果集
     */
    @PostMapping("/updateConfNodeSrvInfo")
    @ResponseBody
    @ApiOperation("修改节点服务信息")
    public Result<Object> updateConfNodeSrvInfo(String loginUserID, String loginUserName, ConfNodeSrv confNodeSrv){
        Result<Object> result = new Result<>();
        try {
            confNodeSrv.setOperateaction(BaseConst.UPDATEACTION);//操作标志 2 修改
            confNodeSrv.setLastmodifytime(new Date());
            confNodeSrvService.updateConfNodeSrvInfo(confNodeSrv);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfNodeSrvInfo);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfNodeSrvInfo_Update);
            operateLogInfo.setOperatedsp("修改节点服务信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 删除节点服务信息
     * @param confNodeSrv
     * @return Result结果集
     */
    @PostMapping("/deleteConfNodeSrvInfo")
    @ResponseBody
    @ApiOperation("删除节点服务信息")
    public Result<Object> deleteConfNodeSrvInfo(String loginUserID,String loginUserName,ConfNodeSrv confNodeSrv){
        Result<Object> result = new Result<>();
        try {
            confNodeSrv.setOperateaction(BaseConst.DELETEACTION);//操作标志 3 删除
            confNodeSrv.setUsestate(BaseConst.UNUSESTATE);
            confNodeSrv.setLastmodifytime(new Date());
            confNodeSrvService.deleteConfNodeSrvInfo(confNodeSrv);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfNodeSrvInfo);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfNodeSrvInfo_Delete);
            operateLogInfo.setOperatedsp("删除节点服务信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }
}
