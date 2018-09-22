package com.sitexa.facade.vo;

import com.sitexa.common.validator.VGroup;
import com.sitexa.common.validator.VRule;
import com.sitexa.common.validator.VRules;
import com.sitexa.common.validator.VType;

import java.io.Serializable;

@VRules(rules = {
        @VRule(attrName = "type", message = "点赞方式不能为空", groups = {VGroup.save, VGroup.excutor}, types = {VType.number}),
        @VRule(attrName = "userId", message = "唯一标识不能为空", groups = {VGroup.save}, types = {VType.notnull})
})
public class Thumb implements Serializable {

    private static final long serialVersionUID = 1500544669753534522L;
    private Long id;
    private String userId;
    private Integer type;
    private Long aId;
    private String createTime;
    private String updateTime;

    public Thumb() {
    }

    public Thumb(Long id, String userId, Integer type, Long aId, String createTime, String updateTime) {
        super();
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.aId = aId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setAId(Long aId) {
        this.aId = aId;
    }

    public Long getAId() {
        return aId;
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

}