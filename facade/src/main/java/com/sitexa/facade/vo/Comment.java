package com.sitexa.facade.vo;


import com.sitexa.common.validator.VGroup;
import com.sitexa.common.validator.VRule;
import com.sitexa.common.validator.VRules;
import com.sitexa.common.validator.VType;

import java.io.Serializable;

@VRules(rules = {
        @VRule(attrName = "videoId", message = "视频主键不能为空", groups = {VGroup.save, VGroup.excutor}, types = {VType.notnull}),
        @VRule(attrName = "content", message = "评论内容不能为空", groups = {VGroup.save}, types = {VType.notnull})
})
public class Comment implements Serializable {

    private static final long serialVersionUID = 7496284643005081005L;
    private Long id;
    private Long videoId;
    private Long userId;
    private String userName;
    private String content;
    private Integer state;
    private Integer iscomment;
    private String createTime;
    private String updateTime;

    private Integer commentThumb; //当前评论的点赞数
    private boolean isThumb; //当前用户是否已点赞过
    private String userPic; //用户头像

    public Comment() {
    }

    public Comment(Long id, Long videoId, Long userId, String userName,
                   String content, Integer state, String createTime, String updateTime,
                   Integer iscomment, Integer commentThumb, boolean isThumb, String userPic) {
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
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

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Integer getIscomment() {
        return iscomment;
    }

    public void setIscomment(Integer iscomment) {
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