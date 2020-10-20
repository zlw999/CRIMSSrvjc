package com.crims.apps.model.confinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ConfVqdCamera implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deviceid;

    private Integer taskNo;

    private Integer usestate;

    private Integer operateaction;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastmodifytime;

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Integer getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(Integer taskNo) {
        this.taskNo = taskNo;
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
