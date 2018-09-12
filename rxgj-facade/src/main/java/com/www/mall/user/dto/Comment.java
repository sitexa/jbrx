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
@VRules(rules = {
		@VRule(attrName = "videoId", message = "视频主键不能为空", groups = { VGroup.save, VGroup.excutor }, types = {
				VType.notnull }),
		@VRule(attrName = "content", message = "评论内容不能为空", groups = { VGroup.save }, types = { VType.notnull }) })
public class Comment implements Serializable {

	private static final long serialVersionUID = 7496284643005081005L;
	private java.lang.Long id;
	private java.lang.Long videoId;
	private java.lang.Long userId;
	private java.lang.String userName;
	private java.lang.String content;
	private java.lang.Integer state;
	private java.lang.Integer iscomment;
	private java.lang.String createTime;
	private java.lang.String updateTime;
	
	private Integer commentThumb; //当前评论的点赞数
	private boolean isThumb; //当前用户是否已点赞过
	private String userPic ; //用户头像

	public Comment() {
	}

	public Comment(java.lang.Long id, java.lang.Long videoId, java.lang.Long userId, java.lang.String userName,
			java.lang.String content, java.lang.Integer state, java.lang.String createTime, java.lang.String updateTime,
			java.lang.Integer iscomment,Integer commentThumb, boolean isThumb,String userPic ) {
		super();
		this.id = id;
		this.videoId = videoId;
		this.userId = userId;
		this.userName = userName;
		this.content = content;
		this.state = state;
		this.iscomment = iscomment;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.commentThumb = commentThumb;
		this.isThumb = isThumb;
		this.userPic = userPic;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	public java.lang.Long getUserId() {
		return userId;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	public java.lang.Integer getState() {
		return state;
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

	public java.lang.Long getVideoId() {
		return videoId;
	}

	public void setVideoId(java.lang.Long videoId) {
		this.videoId = videoId;
	}

	public java.lang.Integer getIscomment() {
		return iscomment;
	}

	public void setIscomment(java.lang.Integer iscomment) {
		this.iscomment = iscomment;
	}

	public Integer getCommentThumb() {
		return commentThumb;
	}

	public void setCommentThumb(Integer commentThumb) {
		this.commentThumb = commentThumb;
	}

	public boolean getIsThumb() {
		return isThumb;
	}

	public void setIsThumb(boolean isThumb) {
		this.isThumb = isThumb;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}


}