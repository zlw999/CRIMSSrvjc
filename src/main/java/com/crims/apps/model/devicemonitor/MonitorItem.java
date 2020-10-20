package com.crims.apps.model.devicemonitor;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MonitorItem implements Serializable {

    private static final long serialVersionUID = 1L;

//	@JSONField(ordinal = 1)
	private String type;

//	@JSONField(ordinal = 2)
	private String typeName;
	
//	@JSONField(ordinal = 3)
	private String itemDsp;
/*	
	@JSONField(ordinal = 4)
	private float maxVal;
	
	@JSONField(ordinal = 5)
	private float minVal;
*/	
	@JSONField(ordinal = 4)
	private ArrayList<MonitorThreshold> thresholds=new ArrayList<MonitorThreshold>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	

	public String getTypename() {
		return typeName;
	}

	@JsonProperty(value="typeName")
	public void setTypename(String typeName) {
		this.typeName = typeName;
	}

	public String getItemDsp() {
		return itemDsp;
	}

	public void setItemDsp(String itemDsp) {
		this.itemDsp = itemDsp;
	}

	public List<MonitorThreshold> getThresholds() {
		return thresholds;
	}

	public void setThresholds(ArrayList<MonitorThreshold> thresholds) {
		this.thresholds = thresholds;
	}
	
	
}
