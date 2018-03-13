package com.tenmaker.backupwd.util.kuaidi;

public class KuaidiData {
	private String time; // 更新时间
	private String ftime;
	private String location;// 当前所在地点
	private String context; // 描述

	public KuaidiData() {

	}

	public KuaidiData(String time, String ftime, String location, String context) {
		super();
		this.time = time;
		this.ftime = ftime;
		this.location = location;
		this.context = context;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFtime() {
		return ftime;
	}

	public void setFtime(String ftime) {
		this.ftime = ftime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
