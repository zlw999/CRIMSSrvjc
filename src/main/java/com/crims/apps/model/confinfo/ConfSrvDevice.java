package com.crims.apps.model.confinfo;

import java.io.Serializable;

public class ConfSrvDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    private String srvnodeid;

    private String deviceid;

    public String getSrvnodeid() {
        return srvnodeid;
    }

    public void setSrvnodeid(String srvnodeid) {
        this.srvnodeid = srvnodeid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
}
