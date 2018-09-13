package com.www.mall.user.vo;

import java.io.Serializable;

public class PlatformVo implements Serializable {
    private static final long serialVersionUID = 7481029910263308438L;
    private Long id;
    private String name;//平台名称
    private Long day;//运转天数
    private String descx;//平台描述
    private String investMoney;//投资总额
    private String shareMoney;//分享财富
    private String url;//加入平台url
    private Integer flag;//0未关注1已关注

    public PlatformVo() {
    }

    public PlatformVo(Long id, String name, Long day, String descx, String investMoney, String shareMoney, String url, Integer flag) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.descx = descx;
        this.investMoney = investMoney;
        this.shareMoney = shareMoney;
        this.url = url;
        this.flag = flag;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
