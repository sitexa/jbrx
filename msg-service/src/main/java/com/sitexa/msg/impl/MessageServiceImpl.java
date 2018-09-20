package com.sitexa.msg.impl;

import com.gavin.business.DBTrans;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.sitexa.common.bean.Ret;
import com.sitexa.msg.api.SmsUtils;
import com.sitexa.msg.interfaces.MessageService;
import com.sitexa.msg.vo.MessageRecord;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class MessageServiceImpl implements MessageService {

    @Override
    public Ret sendCode(MessageRecord messageRecord) {
        //1.新增短信记录
        Request request = Request.build("MessageServiceImpl", "insertMessageRecord").currentTime().from(messageRecord);
        Response response = DBTrans.execute(request);
        if (response.fail()) {
            return Ret.result(response.result, response.message);
        }
        Ret ret = SmsUtils.sendCode(messageRecord.getReceiver(), messageRecord.getServiceType());
        if (ret.success()) {//2.更新三方短信结果
            Request updateRequest = Request.build("MessageServiceImpl", "updateMessageRecord").currentTime()
                    .set("thirdSn", ret.getData() + "")
                    .set("messageSn", messageRecord.getMessageSn());
            DBTrans.execute(updateRequest);
        }
        return ret;
    }

    @Override
    public Ret verifyCode(String messageId, String verifyCode) {
        Ret ret = SmsUtils.virifyCode(messageId, verifyCode);
        return ret;
    }

}
