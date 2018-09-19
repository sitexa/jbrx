package com.sitexa.common.bean;

import com.gavin.model.Model;

import java.io.Serializable;

public class AdminVo extends Model implements Serializable {
    private static final long serialVersionUID = -758439056156155606L;

    public AdminVo() {
    }
    public Long getId() {
        return getLong("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public Long getPlateformId() {
        return getLong("plateformId");
    }

    public void setPlateformId(Long plateformId) {
        set("plateformId",plateformId );
    }

    public String getUserName() {
        return getString("userName");
    }

    public void setUserName(String userName) {
        set("userName",userName );
    }

    public String getAvatar() {
        return getString("avatar");
    }

    public void setAvatar(String avatar) {
        set("avatar",avatar );
    }

    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        set("password",password );
    }

    public String getSalt() {
        return getString("salt");
    }

    public void setSalt(String salt) {
        set("salt",salt );
    }

    public int getStatus() {
        return getInteger("status");
    }

    public void setStatus(int status) {
        set("status", status);
    }

    public String getLoginTime() {
        return getString("loginTime");
    }

    public void setLoginTime(String loginTime) {
        set("loginTime", loginTime);
    }

    public String getCreateTime() {
        return getString("createTime");
    }

    public void setCreateTime(String createTime) {
        set("createTime",createTime );
    }

    public String getUpdateTime() {
        return getString("updateTime");
    }

    public void setUpdateTime(String updateTime) {
        set("updateTime",updateTime );
    }
}
