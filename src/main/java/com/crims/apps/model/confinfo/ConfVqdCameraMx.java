package com.crims.apps.model.confinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ConfVqdCameraMx implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String deviceid;

    private String analysetype;

    private Double upvalue;

    private Double downvalue;

    private String alarmlevel;

    private Integer usestate;

    private Integer operateaction;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastmodifytime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getAnalysetype() {
        return analysetype;
    }

    public void setAnalysetype(String analysetype) {
        this.analysetype = analysetype == null ? null : analysetype.trim();
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

    public String getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(String alarmlevel) {
        this.alarmlevel = alarmlevel == null ? null : alarmlevel.trim();
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