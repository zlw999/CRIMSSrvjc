package com.crims.apps.model.operlog;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class RecOperateinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userid;

    private String username;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date operatedt;

    private String operatenodeid;

    private String operatenodename;

    private String operateobjid;

    private String operateobjname;

    private String operateobjtype;

    private String operatety;

    private String operatedsp;

    private String nodeid;

    private String usercls;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getOperatedt() {
        return operatedt;
    }

    public void setOperatedt(Date operatedt) {
        this.operatedt = operatedt;
    }

    public String getOperatenodeid() {
        return operatenodeid;
    }

    public void setOperatenodeid(String operatenodeid) {
        this.operatenodeid = operatenodeid;
    }

    public String getOperatenodename() {
        return operatenodename;
    }

    public void setOperatenodename(String operatenodename) {
        this.operatenodename = operatenodename;
    }

    public String getOperateobjid() {
        return operateobjid;
    }

    public void setOperateobjid(String operateobjid) {
        this.operateobjid = operateobjid;
    }

    public String getOperateobjname() {
        return operateobjname;
    }

    public void setOperateobjname(String operateobjname) {
        this.operateobjname = operateobjname;
    }

    public String getOperateobjtype() {
        return operateobjtype;
    }

    public void setOperateobjtype(String operateobjtype) {
        this.operateobjtype = operateobjtype;
    }

    public String getOperatety() {
        return operatety;
    }

    public void setOperatety(String operatety) {
        this.operatety = operatety;
    }

    public String getOperatedsp() {
        return operatedsp;
    }

    public void setOperatedsp(String operatedsp) {
        this.operatedsp = operatedsp;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getUsercls() {
        return usercls;
    }

    public void setUsercls(String usercls) {
        this.usercls = usercls;
    }
}
