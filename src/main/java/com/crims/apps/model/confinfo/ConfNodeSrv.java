package com.crims.apps.model.confinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ConfNodeSrv implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nodeid; //服务节点ID

    private String nodename; //服务节点名称

    private String nodetype; //节点类型，数据字典M009

    private String factoryid; //厂家ID，数据字典M017

    private String ip; //IP地址

    private Integer sigport; //信令端口

    private Integer streamport; //流媒体端口

    private Integer transmode; //流传输模式，数据字典M025

    private String multicastip; //组播IP地址

    private Integer multicastport; //组播端口

    private String mannodeid; //管理节点ID

    private String tmannodeid; //上级管理节点ID

    private String routeip; //路由IP

    private Integer routeport; //路由port

    private String username; //用户名称

    private String password; //登录密码

    private String reserve; //预留

    private Integer maxdispatch; //最大路数

    private Integer interfacetype; //预留

    private Integer usestate;

    private Integer operateaction;

    private String operateuserid;

    private String operateusername;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastmodifytime;

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid == null ? null : nodeid.trim();
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename == null ? null : nodename.trim();
    }

    public String getNodetype() {
        return nodetype;
    }

    public void setNodetype(String nodetype) {
        this.nodetype = nodetype == null ? null : nodetype.trim();
    }

    public String getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(String factoryid) {
        this.factoryid = factoryid == null ? null : factoryid.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getSigport() {
        return sigport;
    }

    public void setSigport(Integer sigport) {
        this.sigport = sigport;
    }

    public Integer getStreamport() {
        return streamport;
    }

    public void setStreamport(Integer streamport) {
        this.streamport = streamport;
    }

    public Integer getTransmode() {
        return transmode;
    }

    public void setTransmode(Integer transmode) {
        this.transmode = transmode;
    }

    public String getMulticastip() {
        return multicastip;
    }

    public void setMulticastip(String multicastip) {
        this.multicastip = multicastip == null ? null : multicastip.trim();
    }

    public Integer getMulticastport() {
        return multicastport;
    }

    public void setMulticastport(Integer multicastport) {
        this.multicastport = multicastport;
    }

    public String getMannodeid() {
        return mannodeid;
    }

    public void setMannodeid(String mannodeid) {
        this.mannodeid = mannodeid == null ? null : mannodeid.trim();
    }

    public String getTmannodeid() {
        return tmannodeid;
    }

    public void setTmannodeid(String tmannodeid) {
        this.tmannodeid = tmannodeid == null ? null : tmannodeid.trim();
    }

    public String getRouteip() {
        return routeip;
    }

    public void setRouteip(String routeip) {
        this.routeip = routeip == null ? null : routeip.trim();
    }

    public Integer getRouteport() {
        return routeport;
    }

    public void setRouteport(Integer routeport) {
        this.routeport = routeport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public Integer getMaxdispatch() {
        return maxdispatch;
    }

    public void setMaxdispatch(Integer maxdispatch) {
        this.maxdispatch = maxdispatch;
    }

    public Integer getInterfacetype() {
        return interfacetype;
    }

    public void setInterfacetype(Integer interfacetype) {
        this.interfacetype = interfacetype;
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
        this.operateuserid = operateuserid == null ? null : operateuserid.trim();
    }

    public String getOperateusername() {
        return operateusername;
    }

    public void setOperateusername(String operateusername) {
        this.operateusername = operateusername == null ? null : operateusername.trim();
    }

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
}