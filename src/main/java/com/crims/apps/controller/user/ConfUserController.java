package com.crims.apps.controller.user;

import com.crims.apps.common.Result;
import com.crims.apps.model.confinfo.ConfEnum;
import com.crims.apps.model.confinfo.ConfUserInfo;
import com.crims.apps.model.confinfo.ConfUserUI;
import com.crims.apps.model.operlog.OperateLogInfo;
import com.crims.apps.model.operlog.RecOperateinfo;
import com.crims.apps.model.useronline.ConfUserOnline;
import com.crims.apps.service.ddman.DDManService;
import com.crims.apps.service.operlog.OperateLogInfoService;
import com.crims.apps.service.operlog.RecOperateinfoService;
import com.crims.apps.service.user.ConfUserService;
import com.crims.apps.service.user.ConfUserUIService;
import com.crims.apps.service.useronline.ConfUserOnlineService;
import com.crims.common.constants.BaseConst;
import com.crims.common.struct.UserOnlineInfo;
import com.crims.container.DataCache;
import com.crims.dbconfig.DataSourceContextHolder;
import com.crims.main.ParamManager;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/userLogin")
public class ConfUserController {

	public static Logger logger = LoggerFactory.getLogger(ConfUserController.class);

	@Autowired
    ConfUserService confUserService;

    @Autowired
    ConfUserUIService confUserUIService;

    @Autowired
    private DDManService ddmanService;

    @Autowired
    private OperateLogInfoService operateLogInfoService;

    @Autowired
    private ConfUserOnlineService confUserOnlineService;

    @Autowired
    private RecOperateinfoService recOperateinfoService;

    /**
     * 登录验证
     * @param userName 账户名称
     * @param password 密码
     * @return
     */
    @PostMapping("/checkLogin")
    @ResponseBody
    @ApiOperation(value = "登录验证")
    public Result<Object> checkLogin(String userName, String password, HttpServletRequest request) {
        Result<Object> result = new Result<>();
        ConfUserInfo confUserInfo = confUserService.checkLogin(userName,password);
        if(null==confUserInfo) {
            result.setCode("1");
            result.setMsg("没有此用户");
            return result;
        }
        List<ConfUserUI> confUserUIList = confUserUIService.getConfUserUIList(confUserInfo.getUserId());
        List<ConfEnum> confEnumList = new ArrayList<>();
        if(confUserUIList != null && !confUserUIList.isEmpty()){
            for(int i = 0;i<confUserUIList.size();i++){
                ConfEnum confEnum = ddmanService.getItem_value(confUserUIList.get(i).getItem_value(),BaseConst.CONFENUM_ID);
                confEnum.setUserId(confUserInfo.getUserId());
                confEnum.setUserName(confUserInfo.getUserName());
                confEnumList.add(confEnum);
            }
        }

        ConfUserInfo userInfo = confUserService.getConfUserInfo(userName, ParamManager.syncsrvnodeid);
        if(userInfo != null){
            String userId = userInfo.getUserId();

            UserOnlineInfo cacheUserInfo = new UserOnlineInfo();
            cacheUserInfo.setUserId(userId);
            cacheUserInfo.setUserName(userName);
            cacheUserInfo.setPassword(password);
            cacheUserInfo.setLongTime(new Date());
            //记录该用户登录时间
            DataCache.getInstance().addUserOnlineMap(cacheUserInfo);

            ConfUserOnline userOnlineInfo = confUserOnlineService.getUserOnlineInfo(userId);
            if (userOnlineInfo != null) {
                int num = userOnlineInfo.getLoginstate();
                num++;
                userOnlineInfo.setLoginstate(num);
                userOnlineInfo.setOnlinestatu("01");//01 在线 02 离线
                userOnlineInfo.setLastmodifytime(new Date());
                confUserOnlineService.updateUserOnlineInfo(userOnlineInfo);
            }

            DataSourceContextHolder.setKey("crimsrec");
            RecOperateinfo recOperateinfo = recOperateinfoService.getRecOperateinfo(userId);
            String ItemValue = "0105";
            if (recOperateinfo != null) {
                recOperateinfo.setOperatedt(new Date());
                recOperateinfo.setOperatety(ItemValue);
                recOperateinfoService.updateRecOperateinfo(recOperateinfo);
            }
            DataSourceContextHolder.clearKey();
        }


        result.setResult(confEnumList);
        return result;
    }

    @PostMapping("/loginOut")
    @ResponseBody
    @ApiOperation(value = "退出登录")
    public Result<Object> userLoginOut(String userId) {
        Result<Object> result = new Result<>();
        ConfUserOnline userOnlineInfo = confUserOnlineService.getUserOnlineInfo(userId);
        if (userOnlineInfo != null) {
            userOnlineInfo.setOnlinestatu("02");//01 在线 02 离线
            userOnlineInfo.setLastmodifytime(new Date());
            confUserOnlineService.updateUserOnlineInfo(userOnlineInfo);
        }
        DataSourceContextHolder.setKey("crimsrec");
        RecOperateinfo recOperateinfo = recOperateinfoService.getRecOperateinfo(userId);
        String ItemValue = "0102";
        if (recOperateinfo != null) {
            recOperateinfo.setOperatedt(new Date());
            recOperateinfo.setOperatety(ItemValue);
            recOperateinfoService.updateRecOperateinfo(recOperateinfo);
        }

        result.setCode("0");
        result.setMsg("用户在线状态表更新成功");
        return result;
    }

    /**
     * 查询所有用户信息(不分页查询)
     * @return List<ConfUserInfo>对象集以JSON格式输出
     */
    @GetMapping("/getConfUserList")
    @ResponseBody
    @ApiOperation(value = "查询所有用户信息(不分页查询)", notes = "confuser_info")
    public List<ConfUserInfo> getConfUserList(){
        return confUserService.getConfUserList();
    }

    /**
     * 根据用户名称模糊查询用户信息(分页查询)
     * @param userName
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfUserInfo>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getAll")
    @ResponseBody
    @ApiOperation(value = "根据用户名称模糊查询用户信息(分页查询)", notes = "confuser_info")
    public PageInfo<ConfUserInfo> getAll(String loginUserID,String loginUserName,String userName,
                                     @RequestParam(required = false, defaultValue = "0") int currentPage,
                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<ConfUserInfo> confUserInfoList = confUserService.getAll(userName,currentPage, pageSize);
        // 添加日志
        OperateLogInfo operateLogInfo = new OperateLogInfo();
        operateLogInfo.setUserid(loginUserID);
        operateLogInfo.setUsername(loginUserName);
        operateLogInfo.setOperatetime(new Date());
        operateLogInfo.setOperatetype(BaseConst.MsgCmdType_User);
        operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_User_Query);
        operateLogInfo.setOperatedsp("查询用户信息");
        operateLogInfoService.add_OperateLog(operateLogInfo);
        return confUserInfoList;
    }

    /**
     * 添加用户信息
     * @param confUser
     * @return Result结果集
     */
    @RequestMapping(value = "/insertConfUser", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加用户信息")
    public Result<Object> insertConfUser(String loginUserID,String loginUserName,ConfUserInfo confUser){
        Result<Object> result = new Result<Object>();
        try{
            confUser.setOperateaction(BaseConst.INSERTACTION);//操作标志 1 添加
            confUser.setLastModifyTime(new Date());
            confUserService.insertConfUser(confUser);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_User);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_User_Add);
            operateLogInfo.setOperatedsp("添加用户信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch(Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 修改用户信息
     * @param confUser
     * @return Result结果集
     */
    @PostMapping("/updateConfUser")
    @ResponseBody
    @ApiOperation("修改用户信息")
    public Result<Object> updateConfUser(String loginUserID,String loginUserName,ConfUserInfo confUser){
        Result<Object> result = new Result<>();
        try {
            confUser.setOperateaction(BaseConst.UPDATEACTION);//操作标志 2 修改
            confUser.setLastModifyTime(new Date());
            confUserService.updateConfUser(confUser);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_User);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_User_Update);
            operateLogInfo.setOperatedsp("修改用户信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 删除用户信息
     * @param confUser
     * @return Result结果集
     */
    @PostMapping("/deleteConfUser")
    @ResponseBody
    @ApiOperation("删除用户信息")
    public Result<Object> deleteConfUser(String loginUserID,String loginUserName,ConfUserInfo confUser){
        Result<Object> result = new Result<>();
        try {
            confUser.setLastModifyTime(new Date());
            confUser.setOperateaction(BaseConst.DELETEACTION);//操作标志 3 删除
            confUser.setUsestate(BaseConst.UNUSESTATE);
            confUserService.deleteConfUser(confUser);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_User);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_User_Delete);
            operateLogInfo.setOperatedsp("删除用户信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 查询所有用户类型信息(不分页查询)
     * @return List<ConfUserUI>对象集以JSON格式输出
     */
    @GetMapping("/getConfUserUIInfo")
    @ResponseBody
    @ApiOperation(value = "查询所有用户类型信息(不分页查询)", notes = "confuserui_info")
    public List<ConfUserUI> getConfUserUIInfo(){
        return confUserUIService.getConfUserUIInfo();
    }

    /**
     * 查询所有用户类型信息(分页查询)
     *
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfUserUI>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getconfUserUIPage")
    @ResponseBody
    @ApiOperation(value = "查询所有用户类型信息(分页查询)", notes = "confuserui_info")
    public PageInfo<ConfUserUI> getAll(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<ConfUserUI> confUserUIList = confUserUIService.getAll(currentPage, pageSize);
        return confUserUIList;
    }

    /**
     * 添加用户类型信息
     * @param confUserUI
     * @return Result结果集
     */
    @RequestMapping(value = "/insertConfUserUI", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加用户类型信息")
    public Result<Object> insertConfUserUI(String loginUserID,String loginUserName,ConfUserUI confUserUI){
        Result<Object> result = new Result<Object>();
        try{
            confUserUI.setOperateaction(String.valueOf(BaseConst.INSERTACTION));//操作标志 1 添加
            confUserUI.setLastModifyTime(new Date());
            confUserUIService.insertConfUserUI(confUserUI);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_UserUI);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_UserUI_Add);
            operateLogInfo.setOperatedsp("添加用户类型信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch(Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 修改用户类型信息
     * @param confUserUI
     * @return Result结果集
     */
    @PostMapping("/updateConfUserUI")
    @ResponseBody
    @ApiOperation("修改用户类型信息")
    public Result<Object> updateConfUserUI(String loginUserID,String loginUserName,ConfUserUI confUserUI){
        Result<Object> result = new Result<>();
        try {
            confUserUI.setOperateaction(String.valueOf(BaseConst.UPDATEACTION));//操作标志 2 修改
            confUserUI.setLastModifyTime(new Date());
            confUserUIService.updateConfUserUI(confUserUI);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_UserUI);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_UserUI_Update);
            operateLogInfo.setOperatedsp("修改用户类型信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 删除用户类型信息
     * @param confUserUI
     * @return Result结果集
     */
    @PostMapping("/deleteConfUserUI")
    @ResponseBody
    @ApiOperation("删除用户类型信息")
    public Result<Object> deleteConfUserUI(String loginUserID,String loginUserName,ConfUserUI confUserUI){
        Result<Object> result = new Result<>();
        try {
            confUserUI.setLastModifyTime(new Date());
            confUserUI.setOperateaction(String.valueOf(BaseConst.DELETEACTION));//操作标志 3 删除
            confUserUI.setUsestate(String.valueOf(BaseConst.UNUSESTATE));
            confUserUIService.deleteConfUserUI(confUserUI);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_UserUI);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_UserUI_Delete);
            operateLogInfo.setOperatedsp("删除用户类型信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }
}
