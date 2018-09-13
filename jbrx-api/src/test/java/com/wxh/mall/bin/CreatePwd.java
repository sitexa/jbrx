package com.wxh.mall.bin;

import com.www.mall.common.utils.MD5;
import org.apache.shiro.crypto.hash.Md5Hash;

import com.xiaoleilu.hutool.util.RandomUtil;



public class CreatePwd {

    public static void main(String[] args) {
    	String salt=RandomUtil.simpleUUID();
    	String password="123456";
    	System.out.println(password);
		System.out.println(new Md5Hash(password,salt,3).toString()+" salt="+salt);
    	System.out.println(new Md5Hash(MD5.getMD5(password),salt,3).toString()+" salt="+salt);
	}
	
}
