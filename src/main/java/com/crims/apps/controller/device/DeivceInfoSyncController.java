package com.crims.apps.controller.device;

import com.crims.apps.common.Result;
import com.crims.apps.model.confinfo.ConfChannel;
import com.crims.apps.model.confinfo.ConfChannelidTocrealInt;
import com.crims.apps.model.confinfo.DataBaseInfo;
import com.crims.apps.model.confinfo.DeviceInfo;
import com.crims.apps.service.channelidtocrealInt.ChannelidTocrealIntService;
import com.crims.apps.service.device.DeviceInfoService;
import com.crims.apps.service.device.channel.ChannelInfoService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/deviceinfosync")
public class DeivceInfoSyncController {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeviceInfoService deviceInfoService;

    @Autowired
    private ChannelInfoService channelInfoService;

    @Autowired
    private ChannelidTocrealIntService channelidTocrealIntService;

    @RequestMapping(value = "/deviceSyncData", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "同步设备信息和通道信息")
    public Result<Object> deviceSyncData(DataBaseInfo dataBaseInfo){
        Result<Object> result = new Result<Object>();
        try{
            //加载数据库驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //获取连接地址
            String strUrl = "jdbc:sqlserver://"+dataBaseInfo.getIp()+":"+dataBaseInfo.getPort()+";database="+dataBaseInfo.getDataBaseName()+"";//由协议和地址名称组成

            //连接数据库：用户名，密码，URL数据库地址，得到连接Connection
            Connection conn = DriverManager.getConnection(strUrl, dataBaseInfo.getUserName(), dataBaseInfo.getPassword());

            Statement statement = conn.createStatement();

            //同步设备信息数据
            syncDeviceInfo(statement);

            //同步通道信息数据
            syncChannelInfo(statement);

            //同步通道编码数据
            syncConfChannelidTocrealInt(statement);

            result.setMsg("数据导入成功");
            result.setCode("0");
        }catch(Exception e){
            result.setCode("1");
            result.setMsg("数据导入失败:数据库连接失败");
            return result;
        }

        return result;
    }

    /**
     * 同步设备信息数据
     * @param statement
     */
    public void syncDeviceInfo(Statement statement){
        try{
            List<Map<String, Object>> deviceInfoList = new ArrayList<>();

            String sql = "SELECT\n" +
                    "\tdbo.[sip-nodedevice-map-16].nodedevicecode,\n" +
                    "\tdbo.[conf-node].nodename,\n" +
                    "\tdbo.[conf-node].DvrType,\n" +
                    "\tdbo.[conf-node].factoryno AS factoryno,\n" +
                    "\tdbo.[conf-node].ip,\n" +
                    "\tdbo.[conf-node].lisport,\n" +
                    "\tdbo.[conf-node].[user],\n" +
                    "\tdbo.[conf-node].password,\n" +
                    "\tdbo.[conf-node].[video-cnum] AS videonum \n" +
                    "FROM\n" +
                    "\tdbo.[sip-nodedevice-map-16]\n" +
                    "\tINNER JOIN dbo.[conf-node] ON dbo.[sip-nodedevice-map-16].nodeid = dbo.[conf-node].nodeid \n" +
                    "WHERE\n" +
                    "\t(\n" +
                    "\tdbo.[conf-node].nodeid & 0xFF = 1)";

            syncDataAcess(sql, statement, deviceInfoList);

            if(deviceInfoList != null && !deviceInfoList.isEmpty()){
                for (int i = 0; i < deviceInfoList.size(); i++) {
                    DeviceInfo deviceInfo = new DeviceInfo();
                    deviceInfo.setDeviceid(deviceInfoList.get(i).get("nodedevicecode") != null ? deviceInfoList.get(i).get("nodedevicecode").toString() : "");
                    deviceInfo.setDevicename(deviceInfoList.get(i).get("nodename") != null ? deviceInfoList.get(i).get("nodename").toString() : "");
                    deviceInfo.setFactoryid(deviceInfoList.get(i).get("factoryno") != null ? String.valueOf(Integer.parseInt(deviceInfoList.get(i).get("factoryno").toString()) & 0xFFFF) : "");
                    deviceInfo.setIpaddress(deviceInfoList.get(i).get("ip") != null ? deviceInfoList.get(i).get("ip").toString() : "");
                    deviceInfo.setLisport(deviceInfoList.get(i).get("lisport") != null ? Integer.parseInt(deviceInfoList.get(i).get("lisport").toString()) : 0);
                    deviceInfo.setChannelnum(deviceInfoList.get(i).get("video-cnum") != null ? Integer.parseInt(deviceInfoList.get(i).get("video-cnum").toString()) : 0);
                    deviceInfo.setLoginuser(deviceInfoList.get(i).get("user") != null ? deviceInfoList.get(i).get("user").toString() : "");
                    deviceInfo.setPassword(deviceInfoList.get(i).get("password") != null ? deviceInfoList.get(i).get("password").toString() : "");
                    deviceInfo.setLastModifyTime(new Date());
                    DeviceInfo dInfo = deviceInfoService.getDeviceInfo(deviceInfoList.get(i).get("nodedevicecode").toString());
                    if(dInfo != null){
                        deviceInfoService.updateDeviceInfo(deviceInfo);
                    }else{
                        deviceInfoService.insertDeviceInfo(deviceInfo);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 同步通道信息数据
     * @param statement
     */
    public void syncChannelInfo(Statement statement){
        try{
            List<Map<String, Object>> channelInfoList = new ArrayList<>();

            String sql = "SELECT     dbo.[sip-channel-map-16].channelcode AS channelcode, dbo.[conf-channel].channelname AS channelname, dbo.[sip-nodedevice-map-16].nodedevicecode AS deviceid\n" +
                    "FROM dbo.[conf-channel] INNER JOIN\n" +
                    "dbo.[sip-channel-map-16] ON dbo.[conf-channel].channelid = dbo.[sip-channel-map-16].channelid\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tINNER JOIN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tdbo.[sip-nodedevice-map-16] ON (dbo.[conf-channel].channelid & 0x3FFF00) / 0x100 = dbo.[sip-nodedevice-map-16].nodeid / 0x100";

            syncDataAcess(sql, statement, channelInfoList);

            if(channelInfoList != null && !channelInfoList.isEmpty()){
                for (int i = 0; i < channelInfoList.size(); i++) {
                    ConfChannel confChannel = new ConfChannel();
                    confChannel.setChannelid(channelInfoList.get(i).get("channelcode") != null?channelInfoList.get(i).get("channelcode").toString() : "");
                    confChannel.setChannelname(channelInfoList.get(i).get("channelname") != null?channelInfoList.get(i).get("channelname").toString() : "");
                    confChannel.setDeviceid(channelInfoList.get(i).get("deviceid") != null?channelInfoList.get(i).get("deviceid").toString() : "");
                    confChannel.setTypeid("");
                    confChannel.setFactoryid("");
                    confChannel.setUsestate(1);
                    confChannel.setOperateaction(1);
                    confChannel.setOperateuserid("");
                    confChannel.setOperateusername("");
                    confChannel.setLastModifyTime(new Date());

                    List<ConfChannel> list = channelInfoService.getConfChannelByCId(channelInfoList.get(i).get("channelcode").toString());
                    if(list != null && !list.isEmpty()){
                        channelInfoService.updateChannelInfoByCID(channelInfoList.get(i).get("deviceid").toString(),channelInfoList.get(i).get("channelcode").toString());
                    }else{
                        channelInfoService.insertChannelInfo(confChannel);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 同步通道编码数据
     * @param statement
     */
    public void syncConfChannelidTocrealInt(Statement statement){
        try{
            List<Map<String, Object>> channelidTocrealIntList = new ArrayList<>();

            String sql = "SELECT *  FROM [sip-channel-map-16]";//查询sip-channel-map信息表

            syncDataAcess(sql, statement, channelidTocrealIntList);

            if(channelidTocrealIntList != null && !channelidTocrealIntList.isEmpty()){
                for (int i = 0; i < channelidTocrealIntList.size(); i++) {
                    ConfChannelidTocrealInt cCTocrealInt = new ConfChannelidTocrealInt();
                    cCTocrealInt.setTB16ID(channelidTocrealIntList.get(i).get("channelcode") != null ? channelidTocrealIntList.get(i).get("channelcode").toString() : "");
                    cCTocrealInt.setCRealintID(channelidTocrealIntList.get(i).get("channelid") != null ? Integer.parseInt(channelidTocrealIntList.get(i).get("channelid").toString()) : 0);

                    ConfChannelidTocrealInt cct = channelidTocrealIntService.getConfChannelidTocrealIntInfo(channelidTocrealIntList.get(i).get("channelcode").toString());
                    if(cct != null){
                        channelidTocrealIntService.updateChannelidTocrealInt(cCTocrealInt);
                    }else{
                        channelidTocrealIntService.insertConfChannelidTocrealInt(cCTocrealInt);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 数据同步处理
     * @param sql
     * @param statement
     * @param list
     */
    public void syncDataAcess(String sql,Statement statement,List<Map<String, Object>> list){
        try {
            ResultSet rs = statement.executeQuery(sql);//执行查询语句，接收结果集

            ResultSetMetaData md = rs.getMetaData();//获取整个数据集的对象

            int columnCount = md.getColumnCount();//获取数据集的大小

            while (rs.next()) {
                Map<String,Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
