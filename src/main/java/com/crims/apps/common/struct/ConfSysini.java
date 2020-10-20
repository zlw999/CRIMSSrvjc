package com.crims.apps.common.struct;

import java.io.Serializable;

public class ConfSysini implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String key;

    private String value;

    private String dsp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDsp() {
        return dsp;
    }

    public void setDsp(String dsp) {
        this.dsp = dsp;
    }

}
