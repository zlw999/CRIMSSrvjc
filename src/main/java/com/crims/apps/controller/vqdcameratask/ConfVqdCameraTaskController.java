package com.crims.apps.controller.vqdcameratask;

import com.crims.apps.common.Result;
import com.crims.apps.model.confinfo.*;
import com.crims.apps.model.operlog.OperateLogInfo;
import com.crims.apps.service.operlog.OperateLogInfoService;
import com.crims.apps.service.vqdcamera.ConfVqdcameraService;
import com.crims.apps.service.vqdcameranum.ConfVqdCameraNumService;
import com.crims.apps.service.vqdcameratask.ConfVqdCameraTaskService;
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
import java.util.Map;

@Controller
@RequestMapping("/confvqdcamera")
public class ConfVqdCameraTaskController {

    public static Logger logger = LoggerFactory.getLogger(ConfVqdCameraTaskController.class);

    @Autowired
    private ConfVqdCameraTaskService confVqdCameraTaskService;

    @Autowired
    private ConfVqdcameraService confVqdcameraService;

    @Autowired
    private ConfVqdCameraNumService confVqdCameraNumService;

    @Autowired
    private OperateLogInfoService operateLogInfoService;

    /**
     * 查询图像服务任务信息(不分页查询)
     * @return List<ConfVqdCameraTask>对象集以JSON格式输出
     */
    @GetMapping("/getALL")
    @ResponseBody
    @ApiOperation(value = "查询图像服务任务信息(不分页查询)", notes = "confvqdcameratask_info")
    public List<ConfVqdCameraTask> getALL(){
        return confVqdCameraTaskService.getALL();
    }

    /**
     * 查询图像服务任务信息(分页查询)
     * @param loginUserID 用户ID
     * @param loginUserName 用户名称
     * @param currentPage 第几页
     * @param pageSize 每页条数
     * @return List<ConfVqdCameraTask>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getConfVqdCameraTaskList")
    @ResponseBody
    @ApiOperation(value = "查询图像服务任务信息(分页查询)")
    public PageInfo<ConfVqdCameraTask> getConfVqdCameraTaskList(String loginUserID, String loginUserName,
                                                    @RequestParam(required = false, defaultValue = "0") int currentPage,
                                                    @RequestParam(required = false, defaultValue = "10") int pageSize) {
        // 添加日志
        OperateLogInfo operateLogInfo = new OperateLogInfo();
        operateLogInfo.setUserid(loginUserID);
        operateLogInfo.setUsername(loginUserName);
        operateLogInfo.setOperatetime(new Date());
        operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfVqdCameraTaskInfo);
        operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfVqdCameraTaskInfo_Query);
        operateLogInfo.setOperatedsp("查询图像服务任务信息");
        operateLogInfoService.add_OperateLog(operateLogInfo);
        return confVqdCameraTaskService.getConfVqdCameraTaskList(currentPage,pageSize);
    }

    /**
     * 添加图像服务任务信息
     * @param confVqdCameraTask
     * @return Result结果集
     */
    @RequestMapping(value = "/insertConfVqdCameraTaskInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加图像服务任务信息")
    public Result<Object> insertConfVqdCameraTaskInfo(String loginUserID, String loginUserName, @RequestBody ConfVqdCameraTask confVqdCameraTask){
        Result<Object> result = new Result<>();
        try{
            confVqdCameraTask.setOperateaction(BaseConst.INSERTACTION);//操作标志 1 添加
            confVqdCameraTask.setLastmodifytime(new Date());
            //添加任务信息表conf_vqd_camera_task
            int num = confVqdCameraTaskService.insertConfVqdCameraTaskInfo(confVqdCameraTask);
            //获取最新的图像服务信息
            ConfVqdCameraTask confVqdCameraTaskNew = confVqdCameraTaskService.getConfVqdCameraTaskInfo();
            if(num > 0 && confVqdCameraTaskNew != null && confVqdCameraTask.getVqdsvrid().equals(confVqdCameraTaskNew.getVqdsvrid())){
                List<ConfVqdCamera> confVqdCameraList = confVqdCameraTask.getConfVqdCameraList();
                if(confVqdCameraList != null && !confVqdCameraList.isEmpty()){
                    for (int i = 0; i < confVqdCameraList.size(); i++) {
                        confVqdCameraList.get(i).setOperateaction(BaseConst.INSERTACTION);//操作标志 1 添加
                        confVqdCameraList.get(i).setLastmodifytime(new Date());
                        confVqdCameraList.get(i).setTaskNo(confVqdCameraTaskNew.getTaskno());
                        //添加任务信息通道表conf_vqd_camera
                        confVqdcameraService.insertConfVqdCameraInfo(confVqdCameraList.get(i));

                        //添加任务信息通道数量表conf_vqd_cameranum
                        updateConfVqdCameraNum(confVqdCameraList.get(i).getDeviceid(),confVqdCameraTaskNew.getTaskno());
                    }
                }
            }

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfVqdCameraTaskInfo);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfVqdCameraTaskInfo_Add);
            operateLogInfo.setOperatedsp("添加图像服务任务信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch(Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 修改图像服务任务信息
     * @param confVqdCameraTask
     * @return Result结果集
     */
    @PostMapping("/updateConfVqdCameraTaskInfo")
    @ResponseBody
    @ApiOperation("修改图像服务任务信息")
    public Result<Object> updateConfVqdCameraTaskInfo(String loginUserID, String loginUserName, @RequestBody ConfVqdCameraTask confVqdCameraTask){
        Result<Object> result = new Result<>();
        try {
            confVqdCameraTask.setOperateaction(BaseConst.UPDATEACTION);//操作标志 2 修改
            confVqdCameraTask.setLastmodifytime(new Date());
            //更新任务信息表conf_vqd_camera_task
            int num = confVqdCameraTaskService.updateConfVqdCameraTaskInfo(confVqdCameraTask);
            if(num > 0){
                //更新任务信息通道表conf_vqd_camera
                confVqdcameraService.updateConfVqdCameraInfo(confVqdCameraTask.getTaskno());
                confVqdCameraNumService.deleteVqdCameraNumInfo(confVqdCameraTask.getTaskno());
                List<ConfVqdCamera> confVqdCameraInfoList = confVqdCameraTask.getConfVqdCameraList();
                if(confVqdCameraInfoList != null && !confVqdCameraInfoList.isEmpty()){
                    for (int i = 0; i < confVqdCameraInfoList.size(); i++) {
                        confVqdCameraInfoList.get(i).setTaskNo(confVqdCameraTask.getTaskno());
                        confVqdCameraInfoList.get(i).setLastmodifytime(new Date());
                        confVqdCameraInfoList.get(i).setOperateaction(BaseConst.INSERTACTION);//操作标志 1 添加
                        //添加任务信息通道表conf_vqd_camera
                        confVqdcameraService.insertConfVqdCameraInfo(confVqdCameraInfoList.get(i));

                        //更新任务信息通道数量表conf_vqd_cameranum
                        updateConfVqdCameraNum(confVqdCameraInfoList.get(i).getDeviceid(),confVqdCameraTask.getTaskno());
                    }
                }
            }

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfVqdCameraTaskInfo);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfVqdCameraTaskInfo_Update);
            operateLogInfo.setOperatedsp("修改图像服务任务信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 删除图像服务任务信息
     * @param confVqdCameraTask
     * @return Result结果集
     */
    @PostMapping("/deleteConfVqdCameraTaskInfo")
    @ResponseBody
    @ApiOperation("删除图像服务任务信息")
    public Result<Object> deleteConfVqdCameraTaskInfo(String loginUserID,String loginUserName,ConfVqdCameraTask confVqdCameraTask){
        Result<Object> result = new Result<>();
        try {
            confVqdCameraTask.setOperateaction(BaseConst.DELETEACTION);//操作标志 3 删除
            confVqdCameraTask.setUsestate(BaseConst.UNUSESTATE);
            confVqdCameraTask.setLastmodifytime(new Date());
            int num = confVqdCameraTaskService.deleteConfVqdCameraTaskInfo(confVqdCameraTask);
            if(num > 0){
                ConfVqdCamera confVqdCamera = new ConfVqdCamera();
                confVqdCamera.setOperateaction(BaseConst.DELETEACTION);//操作标志 3 删除
                confVqdCamera.setLastmodifytime(new Date());
                confVqdCamera.setUsestate(confVqdCameraTask.getUsestate());
                confVqdCamera.setTaskNo(confVqdCameraTask.getTaskno());
                confVqdcameraService.deleteConfVqdCameraInfo(confVqdCamera);
            }

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_ConfVqdCameraTaskInfo);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_ConfVqdCameraTaskInfo_Delete);
            operateLogInfo.setOperatedsp("删除图像服务任务信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 获取某项任务配置下所属通道任务信息
     * @return Result
     */
    @PostMapping("/getVqdCameraTaskInfoByTaskNo")
    @ResponseBody
    @ApiOperation("获取某项任务配置下所属通道任务信息")
    public Result<Object> getVqdCameraTaskInfoByTaskNo(String taskNo){
        Result<Object> result = new Result<>();
        try {
            List<Map<String,Object>> channelCameraTaskInfoList = confVqdCameraTaskService.getVqdCameraTaskInfoByTaskNo(taskNo);
            result.setResult(channelCameraTaskInfoList);
        } catch (Exception e) {
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 查询通道数量信息
     * @return List<ConfVqdCameraNum>对象集以JSON格式输出
     */
    @GetMapping("/getVqdCameraNumInfo")
    @ResponseBody
    @ApiOperation(value = "查询通道数量信息(查所有)", notes = "confVqdCameraNum_info")
    public List<ConfVqdCameraNum> getVqdCameraNumInfo(){
        return confVqdCameraNumService.getVqdCameraNumInfo();
    }

    /**
     * 根据任务编号和节点编号查询通道数量信息
     * @return ConfVqdCameraNum对象集以JSON格式输出
     */
    @GetMapping("/getVqdCameraNumByTaskNo")
    @ResponseBody
    @ApiOperation(value = "根据任务编号和节点编号查询通道数量信息", notes = "confVqdCameraNum_info")
    public ConfVqdCameraNum getVqdCameraNumByTaskNo(String taskNo,String nodeid){
        return confVqdCameraNumService.getVqdCameraNumByTaskNo(Integer.parseInt(taskNo),nodeid);
    }

    /**
     * 根据任务编号获取通道ID和通道名称
     * @return List<Map<String,Object>>
     */
    @GetMapping("/getVqdCameraInfoByChannelId")
    @ResponseBody
    @ApiOperation(value = "根据任务编号获取通道ID和通道名称", notes = "confVqdCamera_info")
    public List<Map<String,Object>> getVqdCameraInfoByChannelId(String taskNo){
        return confVqdcameraService.getVqdCameraInfoByChannelId(taskNo);
    }

    /**
     * 更新任务通道信息数量表
     * @param deviceId
     * @param taskNo
     */
    public void updateConfVqdCameraNum(String deviceId,int taskNo){
        String nodeId = deviceId.substring(2,6);
        String nodeIdI = deviceId.substring(4,6);
        String nodeIdII = deviceId.substring(0,6);
        if("0000".equals(nodeId)){//添加核心区域节点通道数量
            String nodeIdStr = deviceId.substring(0,2);
            checkConfVqdCameraNum(nodeIdStr,taskNo);
        }
        if("00".equals(nodeIdI) && !"0000".equals(nodeId)){//I类节点通道数量添加，同时添加核心区域节点通道数量
            String nodeIdIStr = deviceId.substring(0,4);
            checkConfVqdCameraNum(nodeIdIStr,taskNo);
            String nodeIdStr = deviceId.substring(0,2);
            checkConfVqdCameraNum(nodeIdStr,taskNo);
        }

        if(!"00".equals(nodeIdI) && !"0000".equals(nodeId)) {//II类节点通道数量添加，同时添加核心区域和一类节点通道数量
            checkConfVqdCameraNum(nodeIdII,taskNo);
            String nodeIdIStr = deviceId.substring(0,4);
            checkConfVqdCameraNum(nodeIdIStr,taskNo);
            String nodeIdStr = deviceId.substring(0,2);
            checkConfVqdCameraNum(nodeIdStr,taskNo);
        }
    }

    /**
     * 更新任务通道数量信息表
     * @param nodeId
     * @param taskNo
     */
    public void checkConfVqdCameraNum(String nodeId,int taskNo){
        ConfVqdCameraNum confVqdCameraNum = confVqdCameraNumService.getVqdCameraNumByTaskNo(taskNo,nodeId);
        if(confVqdCameraNum != null){
            int channelnum = confVqdCameraNum.getVideochannelnum() + 1;
            confVqdCameraNum.setVideochannelnum(channelnum);
            confVqdCameraNum.setNodeid(nodeId);
            confVqdCameraNum.setTaskno(taskNo);
            confVqdCameraNumService.updateVqdCameraNumInfo(confVqdCameraNum);
        }else{
            ConfVqdCameraNum cVqdCameraNum = new ConfVqdCameraNum();
            cVqdCameraNum.setVideochannelnum(1);
            cVqdCameraNum.setNodeid(nodeId);
            cVqdCameraNum.setTaskno(taskNo);
            confVqdCameraNumService.insertVqdCameraNumInfo(cVqdCameraNum);
        }
    }
}
