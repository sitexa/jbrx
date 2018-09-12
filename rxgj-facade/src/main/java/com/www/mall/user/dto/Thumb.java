package com.www.mall.user.dto;

import java.io.Serializable;

import com.www.mall.common.validator.VGroup;
import com.www.mall.common.validator.VRule;
import com.www.mall.common.validator.VRules;
import com.www.mall.common.validator.VType;

/**
 * ------------------------------ Automatic Generator
 * ------------------------------
 * 
 * @author wdm @date 2018年01月26日
 * @version 1.0
 */
@VRules(rules={
        @VRule(attrName = "type", message="点赞方式不能为空", groups = { VGroup.save,VGroup.excutor },  types = { VType.number } ),
        @VRule(attrName = "userId", message="唯一标识不能为空",  groups = { VGroup.save },  types = { VType.notnull } )
})
public class Thumb implements Serializable {

	private static final long serialVersionUID = 1500544669753534522L;
	private java.lang.Long id;
	private java.lang.String userId;
	private java.lang.Integer type;
	private java.lang.Long aId;
	private java.lang.String createTime;
	private java.lang.String updateTime;

	public Thumb() {
	}

	public Thumb(java.lang.Long id, java.lang.String userId, java.lang.Integer type, java.lang.Long aId,
			java.lang.String createTime, java.lang.String updateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.type = type;
		this.aId = aId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	public java.lang.Integer getType() {
		return type;
	}

	public void setAId(java.lang.Long aId) {
		this.aId = aId;
	}

	public java.lang.Long getAId() {
		return aId;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(java.lang.String updateTime) {
		this.updateTime = updateTime;
	}

	public java.lang.String getUpdateTime() {
		return updateTime;
	}

}