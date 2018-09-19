package com.sitexa.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共类
 */
public class JSONUtils {

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean notNull(String str) {
		if (str == null || ("").equals(str))
			return false;
		else
			return true;
	}

	/**
	 * 判断字符串为空
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean isNull(String str) {
		if (str == null || ("").equals(str))
			return true;
		else
			return false;
	}

	/**
	 * 空字符转换
	 */
	public static String toNotNullString(Object object) {
		String rn = "";
		if (object != null) {
			rn = object.toString();
		}
		return rn;
	}

	/**
	 * 对象转JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String beanToJson(Object object) {
		String jsonStr = JSON.toJSONString(object);
		return jsonStr;
	}

	/**
	 * JSON字符串转对象
	 * 
	 * @param json
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public static Object jsonToBean(String json, Class<?> c)  {//throws Exception
		return JSON.parseObject(json, c);
	}

	/**
	 * 
	 * @param <T>
	 * @Title: jsonToList @Description: Json转List @param: @param
	 * jsonStr @param: @param elementClasses @param: @return @return: List @throws
	 */
	public static <T> List<T> jsonToList(String jsonStr, Class<?> elementClasses) {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, elementClasses);
		try {
			return mapper.readValue(jsonStr, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}