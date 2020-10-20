package com.crims.apps.controller.videotaperule;

import com.crims.apps.common.Result;
import com.crims.apps.model.confinfo.ConfVideotapeRule;
import com.crims.apps.model.operlog.OperateLogInfo;
import com.crims.apps.service.operlog.OperateLogInfoService;
import com.crims.apps.service.videotaperule.ConfVideotapeRuleService;
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
@RequestMapping("/videotaperule")
public class ConfVideotapeRuleController {

    public static Logger logger = LoggerFactory.getLogger(ConfVideotapeRuleController.class);

    @Autowired
    private ConfVideotapeRuleService confVideotapeRuleService;

    @Autowired
    private OperateLogInfoService operateLogInfoService;

    /**
     * 查询所有通道录像时间规则信息(不分页查询)
     * @return List<ConfVideotapeRule>对象SON格式输出
     */
    @GetMapping("/getConfVideotapeRuleList")
    @ResponseBody
    @ApiOperation(value = "查询所有通道录像时间规则信息(不分页查询)", notes = "ConfVideotapeRule_info")
    public List<ConfVideotapeRule> getConfVideotapeRuleList() {
        return confVideotapeRuleService.getConfVideotapeRuleList();
    }

    /**
     * 查询所有通道录像时间规则信息(分页查询)
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfVideotapeRule>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getAll")
    @ResponseBody
    @ApiOperation(value = "查询所有通道录像时间规则信息(分页查询)", notes = "ConfVideotapeRule_info")
    public PageInfo<ConfVideotapeRule> getAll(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                       @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<ConfVideotapeRule> confVideotapeRuleList = confVideotapeRuleService.getAll(currentPage, pageSize);
        return confVideotapeRuleList;
    }

    /**
     * 根据通道ID查询通道录像时间规则信息
     * @param loginUserID
     * @param loginUserName
     * @param channelID
     * @return result
     */
    @PostMapping("/getConfVideotapeRuleInfo")
    @ResponseBody
    @ApiOperation("根据通道ID查询通道录像时间规则信息")
    public Result getConfVideotapeRuleInfo(String loginUserID,String loginUserName,String channelID){
        Result<Object> result = new Result<Object>();
        try {
            ConfVideotapeRule confVideotapeRule = confVideotapeRuleService.getConfVideotapeRuleInfo(channelID);
            result.setCode("0");
            result.setMsg("success");
            result.setResult(confVideotapeRule);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_VideotapeRule);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_VideotapeRule_Query);
            operateLogInfo.setOperatedsp("根据通道ID查询通道录像时间规则信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        } catch (Exception e) {
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }
        return result;
    }

    /**
     * 添加通道录像时间规则信息
     * @param loginUserID
     * @param loginUserName
     * @param confVideotapeRuleList
     * @return Result结果集
     */
    @RequestMapping(value = "/insertConfVideotapeRuleInfo",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加通道录像时间规则信息")
    public Result<Object> insertConfVideotapeRuleInfo(String loginUserID,String loginUserName,@RequestBody List<ConfVideotapeRule> confVideotapeRuleList){
        Result<Object> result = new Result<>();
        try {
            if(confVideotapeRuleList != null && !confVideotapeRuleList.isEmpty()){
                for(int i = 0;i<confVideotapeRuleList.size();i++){
                    confVideotapeRuleList.get(i).setOperateaction(BaseConst.INSERTACTION);//操作标志 1 添加
                    confVideotapeRuleList.get(i).setOperateuserid(loginUserID);
                    confVideotapeRuleList.get(i).setOperateusername(loginUserName);
                    confVideotapeRuleList.get(i).setLastmodifytime(new Date());
                    confVideotapeRuleService.insertConfVideotapeRuleInfo(confVideotapeRuleList.get(i));
                }

                // 添加日志
                OperateLogInfo operateLogInfo = new OperateLogInfo();
                operateLogInfo.setUserid(loginUserID);
                operateLogInfo.setUsername(loginUserName);
                operateLogInfo.setOperatetime(new Date());
                operateLogInfo.setOperatetype(BaseConst.MsgCmdType_VideotapeRule);
                operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_VideotapeRule_Add);
                operateLogInfo.setOperatedsp("添加通道录像时间规则信息");
                operateLogInfoService.add_OperateLog(operateLogInfo);
            }
        } catch (Exception e) {
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }
        return result;
    }

    /**
     * 修改通道录像时间规则信息
     * @param loginUserID
     * @param loginUserName
     * @param confVideotapeRule
     * @return Result结果集
     */
    @PostMapping("/updateConfVideotapeRule")
    @ResponseBody
    @ApiOperation("修改通道录像时间规则信息")
    public Result<Object> updateConfVideotapeRule(String loginUserID,String loginUserName,ConfVideotapeRule confVideotapeRule){
        Result<Object> result = new Result<>();
        try {
            confVideotapeRule.setOperateaction(BaseConst.UPDATEACTION);//操作标志 2 修改
            confVideotapeRule.setLastmodifytime(new Date());
            confVideotapeRuleService.updateConfVideotapeRule(confVideotapeRule);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_VideotapeRule);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_VideotapeRule_Update);
            operateLogInfo.setOperatedsp("修改通道录像时间规则信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 删除通道录像时间规则信息
     * @param loginUserID
     * @param loginUserName
     * @param channelID
     * @return Result结果集
     */
    @PostMapping("/deleteConfVideotapeRule")
    @ResponseBody
    @ApiOperation("删除通道录像时间规则信息")
    public Result<Object> deleteConfVideotapeRule(String loginUserID,String loginUserName,String channelID){
        Result<Object> result = new Result<>();
        try {
            confVideotapeRuleService.deleteConfVideotapeRule(channelID);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_VideotapeRule);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_VideotapeRule_Delete);
            operateLogInfo.setOperatedsp("删除通道录像时间规则信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }
}
