package com.sitexa.facade.vo;

import com.sitexa.common.validator.VGroup;
import com.sitexa.common.validator.VRule;
import com.sitexa.common.validator.VRules;
import com.sitexa.common.validator.VType;

import java.io.Serializable;

@VRules(rules = {
        @VRule(attrName = "mobilePhone", message = "手机号码有误", groups = {VGroup.save, VGroup.excutor}, types = {VType.mobilePhone}),
        @VRule(attrName = "password", message = "密码最少为6位字符", groups = {VGroup.save}, types = {VType.password}),
        @VRule(attrName = "verifyCode", message = "验证码有误", groups = {VGroup.save}, types = {VType.number}),
        @VRule(attrName = "messageId", message = "验证码标识有误", groups = {VGroup.save}, types = {VType.notnull})
})
public class Register implements Serializable {
    private static final long serialVersionUID = 7481029910263308438L;

    private String mobilePhone;
    private String password;
    private String verifyCode;
    private String messageId;//短信标识
    private String nickName;//

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}