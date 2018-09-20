package com.sitexa.facade.vo;

import java.io.Serializable;

public class Banner implements Serializable {
    private static final long serialVersionUID = -1674393695659741786L;
    private Long id;
    private String name;//广告名称
    private Integer category;//栏目 1 启动页 2 首页banner
    private String murl;//图片地址
    private String aurl;//跳转地址
    private String startDate;//开始时间
    private String endDate;//结束时间
    private int status;//1可用2删除
    private Long count;//点击次数
    private String createTime;//创建时间
    private String updateTime;//修改时间

    public Banner() {
    }

    public Banner(Long id,String name, Integer category,String murl, String aurl, String startDate, String endDate, int status, Long count, String createTime, String updateTime) {
        this.id = id;
        this.category = category;
        this.murl = murl;
        this.aurl = aurl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.count = count;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMurl() {
        return murl;
    }

    public void setMurl(String murl) {
        this.murl = murl;
    }

    public String getAurl() {
        return aurl;
    }

    public void setAurl(String aurl) {
        this.aurl = aurl;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
