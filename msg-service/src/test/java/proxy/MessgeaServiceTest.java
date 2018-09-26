package proxy;

import com.sitexa.common.bean.Ret;
import com.sitexa.msg.impl.MessageServiceImpl;
import com.sitexa.msg.interfaces.MessageService;
import com.sitexa.msg.vo.MessageRecord;
import com.sitexa.msg.vo.TemplateType;
import com.xiaoleilu.hutool.util.RandomUtil;
import conf.BaseTest;
import org.junit.Test;

public class MessgeaServiceTest extends BaseTest {
    MessageService service = new MessageServiceImpl();

/*    @Test
    public void TestSendCode(){
        MessageRecord msg = new MessageRecord();

        msg.setServiceType(TemplateType.REGISTER.name());
        msg.setContent(TemplateType.REGISTER.name());
        msg.setTitle("register verify code");
        msg.setMessageType(1);
        msg.setReceiver("18673107430");
        msg.setMessageSn(RandomUtil.randomUUID());

        Ret ret = service.sendCode(msg);

        result(ret);
    }*/
}
