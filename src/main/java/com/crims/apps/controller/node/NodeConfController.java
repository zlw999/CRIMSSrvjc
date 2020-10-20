package com.crims.apps.controller.node;

import com.crims.apps.common.Result;
import com.crims.apps.model.confinfo.ConfEnum;
import com.crims.apps.model.confinfo.ConfNode;
import com.crims.apps.model.operlog.OperateLogInfo;
import com.crims.apps.service.ddman.DDManService;
import com.crims.apps.service.node.NodeConfService;
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
@RequestMapping("/nodeconf")
public class NodeConfController {

    public static Logger logger = LoggerFactory.getLogger(NodeConfController.class);

    @Autowired
    private NodeConfService nodeConfService;

    @Autowired
    private OperateLogInfoService operateLogInfoService;

    @Autowired
    private DDManService ddManService;

    /**
     * 查询所有节点配置信息(不分页查询)
     * @return List<ConfNode>对象集以JSON格式输出
     */
    @GetMapping("/getALL")
    @ResponseBody
    @ApiOperation(value = "查询所有节点配置信息(不分页查询)", notes = "nodeconf_info")
    public List<ConfNode> getALL(){
        return nodeConfService.getALL();
    }

    /**
     * 根据节点名称模糊查询节点配置信息(分页查询)
     * @param nodeNameParam
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<ConfNode>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getConfNodeList")
    @ResponseBody
    @ApiOperation(value = "根据节点名称模糊查询节点配置信息(分页查询)")
    public PageInfo<ConfNode> getConfNodeList(String loginUserID,String loginUserName,String nodeNameParam,
                                      @RequestParam(required = false, defaultValue = "0") int currentPage,
                                      @RequestParam(required = false, defaultValue = "10") int pageSize) {
        // 添加日志
        OperateLogInfo operateLogInfo = new OperateLogInfo();
        operateLogInfo.setUserid(loginUserID);
        operateLogInfo.setUsername(loginUserName);
        operateLogInfo.setOperatetime(new Date());
        operateLogInfo.setOperatetype(BaseConst.MsgCmdType_Node);
        operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_Node_Query);
        operateLogInfo.setOperatedsp("查询节点配置信息");
        operateLogInfoService.add_OperateLog(operateLogInfo);
        return nodeConfService.getConfNodeList(nodeNameParam,currentPage,pageSize);
    }

    /**
     * 添加节点配置信息
     * @param confNode
     * @return Result结果集
     */
    @RequestMapping(value = "/insertConfNode", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加节点配置信息")
    public Result<Object> insertConfNode(String loginUserID,String loginUserName,ConfNode confNode){
        Result<Object> result = new Result<Object>();
        try{
            confNode.setOperateaction(BaseConst.INSERTACTION);//操作标志 1 添加
            confNode.setLastmodifytime(new Date());
            confNode.setOperateuserid(loginUserID);
            confNode.setOperateusername(loginUserName);

            String nodeId = confNode.getNodeid().substring(0,2);
            ConfNode cNode = nodeConfService.getNodeInfoByNodeId(nodeId);
            if(cNode == null){
                ConfEnum confEnum = ddManService.getItem_value(nodeId,BaseConst.AREANODEID);
                ConfNode areaCNode = new ConfNode();
                areaCNode.setNodeid(nodeId);
                areaCNode.setNodename(confEnum.getItem_name());
                areaCNode.setUsestate(confNode.getUsestate());
                areaCNode.setLastmodifytime(new Date());
                areaCNode.setOperateaction(BaseConst.INSERTACTION);
                areaCNode.setOperateuserid(loginUserID);
                areaCNode.setOperateusername(loginUserName);
                nodeConfService.insertConfNode(areaCNode);
            }
            nodeConfService.insertConfNode(confNode);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_Node);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_Node_Add);
            operateLogInfo.setOperatedsp("添加节点配置信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch(Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 修改节点配置信息
     * @param confNode
     * @return Result结果集
     */
    @PostMapping("/updateConfNode")
    @ResponseBody
    @ApiOperation("修改节点配置信息")
    public Result<Object> updateConfNode(String loginUserID,String loginUserName,ConfNode confNode){
        Result<Object> result = new Result<>();
        try {
            confNode.setOperateaction(BaseConst.UPDATEACTION);//操作标志 2 修改
            confNode.setLastmodifytime(new Date());
            nodeConfService.updateConfNode(confNode);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_Node);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_Node_Update);
            operateLogInfo.setOperatedsp("修改节点配置信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 删除节点配置信息
     * @param confNode
     * @return Result结果集
     */
    @PostMapping("/deleteConfNode")
    @ResponseBody
    @ApiOperation("删除节点配置信息")
    public Result<Object> deleteConfNode(String loginUserID,String loginUserName,ConfNode confNode){
        Result<Object> result = new Result<>();
        try {
            confNode.setOperateaction(BaseConst.DELETEACTION);//操作标志 3 删除
            confNode.setUsestate(BaseConst.UNUSESTATE);
            confNode.setLastmodifytime(new Date());
            nodeConfService.deleteConfNode(confNode);

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_Node);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_Node_Delete);
            operateLogInfo.setOperatedsp("删除节点配置信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

}
