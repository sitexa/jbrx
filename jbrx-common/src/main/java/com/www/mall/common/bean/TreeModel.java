package com.www.mall.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------
 * tree
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年6月13日
 * @version 1.0
 */
public class TreeModel {
	private Object parent;
	private List<Object> children=new ArrayList<Object>();
	public Object getObject() {
		return parent;
	}
	public void setObject(Object parent) {
		this.parent = parent;
	}
	public List<Object> getChildren() {
		return children;
	}
	public void setChildren(List<Object> children) {
		this.children = children;
	}
	
    public void addChild(Object child) {
        this.children.add(child);
    }
}
