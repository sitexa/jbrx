package com.www.mall.common.shiro.principal;

import java.io.Serializable;
import java.util.List;

import com.www.mall.common.bean.Menu;

public class Permissions implements Serializable{
	private static final long serialVersionUID = 746399269443060158L;
	
	private List<Menu> menus;
	private String roles;
	
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
}
