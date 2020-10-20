package com.crims.apps.model.devicemonitor;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ConfMpattribute {

    private String mpid;

    private Integer id;

    private String appsrvid;

    private String deviceid;

    private String alarmtype;

    private Integer mpvaluetype;

    private String mpvalueunit;

    private Integer ctrlbitflag;

    private Float valueprecision;

    private Float mpchangerate;

    private Float mpvaluecfactoradd;

    private Float mpvaluecfactorride;

    private Integer maxeffectvalue;

    private Integer mineffectvalue;

    private Integer mpmodel;

    private String mpcaptureparam;

    private String mpsaveparam;

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


    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid == null ? null : mpid.trim();
    }

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

    public String getAlarmtype() {
        return alarmtype;
    }

    public void setAlarmtype(String alarmtype) {
        this.alarmtype = alarmtype;
    }

    public Integer getMpvaluetype() {
        return mpvaluetype;
    }

    public void setMpvaluetype(Integer mpvaluetype) {
        this.mpvaluetype = mpvaluetype;
    }

    public String getMpvalueunit() {
        return mpvalueunit;
    }

    public void setMpvalueunit(String mpvalueunit) {
        this.mpvalueunit = mpvalueunit;
    }

    public Integer getCtrlbitflag() {
        return ctrlbitflag;
    }

    public void setCtrlbitflag(Integer ctrlbitflag) {
        this.ctrlbitflag = ctrlbitflag;
    }

    public Float getValueprecision() {
        return valueprecision;
    }

    public void setValueprecision(Float valueprecision) {
        this.valueprecision = valueprecision;
    }

    public Float getMpchangerate() {
        return mpchangerate;
    }

    public void setMpchangerate(Float mpchangerate) {
        this.mpchangerate = mpchangerate;
    }

    public Float getMpvaluecfactoradd() {
        return mpvaluecfactoradd;
    }

    public void setMpvaluecfactoradd(Float mpvaluecfactoradd) {
        this.mpvaluecfactoradd = mpvaluecfactoradd;
    }

    public Float getMpvaluecfactorride() {
        return mpvaluecfactorride;
    }

    public void setMpvaluecfactorride(Float mpvaluecfactorride) {
        this.mpvaluecfactorride = mpvaluecfactorride;
    }

    public Integer getMaxeffectvalue() {
        return maxeffectvalue;
    }

    public void setMaxeffectvalue(Integer maxeffectvalue) {
        this.maxeffectvalue = maxeffectvalue;
    }

    public Integer getMineffectvalue() {
        return mineffectvalue;
    }

    public void setMineffectvalue(Integer mineffectvalue) {
        this.mineffectvalue = mineffectvalue;
    }

    public Integer getMpmodel() {
        return mpmodel;
    }

    public void setMpmodel(Integer mpmodel) {
        this.mpmodel = mpmodel;
    }

    public String getMpcaptureparam() {
        return mpcaptureparam;
    }

    public void setMpcaptureparam(String mpcaptureparam) {
        this.mpcaptureparam = mpcaptureparam == null ? null : mpcaptureparam.trim();
    }

    public String getMpsaveparam() {
        return mpsaveparam;
    }

    public void setMpsaveparam(String mpsaveparam) {
        this.mpsaveparam = mpsaveparam == null ? null : mpsaveparam.trim();
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