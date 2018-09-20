package com.sitexa.msg.api;


import com.sitexa.common.bean.Ret;
import com.sitexa.msg.vo.TemplateType;

import java.util.Map;

public class SmsUtils {
    public static ISms sms = new JPushSms();

    public static Ret sendCode(String mobilePhone, String serviceType) {
        TemplateType templateType = TemplateType.getTemplateType(serviceType);
        if (templateType == null) {
            return Ret.fail("不支持的业务类型");
        }
        return sms.sendCode(mobilePhone, "", templateType.templateTypeWithJPush());
    }

    public static Ret virifyCode(String messageId, String verifyCode) {
        return sms.virifyCode(messageId, verifyCode);
    }

    public static Ret sendMessage(String mobilePhone, TemplateType template, Map<String, String> param) {
        return sms.sendMessage(mobilePhone, template.templateTypeWithJPush(), param);
    }
}
