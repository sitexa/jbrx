package com.sitexa.common.utils;

import com.jfinal.template.Engine;
import com.jfinal.template.Template;

import java.util.Map;
import java.util.UUID;

/**
 * ------------------------------
 * 模板工具
 * ------------------------------
 */
public class TemplateUtil {
	
//	public static Map<String,String> cache=new HashMap<String, String>();
	/**
	 * 将Map转换为XML格式的字符串
	 * @param data
	 *            Map类型数据
	 * @return XML格式的字符串
	 * @throws Exception
	 */
	public static String render(Map<String, String> data,String templateName) {
//		String content=cache.get(templateName);
	    Template tp=Engine.use().getTemplate(templateName);
	    return tp.renderToString(data);
	}
	
	/**
	 * 获取随机字符串 Nonce Str
	 *
	 * @return String 随机字符串
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
	}
}