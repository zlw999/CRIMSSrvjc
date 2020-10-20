package com.crims.apps.model.confinfo;

import java.io.Serializable;

public class ConfChannelidTocrealInt implements Serializable {

    private static final long serialVersionUID = 1L;

    private String TB16ID; //铁标575-16位编码

    private Integer CRealintID; //瑞尔整形编码

    public String getTB16ID() {
        return TB16ID;
    }

    public void setTB16ID(String TB16ID) {
        this.TB16ID = TB16ID;
    }

    public Integer getCRealintID() {
        return CRealintID;
    }

    public void setCRealintID(Integer CRealintID) {
        this.CRealintID = CRealintID;
    }
}
