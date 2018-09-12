package com.wxh.mall.bin;

import java.io.Serializable;
import java.util.List;

public class Area implements Serializable{
	
	private static final long serialVersionUID = 5319883141364188049L;
	
	private String code;
	private String name;
	private String longitude;
	private String latitude;
	private int level;
	private List<Area> child;
	
	public Area(){}
	
	public Area(String code, String name, String longitude, String latitude, int level, List<Area> child) {
		super();
		this.code = code;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.level = level;
		this.child = child;
	}

	public Area(String code, String name, int level, List<Area> child) {
		super();
		this.code = code;
		this.name = name;
		this.level = level;
		this.child = child;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public List<Area> getChild() {
		return child;
	}

	public void setChild(List<Area> child) {
		this.child = child;
	}
	
}
