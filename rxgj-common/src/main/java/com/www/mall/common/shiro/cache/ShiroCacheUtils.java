package com.www.mall.common.shiro.cache;

import java.io.Serializable;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * ShiroCache工具类
 * @author Rlax
 *
 */
public class ShiroCacheUtils
{
	private static CacheManager cacheManager;

	/**
	 * 清除用户的授权信息
	 * 
	 * @param username
	 */
	public static void clearAuthorizationInfo(String username)
	{
		Cache<Object, Object> cache = cacheManager.getCache(CacheKey.CACHE_SHIRO_AUTH);
		cache.remove(username);
	}

	public static void clearAuthorizationInfoAll()
	{
		Cache<Object, Object> cache = cacheManager.getCache(CacheKey.CACHE_SHIRO_AUTH);
		cache.clear();
	}

	/**
	 * 清除session(认证信息)
	 * 
	 * @param JSESSIONID
	 */
	public static void clearSession(Serializable JSESSIONID)
	{
		Cache<Object, Object> cache = cacheManager.getCache(CacheKey.CACHE_SHIRO_ACTIVESESSION);
		cache.remove(JSESSIONID);
	}

	public static CacheManager getCacheManager()
	{
		return cacheManager;
	}

	public static void setCacheManager(CacheManager cacheManager)
	{
		ShiroCacheUtils.cacheManager = cacheManager;
	}

}