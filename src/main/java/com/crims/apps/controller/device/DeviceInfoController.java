package com.crims.apps.controller.device;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import com.crims.apps.common.Result;
import com.crims.apps.common.struct.TreeNode;
import com.crims.apps.model.confinfo.*;
import com.crims.apps.model.operlog.OperateLogInfo;
import com.crims.apps.service.ddman.DDManService;
import com.crims.apps.service.srvdevice.ConfSrvDeviceService;
import com.crims.apps.service.channelnum.ConfChannelnumService;
import com.crims.apps.service.device.channel.ChannelInfoService;
import com.crims.apps.service.operlog.OperateLogInfoService;
import com.crims.common.constants.BaseConst;
import com.crims.common.constants.FileUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.crims.apps.service.device.DeviceInfoService;

@Controller
@RequestMapping("/deviceinfo")
public class DeviceInfoController {

	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DeviceInfoService deviceInfoService;

    @Autowired
    private ChannelInfoService channelInfoService;    

    @Autowired
    private ConfChannelnumService confChannelnumService;

    @Autowired
    private ConfSrvDeviceService confSrvDeviceService;

    @Autowired
    private OperateLogInfoService operateLogInfoService;

    @Autowired
    private DDManService ddManService;

    private static String path = "/src/main/resources/templates";

    /**
     * 查询所有设备信息
     * @return List<DeviceInfo>对象集以JSON格式输出
     */
	@GetMapping("/getDeviceInfoList")
	@ResponseBody
    @ApiOperation(value = "查询所有设备信息", notes = "device_info")
	public List<DeviceInfo> getDeviceInfoList() {
		return deviceInfoService.getDeviceInfoList();
	}

    /**
     * 查询所有设备信息(分页查询)
     *
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return List<DeviceInfo>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getAll")
    @ResponseBody
    @ApiOperation(value = "查询所有设备信息(分页查询)", notes = "device_info")
    public PageInfo<DeviceInfo> getAll(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<DeviceInfo> deviceInfoList = deviceInfoService.getAll(currentPage, pageSize);
        return deviceInfoList;
    }

    /**
     * 添加设备信息
     * @param deviceInfo
     * @return Result结果集
     */
    @RequestMapping(value = "/insertDeviceInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加设备信息")
    public Result<Object> insertDeviceInfo(String loginUserID, String loginUserName, @RequestBody DeviceInfo deviceInfo){
        Result<Object> result = new Result<Object>();
        try{
            deviceInfo.setOperateaction(BaseConst.INSERTACTION);//操作标志 1 添加
            deviceInfo.setLastModifyTime(new Date());
            //添加设备信息conf_device
            int num = deviceInfoService.insertDeviceInfo(deviceInfo);
            if(num > 0){
                List<ConfChannel> confChannelList = deviceInfo.getConfChannels();
                if(confChannelList != null && !confChannelList.isEmpty()){
                    for(int i = 0;i<confChannelList.size();i++){
                        ConfChannel confChannel = new ConfChannel();
                        confChannel.setChannelid(confChannelList.get(i).getChannelid()); //通道id
                        confChannel.setTypeid(confChannelList.get(i).getTypeid()); //通道类型
                        confChannel.setChannelname(confChannelList.get(i).getChannelname()); //通道名称
                        confChannel.setFactoryid(deviceInfo.getFactoryid()); //厂家id
                        confChannel.setDeviceid(deviceInfo.getDeviceid()); //设备id
                        confChannel.setUsestate(deviceInfo.getUsestate()); //使用状态
                        confChannel.setOperateaction(BaseConst.INSERTACTION); //操作标志 1 添加
                        confChannel.setOperateuserid(deviceInfo.getOperateuserid());
                        confChannel.setOperateusername(deviceInfo.getOperateusername());
                        confChannel.setLastModifyTime(new Date());
                        //添加通道信息conf_channel
                        channelInfoService.insertChannelInfo(confChannel);
                    }
                    //检测更新通道信息数量conf_channelnum
                    checkChannelNum(confChannelList,0,deviceInfo,"1");
                }
            }

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_Device);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_Device_Add);
            operateLogInfo.setOperatedsp("添加设备信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch(Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 修改设备信息
     * @param deviceInfo
     * @return Result结果集
     */
    @PostMapping("/updateDeviceInfo")
    @ResponseBody
    @ApiOperation("修改设备信息")
    public Result<Object> updateDeviceInfo(String loginUserID,String loginUserName,@RequestBody DeviceInfo deviceInfo){
        Result<Object> result = new Result<>();
        try {
            deviceInfo.setOperateaction(BaseConst.UPDATEACTION);//操作标志 2 修改
            deviceInfo.setLastModifyTime(new Date());
            //修改设备信息conf_device
            int num = deviceInfoService.updateDeviceInfo(deviceInfo);
            if(num > 0){
                ConfChannel confChannelPre = new ConfChannel();
                confChannelPre.setDeviceid(deviceInfo.getDeviceid());
                //根据设备id获取通道数量conf_channel
                int channelSize = channelInfoService.getChannelSizeByDeviceID(deviceInfo.getDeviceid());
                //修改通道信息conf_channel
                channelInfoService.updateChannelInfo(confChannelPre);
                List<ConfChannel> confChannelList = deviceInfo.getConfChannels();
                if(confChannelList != null && !confChannelList.isEmpty()){
                    for(int j = 0;j<confChannelList.size();j++){
                        ConfChannel confChannel = new ConfChannel();
                        confChannel.setChannelid(confChannelList.get(j).getChannelid()); //通道id
                        confChannel.setChannelname(confChannelList.get(j).getChannelname());  //通道名称
                        confChannel.setTypeid(confChannelList.get(j).getTypeid()); //通道类型
                        confChannel.setFactoryid(deviceInfo.getFactoryid()); //厂家id
                        confChannel.setDeviceid(deviceInfo.getDeviceid()); //设备id
                        confChannel.setOperateaction(BaseConst.INSERTACTION); //操作标志 1 添加
                        confChannel.setUsestate(deviceInfo.getUsestate()); //使用状态
                        confChannel.setOperateuserid(deviceInfo.getOperateuserid());
                        confChannel.setOperateusername(deviceInfo.getOperateusername());
                        confChannel.setLastModifyTime(new Date());
                        //添加通道信息conf_channel
                        channelInfoService.insertChannelInfo(confChannel);
                    }
                    //检测更新通道信息数量conf_channelnum
                    checkChannelNum(confChannelList,channelSize,deviceInfo,"2");
                }
            }

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_Device);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_Device_Update);
            operateLogInfo.setOperatedsp("修改设备信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 删除设备信息
     * @param deviceInfo
     * @return Result结果集
     */
    @PostMapping("/deleteDeviceInfo")
    @ResponseBody
    @ApiOperation("删除设备信息")
    public Result<Object> deleteDeviceInfo(String loginUserID,String loginUserName,DeviceInfo deviceInfo){
        Result<Object> result = new Result<>();
        try {
            deviceInfo.setLastModifyTime(new Date());
            deviceInfo.setOperateaction(BaseConst.DELETEACTION);//操作标志 3 删除
            deviceInfo.setUsestate(BaseConst.UNUSESTATE);
            int num = deviceInfoService.deleteDeviceInfo(deviceInfo);
            if(num > 0){
                ConfChannel confChannel = new ConfChannel();
                confChannel.setDeviceid(deviceInfo.getDeviceid());
                confChannel.setOperateaction(BaseConst.DELETEACTION);//操作标志 3 删除
                confChannel.setUsestate(deviceInfo.getUsestate());
                confChannel.setLastModifyTime(new Date());
                channelInfoService.deleteChannelInfo(confChannel);
            }

            // 添加日志
            OperateLogInfo operateLogInfo = new OperateLogInfo();
            operateLogInfo.setUserid(loginUserID);
            operateLogInfo.setUsername(loginUserName);
            operateLogInfo.setOperatetime(new Date());
            operateLogInfo.setOperatetype(BaseConst.MsgCmdType_Device);
            operateLogInfo.setOperatecmd(BaseConst.MsgCmdType_Device_Delete);
            operateLogInfo.setOperatedsp("删除设备信息");
            operateLogInfoService.add_OperateLog(operateLogInfo);
        }catch (Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }

        return result;
    }

    /**
     * 查询所有通道信息
     * @return List<ConfChannel>对象集以JSON格式输出
     */
    @GetMapping("/getConfChannelList")
    @ResponseBody
    @ApiOperation(value = "查询所有通道信息", notes = "channel_info")
    public List<ConfChannel> getConfChannelList() {
        return channelInfoService.getConfChannelList();
    }

    /**
     * 根据通道类型查询最大通道ID
     * @param typeID
     * @return confChannel
     */
    @PostMapping("/getMaxChannelID")
    @ResponseBody
    @ApiOperation("根据通道类型查询最大通道ID")
    public ConfChannel getMaxChannelID(String typeID){
        List<ConfChannel> confChannels = channelInfoService.getMaxChannelID(typeID);
        ConfChannel confChannel = null;
        if(confChannels != null && !confChannels.isEmpty()){
            confChannel = confChannels.get(0);
        }
        return confChannel;
    }

    /**
     * 根据通道类型和设备id获取通道数量
     * @param typeID
     * @param deviceID
     * @return Integer
     */
    @PostMapping("/getChannelNum")
    @ResponseBody
    @ApiOperation("根据通道类型和设备id获取通道数量")
    public Integer getChannelNum(String typeID,String deviceID){
        int num = channelInfoService.getChannelNum(typeID,deviceID);
        return num;
    }

    /**
     * 检测更新通道信息数量
     * @param confChannelList
     * @param deviceInfo
     */
    public void checkChannelNum(List<ConfChannel> confChannelList,int channelSize,DeviceInfo deviceInfo,String flag){
        int size = confChannelList.size();
        String nodeId = deviceInfo.getDeviceid().substring(2,6);
        String nodeIdI = deviceInfo.getDeviceid().substring(4,6);
        String nodeIdII = deviceInfo.getDeviceid().substring(0,6);
        if("0000".equals(nodeId)){//添加核心区域节点通道数量
            String nodeIdStr = deviceInfo.getDeviceid().substring(0,2);
            updateConfChannelNum(size,nodeIdStr,channelSize,flag);
        }
        if("00".equals(nodeIdI) && !"0000".equals(nodeId)){//I类节点通道数量添加，同时添加核心区域节点通道数量
            String nodeIdIStr = deviceInfo.getDeviceid().substring(0,4);
            updateConfChannelNum(size,nodeIdIStr,channelSize,flag);
            String nodeIdStr = deviceInfo.getDeviceid().substring(0,2);
            updateConfChannelNum(size,nodeIdStr,channelSize,flag);
        }
        if(!"00".equals(nodeIdI) && !"0000".equals(nodeId)){//II类节点通道数量添加，同时添加核心区域和一类节点通道数量
            updateConfChannelNum(size,nodeIdII,channelSize,flag);
            String nodeIdIStr = deviceInfo.getDeviceid().substring(0,4);
            updateConfChannelNum(size,nodeIdIStr,channelSize,flag);
            String nodeIdStr = deviceInfo.getDeviceid().substring(0,2);
            updateConfChannelNum(size,nodeIdStr,channelSize,flag);
        }
    }

    /**
     * 更新通道数量信息
     * @param size
     * @param nodeId
     */
    public void updateConfChannelNum(int size,String nodeId,int channelSize,String flag){
        ConfChannelnum confChannelnum = confChannelnumService.getChannelNumByNodeID(nodeId);

        if(confChannelnum != null){
            int channelnum = confChannelnum.getVideochannelnum();
            if("2".equals(flag)){//2表示修改设备通道信息数量，表达式表示某节点总得通道信息数量，先减去修改的某设备下的通道数量，再加上修改的总得设备通道数量
                channelnum = channelnum - channelSize + size;
            }else{
                channelnum = channelnum + size;
            }
            confChannelnum.setVideochannelnum(channelnum);
            confChannelnum.setNodeid(nodeId);
            confChannelnumService.updateChannelNumInfo(confChannelnum);
        }else{
            ConfChannelnum cChannelnum = new ConfChannelnum();
            cChannelnum.setVideochannelnum(size);
            cChannelnum.setNodeid(nodeId);
            confChannelnumService.insertChannelNumInfo(cChannelnum);
        }
    }

    /**
     * 查询通道数量信息
     * @return List<ConfChannelnum>对象集以JSON格式输出
     */
    @GetMapping("/getChannelNumInfo")
    @ResponseBody
    @ApiOperation(value = "查询通道数量信息(查所有)", notes = "confchannelnum")
    public List<ConfChannelnum> getChannelNumInfo() {
        return confChannelnumService.getChannelNumInfo();
    }

    /**
     * 根据节点编号模糊查询通道信息(分页查询)
     * @param channelid
     * @param currentPage 第几页
     * @param pageSize    每页条数
     * @return PageInfo<ConfChannel>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getConfChannelByCId")
    @ResponseBody
    @ApiOperation(value = "根据节点编号模糊查询通道信息(分页查询)", notes = "ConfChannel_info")
    public PageInfo<ConfChannel> getConfChannelByCId(String channelid,
                                         @RequestParam(required = false, defaultValue = "0") int currentPage,
                                         @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<ConfChannel> confChannelList = channelInfoService.getConfChannelByCId(channelid,currentPage, pageSize);
        return confChannelList;
    }

    /**
     * 根据节点编号模糊查询通道信息(不分页查询)
     * @param channelid
     * @return List<ConfChannel>对象集分页形式以JSON格式输出
     */
    @GetMapping("/getConfChannelListByCId")
    @ResponseBody
    @ApiOperation(value = "根据节点编号模糊查询通道信息(不分页查询)", notes = "ConfChannel_info")
    public List<ConfChannel> getConfChannelListByCId(String channelid) {
        int length = channelid.length();
        List<ConfChannel> confChannelList = channelInfoService.getConfChannelByCId(channelid);
        List<ConfChannel> confChannelListNew = new ArrayList<>();
        for(int i = 0;i<confChannelList.size();i++){
            String channelidStr = confChannelList.get(i).getChannelid().substring(0,length);
            if(channelidStr.equals(channelid)){  
                confChannelListNew.add(confChannelList.get(i));
            }
        }
        return confChannelListNew;
    }

    @GetMapping("getTemplates")
    @ResponseBody
    @ApiOperation(value = "得到模板树")
    public List<TreeNode> getTemplates() {
        String decode = null;
        String conTextPath = FileUtil.getConTextPath();
        try {
            decode = URLDecoder.decode(conTextPath, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("转换失败");
            return null;
        }
        return FileUtil.getTemPlates(decode.concat(path),null);
    }

    @GetMapping("/getConfSrvDeviceInfo")
    @ResponseBody
    @ApiOperation(value = "根据服务节点获取设备节点信息", notes = "ConfSrvDevice")
    public List<Map<String,Object>> getConfSrvDeviceInfo(String srvnodeid){
        return confSrvDeviceService.getConfSrvDeviceInfo(srvnodeid);
    }

    /**
     * 添加服务节点设备信息
     * @param srvDeviceList
     * @return Result结果集
     */
    @RequestMapping(value = "/insertConfSrvDeviceInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加服务节点设备信息")
    public Result<Object> insertConfSrvDeviceInfo(@RequestBody ArrayList<ConfSrvDevice> srvDeviceList){
        Result<Object> result = new Result<>();
        try{
            confSrvDeviceService.deleteConfSrvDeviceInfo();

            confSrvDeviceService.insertConfSrvDevice(srvDeviceList);
        }catch(Exception e){
            result.setCode("1");
            result.setMsg("必要参数缺失，或参数类型错误");
        }
        return result;
    }

    @PostMapping("/getDeviceInfoByDType")
    @ResponseBody
    @ApiOperation(value = "获取区域节点--设备类型下所属设备", notes = "ConfSrvDevice")
    public List<Map<String,List<DeviceInfo>>> getDeviceInfoByDType(String nodeId){

        String MID = "N002";//设备主类型

        String SRVID = "M037";//服务器

        String STROID = "M038";//存储设备

        String INTID = "M039";//网络设备

        //获取设备主类型数据字典列表
        List<ConfEnum> mIDEnumList = ddManService.getId(MID);

        //获取服务器数据字典列表
        List<ConfEnum> sRVIDEnumList = ddManService.getId(SRVID);

        //获取存储设备数据字典列表
        List<ConfEnum> sTROIDEnumList = ddManService.getId(STROID);

        //获取网络设备数据字典列表
        List<ConfEnum> iNTIDEnumList = ddManService.getId(INTID);

        List<Map<String,List<DeviceInfo>>> listMap = new ArrayList<>();

        for (int i = 0; i < mIDEnumList.size(); i++) {
            if("02".equals(mIDEnumList.get(i).getItem_value())){
                Map<String,List<DeviceInfo>> sRVIDEnumMap = new HashMap<>();
                List<DeviceInfo> sRVIDDeviceList = new ArrayList<>();
                for (int j = 0; j < sRVIDEnumList.size(); j++) {
                    List<DeviceInfo> deviceInfoList = deviceInfoService.getDeviceInfoByDType("0"+sRVIDEnumList.get(j).getItem_value(),nodeId);

                    if(deviceInfoList != null && !deviceInfoList.isEmpty()){
                        for (int k = 0; k < deviceInfoList.size(); k++) {
                            sRVIDDeviceList.add(deviceInfoList.get(k));
                        }
                    }

                }
                sRVIDEnumMap.put(mIDEnumList.get(i).getItem_value(),sRVIDDeviceList);
                listMap.add(sRVIDEnumMap);
            }

            if("03".equals(mIDEnumList.get(i).getItem_value())){
                Map<String,List<DeviceInfo>> sTROIDEnumMap = new HashMap<>();
                List<DeviceInfo> sTROIDDeviceList = new ArrayList<>();
                for (int j = 0; j < sTROIDEnumList.size(); j++) {
                    List<DeviceInfo> deviceInfoList = deviceInfoService.getDeviceInfoByDType("0"+sTROIDEnumList.get(j).getItem_value(),nodeId);
                    if(deviceInfoList != null && !deviceInfoList.isEmpty()){
                        for (int k = 0; k < deviceInfoList.size(); k++) {
                            sTROIDDeviceList.add(deviceInfoList.get(k));
                        }
                    }
                }
                sTROIDEnumMap.put(mIDEnumList.get(i).getItem_value(),sTROIDDeviceList);
                listMap.add(sTROIDEnumMap);
            }


            if("04".equals(mIDEnumList.get(i).getItem_value())){
                Map<String,List<DeviceInfo>> iNTIDEnumMap = new HashMap<>();
                List<DeviceInfo> iNTIDDeviceList = new ArrayList<>();
                for (int j = 0; j < iNTIDEnumList.size(); j++) {
                    List<DeviceInfo> ideviceInfoList = deviceInfoService.getDeviceInfoByDType("0"+iNTIDEnumList.get(j).getItem_value(),nodeId);
                    if(ideviceInfoList != null && !ideviceInfoList.isEmpty()){
                        for (int k = 0; k < ideviceInfoList.size(); k++) {
                            iNTIDDeviceList.add(ideviceInfoList.get(k));
                        }
                    }
                }
                iNTIDEnumMap.put(mIDEnumList.get(i).getItem_value(),iNTIDDeviceList);
                listMap.add(iNTIDEnumMap);
            }

        }

        return listMap;
    }
}
