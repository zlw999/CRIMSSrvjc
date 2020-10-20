package com.crims.apps.model.confinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ConfRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleid; //角色编码

    private String rolename; //角色名称

    private int maxcontlchnel; //视频调度数量

    private int onlinecontrl; //在线控制

    private String operateright; //权限

    private int usestate; //使用状态

    private int operateaction; //当前操作

    private String operateuserid; //操作用户Id

    private String operateusername; //操作用户名

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastmodifytime; //最后修改时间

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public int getMaxcontlchnel() {
        return maxcontlchnel;
    }

    public void setMaxcontlchnel(int maxcontlchnel) {
        this.maxcontlchnel = maxcontlchnel;
    }

    public int getOnlinecontrl() {
        return onlinecontrl;
    }

    public void setOnlinecontrl(int onlinecontrl) {
        this.onlinecontrl = onlinecontrl;
    }

    public String getOperateright() {
        return operateright;
    }

    public void setOperateright(String operateright) {
        this.operateright = operateright;
    }

    public int getUsestate() {
        return usestate;
    }

    public void setUsestate(int usestate) {
        this.usestate = usestate;
    }

    public int getOperateaction() {
        return operateaction;
    }

    public void setOperateaction(int operateaction) {
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
