package com.sitexa.common.shiro.principal;

import java.io.Serializable;
import java.util.Date;

public class UserPrincipal implements Serializable {
    private static final long serialVersionUID = -758439056156155606L;
    private Long id;
    private String pic;//用户头像
    private String nickName;//昵称
    private String realName;//真实姓名
    private Integer sex;//性别 1男 2女 0未未知
    private String mobilePhone;//手机号
    private String password;//密码
    private String salt;//盐
    private Integer failCount;//失败次数
    private Date lockTime;//锁定时间
    private Integer status;//用户状态：1可用  2已禁用
    private String createTime;//创建时间
    private String updateTime;//修改时间

    public UserPrincipal() {
    }

    public UserPrincipal(Long id, String pic, String nickName, String realName, Integer sex, String mobilePhone, String password, String salt, Integer failCount, Date lockTime, Integer status, String createTime, String updateTime, String idcard, String idcardImg, String idcardImgBack, String bank, String bankch, String bankNum, String bankImg, String bankImgBack, String bankcardOwner, String mobile, String country, int cardType, String addr, String email, String birthday, String pro, String city, String area, int idcardIsA, int bankIsA) {
        this.id = id;
        this.pic = pic;
        this.nickName = nickName;
        this.realName = realName;
        this.sex = sex;
        this.mobilePhone = mobilePhone;
        this.password = password;
        this.salt = salt;
        this.failCount = failCount;
        this.lockTime = lockTime;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
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

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "id=" + id +
                ", pic='" + pic + '\'' +
                ", nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                ", sex=" + sex +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", failCount=" + failCount +
                ", lockTime=" + lockTime +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
