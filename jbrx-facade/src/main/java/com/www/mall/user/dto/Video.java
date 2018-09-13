package com.www.mall.user.dto;

import java.io.Serializable;
import java.util.List;

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
		@VRule(attrName = "path", message = "视频地址不能为空", groups = { VGroup.save, VGroup.excutor }, types = {
				VType.notnull }),
		@VRule(attrName = "title", message = "视频标题不能为空", groups = { VGroup.save }, types = { VType.notnull }) })
public class Video implements Serializable {

	private static final long serialVersionUID = -2349761303433490856L;
	private java.lang.Long id;
	private java.lang.Long userId; // 视频创建用户
	private java.lang.String title; // 视频标题
	private java.lang.String path; // 视频路径
	private java.lang.Long count; // 观看视频次数
	private java.lang.String times; // 视频时长
	private java.lang.Integer state; // 是否被删除
	private java.lang.String createTime;
	private java.lang.String updateTime;
	private java.lang.String img; // 图片路径
	
	private String deviceId; //设备唯一主键

	private Integer videoCount; // 记录视频的点赞数据
	private boolean isThumb; // 当前用户是否已点赞过
	private Integer commentCount; // 当前视频评论总数
	
	private String userName; //视频是谁发布的
	private String userPic; //用户头像地址

	public Video() {
	}

	public Video(java.lang.Long id, java.lang.Long userId, java.lang.String title, java.lang.String path,
			java.lang.Long count, java.lang.String times, java.lang.Integer state, java.lang.String createTime,
			java.lang.String updateTime, Integer videoCount, boolean isThumb, Integer commentCount,
			List<Video> listVideo, java.lang.String img,String userName,String userPic,String deviceId) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.path = path;
		this.count = count;
		this.times = times;
		this.state = state;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.videoCount = videoCount;
		this.isThumb = isThumb;
		this.commentCount = commentCount;
		this.img = img;
		this.userName = userName;
		this.userPic = userPic;
		this.deviceId = deviceId;
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

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setPath(java.lang.String path) {
		this.path = path;
	}

	public java.lang.String getPath() {
		return path;
	}

	public void setCount(java.lang.Long count) {
		this.count = count;
	}

	public java.lang.Long getCount() {
		return count;
	}

	public void setTimes(java.lang.String times) {
		this.times = times;
	}

	public java.lang.String getTimes() {
		return times;
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

	public Integer getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(Integer videoCount) {
		this.videoCount = videoCount;
	}

	public boolean getIsThumb() {
		return isThumb;
	}

	public void setIsThumb(boolean isThumb) {
		this.isThumb = isThumb;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public java.lang.String getImg() {
		return img;
	}

	public void setImg(java.lang.String img) {
		this.img = img;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}