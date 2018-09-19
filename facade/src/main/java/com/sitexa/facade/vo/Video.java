package com.sitexa.facade.vo;


import com.sitexa.common.validator.VGroup;
import com.sitexa.common.validator.VRule;
import com.sitexa.common.validator.VRules;
import com.sitexa.common.validator.VType;

import java.io.Serializable;
import java.util.List;

@VRules(rules = {
        @VRule(attrName = "path", message = "视频地址不能为空", groups = {VGroup.save, VGroup.excutor}, types = {VType.notnull}),
        @VRule(attrName = "title", message = "视频标题不能为空", groups = {VGroup.save}, types = {VType.notnull})
})
public class Video implements Serializable {

    private static final long serialVersionUID = -2349761303433490856L;
    private Long id;
    private Long userId; // 视频创建用户
    private String title; // 视频标题
    private String path; // 视频路径
    private Long count; // 观看视频次数
    private String times; // 视频时长
    private Integer state; // 是否被删除
    private String createTime;
    private String updateTime;
    private String img; // 图片路径

    private String deviceId; //设备唯一主键

    private Integer videoCount; // 记录视频的点赞数据
    private boolean isThumb; // 当前用户是否已点赞过
    private Integer commentCount; // 当前视频评论总数

    private String userName; //视频是谁发布的
    private String userPic; //用户头像地址

    public Video() {
    }

    public Video(Long id, Long userId, String title, String path,
                 Long count, String times, Integer state, String createTime,
                 String updateTime, Integer videoCount, boolean isThumb, Integer commentCount,
                 List<Video> listVideo, String img, String userName, String userPic, String deviceId) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTimes() {
        return times;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTime() {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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