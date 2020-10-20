package com.crims.apps.model.devicemonitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MonitorItemBody implements Serializable {

    private static final long serialVersionUID = 1L;

//	@JSONField(ordinal=1)
	private String id;	
	
//	@JSONField(ordinal=2)
	private String name;
	
//	@JSONField(ordinal=3)
	private String result;
	
//	@JSONField(ordinal=4)
	private HashMap<String, MonitorIndexInfo> indexs = new HashMap<String, MonitorIndexInfo>();

//	@JSONField(ordinal=5)
	private HashMap<String, ArrayList<MonitorItem>> items = new HashMap<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}	

	public HashMap<String, MonitorIndexInfo> getIndexs() {
		return indexs;
	}

	public void setIndexs(HashMap<String, MonitorIndexInfo> indexs) {
		this.indexs = indexs;
	}

	public HashMap<String, ArrayList<MonitorItem>> getItems() {
		return items;
	}

	public void setItems(HashMap<String, ArrayList<MonitorItem>> items) {
		this.items = items;
	}
}
