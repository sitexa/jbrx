package com.www.mall.common.bean;

import java.io.Serializable;
/**
 * ------------------------------
 * 
 * Generator by ide
 * ------------------------------
 * @author wdm  @date 2017年11月26日
 * @version 1.0
 */
public class Menu implements Serializable{

	private static final long serialVersionUID = -6628054086762643836L;

    private  long id;  //ID
    private  long parentId;  //父菜单ID
    private  String name;  //菜单名称
    private  String icon;  //图标
    private  String url;  //菜单路径
    private  String visitCode;  	//权限编码
    private  int level;  
    private  String levelCode;  
    private  int type;  //资源类型：1按钮 2链接 3显示菜单
    private  int status;  //状态：1可用  2已禁用
    private  int sort;  //排序号
    private  String createTime;  //创建时间
    private  String updateTime;  //修改时间
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getVisitCode() {
		return visitCode;
	}
	public void setVisitCode(String visitCode) {
		this.visitCode = visitCode;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}