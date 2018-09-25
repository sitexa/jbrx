package com.sitexa.facade.vo;

import java.io.Serializable;
import java.util.Date;

public class AppVersion implements Serializable {
    private static final long serialVersionUID = 8374392582881604905L;

    private int id;
    private String versionNumber;
    private int currentVersion;
    private int updateMethod;
    private String updateContent;
    private String pkgUrl;
    private String androidUrl;
    private String iosUr;
    private Date createTime;
    private Long createUser;

    public AppVersion() {
    }

    public AppVersion(int id, String versionNumber, int currentVersion, int updateMethod, String updateContent, String pkgUrl, String androidUrl, String iosUr) {
        this.id = id;
        this.versionNumber = versionNumber;
        this.currentVersion = currentVersion;
        this.updateMethod = updateMethod;
        this.updateContent = updateContent;
        this.pkgUrl = pkgUrl;
        this.androidUrl = androidUrl;
        this.iosUr = iosUr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
    }

    public int getUpdateMethod() {
        return updateMethod;
    }

    public void setUpdateMethod(int updateMethod) {
        this.updateMethod = updateMethod;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getPkgUrl() {
        return pkgUrl;
    }

    public void setPkgUrl(String pkgUrl) {
        this.pkgUrl = pkgUrl;
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }

    public String getIosUr() {
        return iosUr;
    }

    public void setIosUr(String iosUr) {
        this.iosUr = iosUr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
}
