package com.www.mall.user.dto;

import java.io.Serializable;

public class Platform implements Serializable {
    private static final long serialVersionUID = 7481029910263308438L;
    private Long id;
    private String name;//平台名称
    private Long day;//运转天数
    private String descx;//平台描述
    private String investMoney;//投资总额
    private String shareMoney;//分享财富
    private String url;//加入平台url
    private Integer status;//1启用2禁用3删除
    private String createTime;//创建时间
    private String updateTime;//修改时间

    public Platform() {
    }

    public Platform(Long id, String name, Long day, String descx, String investMoney, String shareMoney, String url, Integer status, String createTime, String updateTime) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.descx = descx;
        this.investMoney = investMoney;
        this.shareMoney = shareMoney;
        this.url = url;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public String getDescx() {
        return descx;
    }

    public void setDescx(String descx) {
        this.descx = descx;
    }

    public String getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(String investMoney) {
        this.investMoney = investMoney;
    }

    public String getShareMoney() {
        return shareMoney;
    }

    public void setShareMoney(String shareMoney) {
        this.shareMoney = shareMoney;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
