package com.www.mall.service;

import org.junit.Test;

import com.gavin.model.Response;
import com.www.mall.common.bean.Ret;
import com.www.mall.pay.dto.paymant.Payment;
import com.www.mall.pay.enums.NotifyType;
import com.www.mall.pay.enums.ServiceType;
import com.www.mall.pay.enums.ThirdType;
import com.www.mall.service.pay.PaymentService;

public class TestPaymentService extends BaseTest{

	@Test
	public void payment(){
		Payment trade=new Payment();
		trade.setAttach("回调会原封不动的返回到业务系统");
		trade.setOrderSn("1528873155730");//订单唯一编号System.currentTimeMillis()+
		trade.setCallback(NotifyType.notify_payment_offline_supermarket.name());//回调标示
		trade.setInUserId(100L);//商家的用户id
		trade.setInStoreId(100L);//商家的店铺id
		trade.setOutUserId(101L);//付款用户id
		trade.setIp("127.0.0.1");
		trade.setMoney(100L);
		trade.setScore(100L);
		trade.setServiceType(ServiceType.payment_offline_supermarket.value());//业务类型
		trade.setSubject("利优智能超市购物订单");
		trade.setTradeDesc("XXXXX商品信息");
		trade.setThirdType(ThirdType.alipay);
		trade.setGiveScore(1L);
		System.out.println(trade);
		
		PaymentService pay=new PaymentService();
		Response result =pay.payment(trade);
		System.out.println(Ret.result(result.getResult(), result.getMessage(),result.getData()).toString());
	}
	
}
