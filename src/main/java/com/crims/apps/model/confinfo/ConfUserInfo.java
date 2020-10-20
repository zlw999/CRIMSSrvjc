package com.crims.apps.model.confinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
*  ConfUserInfo
* @author 2020-04-26
*/
public class ConfUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleId; //角色编码

    private String userId; //用户编码

    private String userName; //用户名称

    private String password; //密码

    private int priorlevel; //优先级

    private String unitid; //单位

    private String position; //部门

    private String telephone; //座机

    private String mobile; //手机号码

    private String address; //电子邮箱

    private int usestate; //使用状态

    private int operateaction; //当前操作

    private String operateuserid; //操作用户Id

    private String operateusername; //操作用户名

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastModifyTime; //最后修改时间

    private String ip;

    public ConfUserInfo() {
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPriorlevel() {
        return priorlevel;
    }

    public void setPriorlevel(int priorlevel) {
        this.priorlevel = priorlevel;
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}