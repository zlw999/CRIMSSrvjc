package com.crims.apps.model.confinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ConfVideotapeSpace implements Serializable {

    private static final long serialVersionUID = 1L;

    private String channel_id; //录像通道号

    private Integer file_index; //录像文件索引

    private String file_path; //录像文件存储路径,，包含文件名称

    private Integer filesize; //录像文件大小，单位MB

    private String fileiscreated; //录像文件是否已经创建，数据字典M022

    private Integer operateaction; //操作标志

    private String operateuserid; //操作用户id

    private String operateusername; //操作用户名称

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastmodifytime; //最后修改时间

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public Integer getFile_index() {
        return file_index;
    }

    public void setFile_index(Integer file_index) {
        this.file_index = file_index;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public String getFileiscreated() {
        return fileiscreated;
    }

    public void setFileiscreated(String fileiscreated) {
        this.fileiscreated = fileiscreated;
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
