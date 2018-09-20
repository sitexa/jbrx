package com.sitexa.msg.interfaces;

import com.sitexa.common.bean.Ret;
import com.sitexa.msg.vo.MessageRecord;

public interface MessageService {

    public final static String service=MessageService.class.getSimpleName();

    public Ret sendCode(MessageRecord messageRecord);

    public Ret verifyCode(String messageId, String code);

}
