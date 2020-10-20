package com.crims.apps.model.confinfo;

import java.io.Serializable;

public class ConfChannelnum implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nodeid;

    private Integer videochannelnum; //视频通道数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public Integer getVideochannelnum() {
        return videochannelnum;
    }

    public void setVideochannelnum(Integer videochannelnum) {
        this.videochannelnum = videochannelnum;
    }
}
