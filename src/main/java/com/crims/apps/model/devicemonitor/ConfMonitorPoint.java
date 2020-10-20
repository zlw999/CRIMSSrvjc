package com.crims.apps.model.devicemonitor;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ConfMonitorPoint {

    private String mpid;

    private String appsrvid;

    private String deviceid;

    private String mptype;

    private String mpname;

    private String dsp ="";

    private Integer usestate;

    private Integer operateaction;

    /**
     * 最后修改时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastmodifytime =new Date();

    public String getDsp() {
        return dsp;
    }

    public void setDsp(String dsp) {
        this.dsp = dsp;
    }

    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid == null ? null : mpid.trim();
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

    public String getMptype() {
        return mptype;
    }

    public void setMptype(String mptype) {
        this.mptype = mptype;
    }

    public String getMpname() {
        return mpname;
    }

    public void setMpname(String mpname) {
        this.mpname = mpname == null ? null : mpname.trim();
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

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
}