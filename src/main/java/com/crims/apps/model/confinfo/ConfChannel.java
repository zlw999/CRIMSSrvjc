package com.crims.apps.model.confinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ConfChannel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String channelid; //通道ID

    private String channelname; //通道名称

    private String typeid; //通道类型

    private String version; //版本

    private String factoryid; //厂家ID

    private String location; //位置

    private String dsp; //描述

    private String mappingchannelid; //映射通道ID

    private String groupid; //组ID

    private String groupname; //组名称

    private Integer videoenctype; //解码类型

    private Integer streamtype; //流类型

    private Integer mainresolution; //分辨率(主码流)

    private Integer mainstreamframerate; //帧率(主码流)

    private Integer mainstreamgop;

    private Integer subresolution; //分辨率(子码流)

    private String srcnodeid; //源节点ID

    private String deviceid; //设备ID

    private Integer usestate; //数据字典0x00010100定义

    private Integer operateaction;

    private String operateuserid;

    private String operateusername;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastModifyTime;

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(String factoryid) {
        this.factoryid = factoryid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDsp() {
        return dsp;
    }

    public void setDsp(String dsp) {
        this.dsp = dsp;
    }

    public String getMappingchannelid() {
        return mappingchannelid;
    }

    public void setMappingchannelid(String mappingchannelid) {
        this.mappingchannelid = mappingchannelid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getVideoenctype() {
        return videoenctype;
    }

    public void setVideoenctype(Integer videoenctype) {
        this.videoenctype = videoenctype;
    }

    public Integer getStreamtype() {
        return streamtype;
    }

    public void setStreamtype(Integer streamtype) {
        this.streamtype = streamtype;
    }

    public Integer getMainresolution() {
        return mainresolution;
    }

    public void setMainresolution(Integer mainresolution) {
        this.mainresolution = mainresolution;
    }

    public Integer getMainstreamframerate() {
        return mainstreamframerate;
    }

    public void setMainstreamframerate(Integer mainstreamframerate) {
        this.mainstreamframerate = mainstreamframerate;
    }

    public Integer getMainstreamgop() {
        return mainstreamgop;
    }

    public void setMainstreamgop(Integer mainstreamgop) {
        this.mainstreamgop = mainstreamgop;
    }

    public Integer getSubresolution() {
        return subresolution;
    }

    public void setSubresolution(Integer subresolution) {
        this.subresolution = subresolution;
    }

    public String getSrcnodeid() {
        return srcnodeid;
    }

    public void setSrcnodeid(String srcnodeid) {
        this.srcnodeid = srcnodeid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Integer getUsestate() {
        return usestate;
    }

    public void setUsestate(Integer usestate) {
        this.usestate = usestate;
    }

    public Integer getOperateaction() {
        return operateaction;
    }

    public void setOperateaction(Integer operateaction) {
        this.operateaction = operateaction;
    }

    public String getOperateuserid() {
        return operateuserid;
    }

    public void setOperateuserid(String operateuserid) {
        this.operateuserid = operateuserid;
    }

    public String getOperateusername() {
        return operateusername;
    }

    public void setOperateusername(String operateusername) {
        this.operateusername = operateusername;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
