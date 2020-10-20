package com.crims.apps.model.confinfo;

import java.io.Serializable;

public class ConfVqdCameraNum implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer videochannelnum;

    private Integer taskno;

    private String nodeid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideochannelnum() {
        return videochannelnum;
    }

    public void setVideochannelnum(Integer videochannelnum) {
        this.videochannelnum = videochannelnum;
    }

    public Integer getTaskno() {
        return taskno;
    }

    public void setTaskno(Integer taskno) {
        this.taskno = taskno;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }
}
