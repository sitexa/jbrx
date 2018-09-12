package com.www.mall.common.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: PageData
 * @Description:接口或页面参数对象PageData
 * @author: 湖南无为网电子商务有限公司 (Dieudonne)
 * @date: 2018年6月12日 上午10:41:13
 * 
 * @Copyright: 2018 http://www.0731333.com Inc. All rights reserved.
 *             注意：本内容仅限于湖南无为网电子商务有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class APIData extends HashMap implements Map {

	private static final long serialVersionUID = 1L;

	Map map = null;
	HttpServletRequest request;

	public APIData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}

	public APIData() {
		map = new HashMap();
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		return String.valueOf(get(key));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Integer getParamToInt(String field, int defaultValue) {
		if (BlankUtil.isBlank(getString(field))) {
			return defaultValue;
		} else {
			try {

				return Integer.valueOf(getString(field));

			} catch (Exception e) {
				return defaultValue;
			}

		}

	}

	public Long getParamToLong(String field, long defaultValue) {
		if (BlankUtil.isBlank(getString(field))) {
			return defaultValue;
		} else {
			try {
				return Long.valueOf(getString(field));
			} catch (Exception e) {
				return defaultValue;
			}

		}
	}

	public Integer getParamToInt(String field) {
		if (BlankUtil.isBlank(getString(field))) {
			return null;
		} else {
			try {
				return Integer.valueOf(getString(field));
			} catch (Exception e) {
				return null;
			}

		}

	}

	public Long getParamToLong(String field) {
		if (BlankUtil.isBlank(getString(field))) {
			return null;
		} else {
			try {
				return Long.valueOf(getString(field));
			} catch (Exception e) {
				return null;
			}
		}
	}

	public Double getParamToDouble(String field) {
		if (BlankUtil.isBlank(getString(field))) {
			return null;
		} else {
			try {
				return Double.valueOf(getString(field));
			} catch (Exception e) {
				return null;
			}

		}
	}

	public static void main(String[] args) {
		APIData data = new APIData();
		data.put("state", "121.12121");
		System.out.println(data.getParamToDouble("state"));
	}

}
