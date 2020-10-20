package com.crims.apps.model.useronline;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ConfUserOnline implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private String username;

    private Integer loginstate;

    private Integer requestvideocount;

    private String onlinestatu;

    private String ip;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastmodifytime;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLoginstate() {
        return loginstate;
    }

    public void setLoginstate(Integer loginstate) {
        this.loginstate = loginstate;
    }

    public Integer getRequestvideocount() {
        return requestvideocount;
    }

    public void setRequestvideocount(Integer requestvideocount) {
        this.requestvideocount = requestvideocount;
    }

    public String getOnlinestatu() {
        return onlinestatu;
    }

    public void setOnlinestatu(String onlinestatu) {
        this.onlinestatu = onlinestatu;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
}
