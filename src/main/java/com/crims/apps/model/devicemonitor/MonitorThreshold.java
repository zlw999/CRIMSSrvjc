package com.crims.apps.model.devicemonitor;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class MonitorThreshold implements Serializable {

    private static final long serialVersionUID = 1L;

	@JSONField(ordinal=1 /*serializeUsing = CustomDWORDToHexSerialize.class*/)
	private String level;
	
	@JSONField(ordinal=2)
	private Integer delaytime;
	
	@JSONField(ordinal=3/*, serializeUsing = CustomDWORDToHexSerialize.class*/)
	private String alarmtype;
	
	@JSONField(ordinal=4)
	private float downval;
	
	@JSONField(ordinal=5)
	private float upval;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getDelaytime() {
        return delaytime;
    }

    public void setDelaytime(Integer delaytime) {
        this.delaytime = delaytime;
    }

    public String getAlarmtype() {
        return alarmtype;
    }

    public void setAlarmtype(String alarmtype) {
        this.alarmtype = alarmtype;
    }

    public float getDownval() {
		return downval;
	}

	public void setDownval(float downval) {
		this.downval = downval;
	}

	public float getUpval() {
		return upval;
	}

	public void setUpval(float upval) {
		this.upval = upval;
	}

}
