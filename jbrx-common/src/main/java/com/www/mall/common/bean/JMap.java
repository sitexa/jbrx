package com.www.mall.common.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * ------------------------------
 * map对象操作
 * ------------------------------
 * @author wdm  @date 2017年12月20日
 * @version 1.0
 */
public class JMap {
	
	private Map<String,Object> map=new HashMap<String,Object>();
	
	private JMap(){}
	
	public static JMap build(){
		JMap map=new JMap();
		return map;
	}
	
	public static JMap build(String key,Object value){
		JMap map=new JMap();
		map.put(key, value);
		return map;
	}
	
	public JMap put(String key,Object value){
		map.put(key, value);
		return this;
	}
	
	public Object get(String key){
		return map.get(key);
	}
	
	public JMap putMap(Map<String,Object> map){
		this.map.putAll(map);
		return this;
	}
	
	public Map<String,Object> get(){
		return map;
	}
	
}
