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
    private String idcard; //证件号码
    private String idcardImg; //证件照片
    private String idcardImgBack; //证件照片背面
    private String bank;  //开户银行
    private String bankch;  //支行信息
    private String bankNum; //银行卡号
    private String bankImg;  //银行卡照片
    private String bankImgBack;  //银行卡照片背面
    private String bankcardOwner; //持卡人姓名
    private String mobile; //绑定手机号
    private String country; //国籍
    private int cardType; //证件类型  0无 1身份证 2护照 3其他
    private String addr;  //详细地址
    private String email; //邮箱
    private String birthday; //生日
    private String pro; //省
    private String city; //市
    private String area; //区
    private int idcardIsA; //身份证是否认证  1认证  2未认证
    private int bankIsA;  //银行卡是否认证  1认证  2未认证

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
        this.idcard = idcard;
        this.idcardImg = idcardImg;
        this.idcardImgBack = idcardImgBack;
        this.bank = bank;
        this.bankch = bankch;
        this.bankNum = bankNum;
        this.bankImg = bankImg;
        this.bankImgBack = bankImgBack;
        this.bankcardOwner = bankcardOwner;
        this.mobile = mobile;
        this.country = country;
        this.cardType = cardType;
        this.addr = addr;
        this.email = email;
        this.birthday = birthday;
        this.pro = pro;
        this.city = city;
        this.area = area;
        this.idcardIsA = idcardIsA;
        this.bankIsA = bankIsA;
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcardImg() {
        return idcardImg;
    }

    public void setIdcardImg(String idcardImg) {
        this.idcardImg = idcardImg;
    }

    public String getIdcardImgBack() {
        return idcardImgBack;
    }

    public void setIdcardImgBack(String idcardImgBack) {
        this.idcardImgBack = idcardImgBack;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankch() {
        return bankch;
    }

    public void setBankch(String bankch) {
        this.bankch = bankch;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getBankImg() {
        return bankImg;
    }

    public void setBankImg(String bankImg) {
        this.bankImg = bankImg;
    }

    public String getBankImgBack() {
        return bankImgBack;
    }

    public void setBankImgBack(String bankImgBack) {
        this.bankImgBack = bankImgBack;
    }

    public String getBankcardOwner() {
        return bankcardOwner;
    }

    public void setBankcardOwner(String bankcardOwner) {
        this.bankcardOwner = bankcardOwner;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getIdcardIsA() {
        return idcardIsA;
    }

    public void setIdcardIsA(int idcardIsA) {
        this.idcardIsA = idcardIsA;
    }

    public int getBankIsA() {
        return bankIsA;
    }

    public void setBankIsA(int bankIsA) {
        this.bankIsA = bankIsA;
    }
}
