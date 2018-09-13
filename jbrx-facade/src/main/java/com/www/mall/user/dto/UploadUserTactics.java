package com.www.mall.user.dto;

import java.io.Serializable;

/**
 * 用户上传策略表
 */
public class UploadUserTactics implements Serializable {
    private static final long serialVersionUID = 7481029910263308438L;
    private Long id;//用户策略ID
    private Long userId;//用户ID
    private Long tacticsId;//策略ID
    private Long platformId;//平台ID
    private String tacticsSn;//策略编号，用于对接CRM系统的编号
    private String name;//策略名称
    private String minRate;//最小年化率
    private String maxRate;//最大年化率
    private String topRisk;//最大风险
    private Long day;//操作周期（天）
    private Integer status;//1关注2取消关注
    private String createTime;//创建时间
    private String updateTime;//修改时间

    public UploadUserTactics() {
    }

    public UploadUserTactics(Long id, Long userId, Long day, Long tacticsId, Long platformId, String tacticsSn, String name, String minRate, String maxRate, String topRisk, Integer status, String createTime, String updateTime) {
        this.id = id;
        this.tacticsId = tacticsId;
        this.day=day;
        this.userId=userId;
        this.platformId = platformId;
        this.tacticsSn = tacticsSn;
        this.name = name;
        this.minRate = minRate;
        this.maxRate = maxRate;
        this.topRisk = topRisk;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTacticsId() {
        return tacticsId;
    }

    public void setTacticsId(Long tacticsId) {
        this.tacticsId = tacticsId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getTacticsSn() {
        return tacticsSn;
    }

    public void setTacticsSn(String tacticsSn) {
        this.tacticsSn = tacticsSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinRate() {
        return minRate;
    }

    public void setMinRate(String minRate) {
        this.minRate = minRate;
    }

    public String getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(String maxRate) {
        this.maxRate = maxRate;
    }

    public String getTopRisk() {
        return topRisk;
    }

    public void setTopRisk(String topRisk) {
        this.topRisk = topRisk;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
