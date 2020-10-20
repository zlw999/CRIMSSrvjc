package com.crims.apps.controller.role;

import com.crims.apps.common.Result;
import com.crims.apps.model.confinfo.ConfRole;
import com.crims.apps.model.operlog.OperateLogInfo;
import com.crims.apps.service.operlog.OperateLogInfoService;
import com.crims.apps.service.role.RoleInfoService;
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
@RequestMapping("/roleinfo")
public class RoleInfoController {

    public static Logger logger = LoggerFactory.getLogger(RoleInfoController.class);

    @Autowired
    private RoleInfoService roleInfoService;

    @Autowired
    private OperateLogInfoService operateLogInfoService;

    /**
     * 查询所有角色信息(不分页查询)
     * @return List<ConfRole>对象集以JSON格式输出
     */
    @GetMapping("/getRoleInfoList")
    @ResponseBody
    @ApiOperation(value = "查询所有角色信息(不分页查询)", notes = "confrole_info")
    public List<ConfRole> getRoleInfoList(){
        return roleInfoService.getRoleInfoList();
    }

    /**
     * 根据角色名称模糊查询角色信息(分页查询)
     * @param roleName
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfRole>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getAll")
    @ResponseBody
    @ApiOperation(value = "根据角色名称模糊查询角色信息(分页查询)", notes = "confrole_info")
    public PageInfo<ConfRole> getAll(String loginUserID,String loginUserName,String roleName,
                                     @RequestParam(required = false, defaultValue = "0") int currentPage,
                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<ConfRole> confRoleList = roleInfoService.getAll(roleName,currentPage, pageSize);
        // 添加日志
        OperateLogInfo operateLogInfo = new OperateLogInfo();
        operateLogInfo.setUserid(loginUserID);
        operateLogInfo.setUsername(loginUserName);
        operateLogInfo.setOperatetime(new Date());
        operateLogInfo.setOperatetype(BaseConst.MsgCmdType_Role);
        operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_Role_Query);
        operateLogInfo.setOperatedsp("查询角色信息");
        operateLogInfoService.add_OperateLog(operateLogInfo);
        return confRoleList;
    }

    /**
     * 添加角色信息
     * @param confRole
     * @return Result结果集
     */
    @RequestMapping(value = "/insertConfRole", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加角色信息")
    public Result<Object> insertConfRole(String loginUserID,String loginUserName,ConfRole confRole){
        Result<Object> result = new Result<Object>();
        try{
            confRole.setOperateaction(BaseConst.INSERTACTION);//操作标志 1 添加
            confRole.setLastmodifytime(new Date());
            roleInfoService.insertConfRole(confRole);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_Role);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_Role_Add);
            operateLogInfo.setOperatedsp("添加角色信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch(Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 修改角色信息
     * @param confRole
     * @return Result结果集
     */
    @PostMapping("/updateConfRole")
    @ResponseBody
    @ApiOperation("修改角色信息")
    public Result<Object> updateConfRole(String loginUserID,String loginUserName,ConfRole confRole){
        Result<Object> result = new Result<>();
        try {
            confRole.setOperateaction(BaseConst.UPDATEACTION);//操作标志 2 修改
            confRole.setLastmodifytime(new Date());
            roleInfoService.updateConfRole(confRole);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_Role);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_Role_Update);
            operateLogInfo.setOperatedsp("修改角色信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 删除角色信息
     * @param confRole
     * @return Result结果集
     */
    @PostMapping("/deleteConfRole")
    @ResponseBody
    @ApiOperation("删除角色信息")
    public Result<Object> deleteConfRole(String loginUserID,String loginUserName,ConfRole confRole){
        Result<Object> result = new Result<>();
        try {
            confRole.setOperateaction(BaseConst.DELETEACTION);//操作标志 3 删除
            confRole.setUsestate(BaseConst.UNUSESTATE);
            confRole.setLastmodifytime(new Date());
            roleInfoService.deleteConfRole(confRole);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_Role);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_Role_Delete);
            operateLogInfo.setOperatedsp("删除角色信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    @PostMapping("/getRoleInfoByNodeID")
    @ResponseBody
    @ApiOperation("根据节点id获取最大角色编号")
    public String getRoleInfoByNodeID(String nodeId){
        return roleInfoService.getRoleInfoByNodeID(nodeId);
    }
}
