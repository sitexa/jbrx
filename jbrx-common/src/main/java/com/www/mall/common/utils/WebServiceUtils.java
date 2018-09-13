package com.www.mall.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.www.mall.common.utils.http.HttpManager;
import com.www.mall.common.utils.http.HttpRequest;
import com.www.mall.common.utils.http.HttpResponse;
import org.apache.log4j.Logger;
import com.www.mall.common.bean.Ret;
import java.util.HashMap;
import java.util.Map;

public class WebServiceUtils {
	private static Logger logger=Logger.getLogger(WebServiceUtils.class);

	/**
	 * 发送到目标服务-参数不能为空
	 * @param param
	 * @param serviceName
	 * @param transName
	 * @return
	 */
	public static Ret send(String url,String param,String[] notNullFieldNames,String serviceName,String transName){
		logger.info("请求参数："+url+"--->"+param);
		JSONObject json=WebServiceUtils.param(param, serviceName, transName,true);
		if(json==null){
			logger.warn("web."+serviceName+"."+transName+" login out error");
			return Ret.fail("参数错误或用户登录已过期");
		}
		for (int i = 0; i < notNullFieldNames.length; i++) {
			Object value=json.get(notNullFieldNames[i]);
			if(value==null){
				if("pageSize".equals(notNullFieldNames[i])){
					json.put("pageSize", 10);
					continue;
				}
				if("pageNum".equals(notNullFieldNames[i])){
					json.put("pageNum", 0);
					continue;
				}
				return Ret.fail("参数有误:("+notNullFieldNames[i]+")");
			}else{
				if("pageNum".equals(notNullFieldNames[i])){
					if(!"0".equals(value+"")){
						int pageNum=Integer.valueOf(value+"");
						json.put("pageNum", pageNum-1);
					}
					continue;
				}
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdoParam", json);
		HttpRequest request = HttpRequest.create(url , params, HttpRequest.METHOD_POST);
		HttpResponse response = HttpManager.me().getHttp().handle(request);
		String content=response.getContent();
		logger.info("web."+serviceName+"."+transName+" Ret:"+content);

		if(response.getResponseCode()!=200){
			return Ret.fail("请求异常:("+response.getResponseCode()+")");
		}
		Ret br=JSON.parseObject(content, Ret.class);
		if(br==null){
			return Ret.fail("请求异常");
		}
		return br;
	}

	/**
	 * 发送到目标服务-参数不能为空
	 * @param param
	 * @param serviceName
	 * @param transName
	 * @return
	 */
	public static Ret send(String url,String param,String[] notNullFieldNames,String serviceName,String transName,boolean needAuth){
		logger.info("请求参数："+url+"--->"+param);
		JSONObject json=WebServiceUtils.param(param, serviceName, transName,needAuth);
		if(json==null){
			logger.warn("web."+serviceName+"."+transName+" login out error");
			return Ret.fail("参数错误或用户登录已过期");
		}
		for (int i = 0; i < notNullFieldNames.length; i++) {
			Object value=json.get(notNullFieldNames[i]);
			if(value==null){
				if("pageSize".equals(notNullFieldNames[i])){
					json.put("pageSize", 10);
					continue;
				}
				if("pageNum".equals(notNullFieldNames[i])){
					json.put("pageNum", 0);
					continue;
				}
				return Ret.fail("参数有误:("+notNullFieldNames[i]+")");
			}else{
				if("pageNum".equals(notNullFieldNames[i])){
					if(!"0".equals(value+"")){
						int pageNum=Integer.valueOf(value+"");
						json.put("pageNum", pageNum-1);
					}
					continue;
				}
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdoParam", json);
		HttpRequest request = HttpRequest.create(url , params, HttpRequest.METHOD_POST);
		HttpResponse response = HttpManager.me().getHttp().handle(request);
		String content=response.getContent();
		logger.info("web."+serviceName+"."+transName+" Ret:"+content);

		if(response.getResponseCode()!=200){
			return Ret.fail("请求异常:("+response.getResponseCode()+")");
		}
		Ret br=JSON.parseObject(content, Ret.class);
		if(br==null){
			return Ret.fail("请求异常");
		}
		return br;
	}

	/**
	 * 发送到目标服务-参数可以为空
	 * @param param
	 * @param serviceName
	 * @param transName
	 * @return
	 */
	public static Ret send(String url,String param,String serviceName,String transName){
		logger.info("请求参数："+url+"--->"+param);
		JSONObject json=WebServiceUtils.param(param, serviceName, transName,true);
		if(json==null){
			logger.warn("web."+serviceName+"."+transName+" login out error");
			return Ret.fail("参数错误或用户登录已过期");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdoParam", json);
		HttpRequest request = HttpRequest.create(url , params, HttpRequest.METHOD_POST);
		HttpResponse response = HttpManager.me().getHttp().handle(request);
		String content=response.getContent();
		logger.info("web."+serviceName+"."+transName+" Ret:"+content);

		if(response.getResponseCode()!=200){
			return Ret.fail("请求异常:("+response.getResponseCode()+")");
		}
		Ret br=JSON.parseObject(content, Ret.class);
		if(br==null){
			return Ret.fail("请求异常");
		}
		return br;
	}

	/**
	 * 发送到目标服务-参数可以为空
	 * @param url
	 * @param serviceName
	 * @param transName
	 * @return
	 */
	public static Ret send(String url,String serviceName,String transName){
		JSONObject json=new JSONObject();
		json.put("strServiceName", serviceName);
		json.put("strTransName", transName);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdoParam", json);
		HttpRequest request = HttpRequest.create(url , params, HttpRequest.METHOD_POST);
		HttpResponse response = HttpManager.me().getHttp().handle(request);
		String content=response.getContent();
		logger.info("web."+serviceName+"."+transName+" Ret:"+content);

		if(response.getResponseCode()!=200){
			return Ret.fail("请求异常:("+response.getResponseCode()+")");
		}
		Ret br=JSON.parseObject(content, Ret.class);
		if(br==null){
			return Ret.fail("请求异常");
		}
		return br;
	}

	public static Ret send(String url,Map<String,String> param,String serviceName,String transName){
		logger.info("请求参数："+url+"--->"+param);
		param.put("strServiceName", serviceName);
		param.put("strTransName", transName);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdoParam", JSON.toJSON(param));

		HttpRequest request = HttpRequest.create(url , params, HttpRequest.METHOD_POST);
		HttpResponse response = HttpManager.me().getHttp().handle(request);
		String content=response.getContent();
		logger.info("manage."+serviceName+"."+transName+" Ret:"+content);

		if(response.getResponseCode()!=200){
			return Ret.fail("请求异常:("+response.getResponseCode()+")");
		}
		Ret br=JSON.parseObject(content, Ret.class);
		if(br==null){
			return Ret.fail("请求异常");
		}
		return br;
	}

	/**
	 * 发送到目标服务-参数可以为空
	 * @param url
	 * @param json
	 * @return
	 */
	public static Ret send(String url,JSONObject json){
		logger.info("请求参数："+url+"--->"+json);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cdoParam", json);

		HttpRequest request = HttpRequest.create(url , params, HttpRequest.METHOD_POST);
		HttpResponse response = HttpManager.me().getHttp().handle(request);
		String content=response.getContent();
		logger.info("manage."+json.get("strServiceName")+"."+json.get("strTransName")+" Ret:"+content);
		if(response.getResponseCode()!=200){
			return Ret.fail("请求异常:("+response.getResponseCode()+")");
		}
		Ret br=JSON.parseObject(content, Ret.class);
		if(br==null){
			return Ret.fail("请求异常");
		}
		return br;
	}

    /**
     * 接口调用-带参数的
     * @param url
     * @param params
     * @param method
     * @return
     */

    public static Ret sendHttp(String url, Map<String, Object> params,String method){
        logger.info("请求参数："+url+"--->"+params);
        HttpRequest request = HttpRequest.create(url , params, method);
        HttpResponse response = HttpManager.me().getHttp().handle(request);
        String content=response.getContent();
        logger.info("method:"+method+" Ret:"+content);
        if(response.getResponseCode()!=200){
            return Ret.fail("请求异常:("+response.getResponseCode()+")");
        }
        Ret br=JSON.parseObject(content, Ret.class);
        if(br==null){
            return Ret.fail("请求异常");
        }
        return br;
    }


	/**
	 * 接口调用-带参数的  带token
	 * @param url
	 * @param params
	 * @param method
	 * @param token
	 * @return
	 */

	public static String sendHttp(String url, Map<String, Object> params,String method,String token){
		logger.info("请求参数："+url+"--->"+params);
		HttpRequest request = HttpRequest.create(url , params, method);
		Map map=new HashMap();
		map.put("token",token);
		request.setHeaders(map);
		//request.addHeader("token",token);
		System.out.println("======================="+request.getHeaders().get("token"));
		HttpResponse response = HttpManager.me().getHttp().handle(request);
		String content=response.getContent();
		logger.info("method:"+method+" Ret:"+content);
		if(response.getResponseCode()!=200){
			return "请求异常:("+response.getResponseCode()+")";
		}
		return content;
	}

	/**
	 * 接口调用-带参数的
	 * @param url
	 * @param method
	 * @return
	 */

	public static Ret sendHttp(String url,String method){
		logger.info("请求参数："+url);
		HttpRequest request = HttpRequest.create(url, method);
		HttpResponse response = HttpManager.me().getHttp().handle(request);
		String content=response.getContent();
		logger.info("method:"+method+" Ret:"+content);
		if(response.getResponseCode()!=200){
			return Ret.fail("请求异常:("+response.getResponseCode()+")");
		}
		Ret br=JSON.parseObject(content, Ret.class);
		if(br==null){
			return Ret.fail("请求异常");
		}
		return br;
	}

	public static JSONObject param(String param,String serviceName,String transName,boolean needAuth){
//		Parameter parameter = Common.jsonToParam(param);
//		String userId="";//获取用户ID
//		if(needAuth){
//			try {
//				UserToken mtoken=parameter.getUserToken();
//				userId=mtoken.getUsers().getId();
//			} catch (Exception e) {
//				logger.error("参数错误或用户登录已过期");
//				return null;
//			}
//		}
//
//		JSONObject json=(JSONObject) JSON.parse(String.valueOf(parameter.getObj()));
//		if(json==null){
//			json=new JSONObject();
//		}
//		json.put("strServiceName", serviceName);
//		json.put("strTransName", transName);
//		json.put("userId", userId);
//
//		return json;
        return null;
	}

	public static boolean verifyParam(String[] notNullFieldNames,JSONObject json){
		for (int i = 0; i < notNullFieldNames.length; i++) {
			Object value=json.get(notNullFieldNames[i]);
			if(value==null){
				logger.info("参数有误:("+notNullFieldNames[i]+")");
				return false;
			}else{
				if("pageNum".equals(notNullFieldNames[i])){
					if(!"0".equals(value+"")){
						int pageNum=Integer.valueOf(value+"");
						json.put("pageNum", pageNum-1);
					}
					continue;
				}
			}
		}
		return true;
	}
}
