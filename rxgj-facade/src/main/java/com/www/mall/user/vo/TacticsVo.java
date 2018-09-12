package com.www.mall.user.vo;

import java.io.Serializable;

public class TacticsVo implements Serializable {
    private static final long serialVersionUID = 7481029910263308438L;
    private Long id;//策略id
    private Long platformId;//平台ID
    private String tacticsSn;//策略编号，用于对接CRM系统的编号
    private String name;//策略名称
    private String minRate;//最小年化率
    private String maxRate;//最大年化率
    private String topRisk;//最大风险
    private Long day;//操作周期（天）
    private Integer flag;//是否关注 0未关注 1已关注
    private String url;//绑定平台url

    public TacticsVo() {
    }

    public TacticsVo(Long id, Long day,Long platformId, String tacticsSn, String name, String minRate, String maxRate, String topRisk, Integer flag, String url) {
        this.id = id;
        this.platformId = platformId;
        this.tacticsSn = tacticsSn;
        this.name = name;
        this.minRate = minRate;
        this.maxRate = maxRate;
        this.topRisk = topRisk;
        this.flag = flag;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
