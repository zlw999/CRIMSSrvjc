package com.crims.apps.model.devicemonitor;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ConfAlarmthreshold implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String appsrvid;

    private String deviceid;

    private String mpid;

    private Double upvalue;

    private Double downvalue;

    private Integer arisedela;

    private Integer disdelay;

    private String alarmsubtype;

    private String alarmlevel;

    private Integer operateaction;

    /**
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastmodifytime=new Date();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppsrvid() {
        return appsrvid;
    }

    public void setAppsrvid(String appsrvid) {
        this.appsrvid = appsrvid == null ? null : appsrvid.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid == null ? null : mpid.trim();
    }

    public Double getUpvalue() {
        return upvalue;
    }

    public void setUpvalue(Double upvalue) {
        this.upvalue = upvalue;
    }

    public Double getDownvalue() {
        return downvalue;
    }

    public void setDownvalue(Double downvalue) {
        this.downvalue = downvalue;
    }

    public Integer getArisedela() {
        return arisedela;
    }

    public void setArisedela(Integer arisedela) {
        this.arisedela = arisedela;
    }

    public Integer getDisdelay() {
        return disdelay;
    }

    public void setDisdelay(Integer disdelay) {
        this.disdelay = disdelay;
    }

    public String getAlarmsubtype() {
        return alarmsubtype;
    }

    public void setAlarmsubtype(String alarmsubtype) {
        this.alarmsubtype = alarmsubtype;
    }

    public String getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(String alarmlevel) {
        this.alarmlevel = alarmlevel;
    }

    public Integer getOperateaction() {
        return operateaction;
    }

    public void setOperateaction(Integer operateaction) {
        this.operateaction = operateaction;
    }

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
}