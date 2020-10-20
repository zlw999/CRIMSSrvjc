package com.crims.apps.model.devicemonitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MonitorIndexInfo implements Serializable {

    private static final long serialVersionUID = 1L;
	
//	@JSONField(ordinal=1)
	private boolean selected = true;
	
//	@JSONField(ordinal=2)
	private String colName;

//	@JSONField(ordinal=3)
	private List<String> rowVal = new ArrayList<String>();

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public List<String> getRowVal() {
		return rowVal;
	}

	public void setRowVal(List<String> rowVal) {
		this.rowVal = rowVal;
	}


	
}
