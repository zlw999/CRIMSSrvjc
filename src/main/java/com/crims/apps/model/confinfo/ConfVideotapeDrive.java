package com.crims.apps.model.confinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ConfVideotapeDrive implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String drivename; //录像盘符

    private String totalCapacity; //磁盘总容量

    private String usedCapacity; //磁盘已用容量

    private String freeCapacity; //磁盘可用容量

    private Integer operateaction; //操作标志

    private String operateuserid; //操作用户id

    private String operateusername; //操作用户名称

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastmodifytime; //最后修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrivename() {
        return drivename;
    }

    public void setDrivename(String drivename) {
        this.drivename = drivename;
    }

    public String getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(String totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public String getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(String usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public String getFreeCapacity() {
        return freeCapacity;
    }

    public void setFreeCapacity(String freeCapacity) {
        this.freeCapacity = freeCapacity;
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

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
}
