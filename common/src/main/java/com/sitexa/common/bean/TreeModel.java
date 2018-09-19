package com.sitexa.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------
 * tree
 * ------------------------------
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
