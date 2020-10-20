package com.crims.apps.model.confinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfVqdCameraTask implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer taskno;

    private String taskname;

    private String vqdsvrid;

    private String rtuid;

    private String protocoltype;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startdate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date enddate;

    private String days;

    private String timeranges;

    private Integer vqdcount;

    private Integer usestate;

    private Integer operateaction;

    private String lastmodifyuser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastmodifytime;

    private String nodename;

    private String analysetype;

    private List<ConfVqdCamera> confVqdCameraList = new ArrayList<>();

    private String tasktype;

    public Integer getTaskno() {
        return taskno;
    }

    public void setTaskno(Integer taskno) {
        this.taskno = taskno;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname == null ? null : taskname.trim();
    }

    public String getVqdsvrid() {
        return vqdsvrid;
    }

    public void setVqdsvrid(String vqdsvrid) {
        this.vqdsvrid = vqdsvrid == null ? null : vqdsvrid.trim();
    }

    public String getRtuid() {
        return rtuid;
    }

    public void setRtuid(String rtuid) {
        this.rtuid = rtuid == null ? null : rtuid.trim();
    }

    public String getProtocoltype() {
        return protocoltype;
    }

    public void setProtocoltype(String protocoltype) {
        this.protocoltype = protocoltype == null ? null : protocoltype.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days == null ? null : days.trim();
    }

    public String getTimeranges() {
        return timeranges;
    }

    public void setTimeranges(String timeranges) {
        this.timeranges = timeranges == null ? null : timeranges.trim();
    }

    public Integer getVqdcount() {
        return vqdcount;
    }

    public void setVqdcount(Integer vqdcount) {
        this.vqdcount = vqdcount;
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

    public String getLastmodifyuser() {
        return lastmodifyuser;
    }

    public void setLastmodifyuser(String lastmodifyuser) {
        this.lastmodifyuser = lastmodifyuser == null ? null : lastmodifyuser.trim();
    }

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public List<ConfVqdCamera> getConfVqdCameraList() {
        return confVqdCameraList;
    }

    public void setConfVqdCameraList(List<ConfVqdCamera> confVqdCameraList) {
        this.confVqdCameraList = confVqdCameraList;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public String getAnalysetype() {
        return analysetype;
    }

    public void setAnalysetype(String analysetype) {
        this.analysetype = analysetype;
    }
}