package com.www.mall.common.utils;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ------------------------------
 * 金额换算：最小支持到分
 * ------------------------------
 * @author wdm  @date 2018年1月2日
 * @version 1.0
 */
public class MoneyUtil {

	private static Logger logger=LoggerFactory.getLogger(MoneyUtil.class);
	
	/**
	 * 分转元
	 * 金额换算 ---> long 型金额转换成 String<br/>
	 * @param money:long
	 * @return money:string
	 */
	public static String getAmount(long money) {
		BigDecimal a = new BigDecimal(money);
		BigDecimal b = new BigDecimal("100");
		return String.valueOf(a.divide(b).setScale(2));
	}

	/**
	 * 元转分
	 * 最小只能支持到分，比分还小则返回0
	 * 金额换算--->String 金额转换成 long 型<br/>
	 * @param money:string
	 * @return money:long
	 */
	public static long getAmount(String money) {
		if (money == null) {
			logger.error("invoke getAmount error strAmount is null");
			return 0;
		}
		//超过两位小数则截取
		int dotIndex=money.indexOf(".")+1;
		if(money.length()>dotIndex+4){
			money=money.substring(0, dotIndex+4);
		}
		try {
			BigDecimal bigAmount = new BigDecimal(money);
			BigDecimal bigFactor = new BigDecimal("100");
			BigDecimal bigResult = bigAmount.multiply(bigFactor).setScale(2);
			return bigResult.longValue();
		} catch (NumberFormatException e) {
			logger.error("invoke getAmount error strAmount is invalidate strAmount=" + money);
			return 0;
		}
	}
	
	/**
	 * 分转元
	 * 金额换算 ---> long 型金额转换成 String<br/>
	 * @param money:long
	 * @return money:string
	 */
	public static long getAmount(long money,String rate) {
		BigDecimal a = new BigDecimal(money);
		BigDecimal b = new BigDecimal(rate);
		return a.multiply(b).longValue();
	}
	
	/**
	 * 根据折扣获取折后价
	 * 金额：分
	 * @param money
	 * @param rebate
	 * @return
	 */
	public static long getAmountByRebate(long money,long rebate){
		try {
			return money*rebate/100;
		} catch (Exception e) {
			logger.error("invoke getAmountByRebate error rebate is invalidate money=" + money);
			return 0L;
		}
	}
	
//	public static void main(String[] args) {
//		System.out.println(MoneyUtil.getAmount(1000));
//		System.out.println(MoneyUtil.getAmount(100));
//		System.out.println(MoneyUtil.getAmount(10));
//		System.out.println(MoneyUtil.getAmount(1));
//		System.out.println(MoneyUtil.getAmount("1"));
//		System.out.println(MoneyUtil.getAmount("10.00"));
//		System.out.println(MoneyUtil.getAmount("10.01"));
//		System.out.println(MoneyUtil.getAmount("10.001"));
//		System.out.println(MoneyUtil.getAmount("10.10"));
//		System.out.println(MoneyUtil.getAmount("10.1"));
//		System.out.println(MoneyUtil.getAmount("10."));
//		System.out.println(MoneyUtil.getAmount("0.01"));
//		
//		System.out.println(getAmount(100,"5"));
//		System.out.println(getAmountByRebate(10,1));
//		System.out.println(100*1/100);
//		1000*10/100
//		1毛 10*5
//		
//	}
}
