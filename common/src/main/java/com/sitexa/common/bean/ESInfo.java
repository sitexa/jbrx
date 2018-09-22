package com.sitexa.common.bean;

import io.jboot.config.annotation.PropertyConfig;

/**
 * ------------------------------
 * 搜索引擎配置
 * ------------------------------
 */
@PropertyConfig(prefix = "jboot.es")
public class ESInfo {

    private String ip;
    private int port;
    private String clusterName;
    private String index;
    public String type;
	public String getIp() {
		return ip;
	}
	public int getPort() {
		return port;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getIndex() {
		return index;
	}
	public String getType() {
		return type;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public void setType(String type) {
		this.type = type;
	}

}
