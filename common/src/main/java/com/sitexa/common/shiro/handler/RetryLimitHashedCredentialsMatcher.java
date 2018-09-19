package com.sitexa.common.shiro.handler;

import com.www.mall.common.shiro.cache.CacheKey;
import com.www.mall.common.shiro.cache.ShiroCacheUtils;
import com.www.mall.common.shiro.principal.MultiLoginToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 密码重试认证
 * @author Rlax
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{//SimpleCredentialsMatcher {

	/** 允许密码重试最大次数 */
	private int allowRetryCount = 10;
	@Override
	public boolean doCredentialsMatch(AuthenticationToken _token, AuthenticationInfo info) {
		Cache<String, AtomicInteger> passwordRetryCache = ShiroCacheUtils.getCacheManager().getCache(CacheKey.CACHE_SHIRO_PASSWORDRETRY);
		MultiLoginToken token = (MultiLoginToken) _token;
		
		String username = (String) token.getPrincipal();

		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > allowRetryCount) {
			throw new ExcessiveAttemptsException();
		}

		boolean matches = false;
		
		if (token.getLoginType().equals(MultiLoginToken.USERPASSWORD_MODE)) {
			matches = super.doCredentialsMatch(token, info);
		} else if (token.getLoginType().equals(MultiLoginToken.TOKEN_MODE)) {
			SimpleCredentialsMatcher simpleMatcher = new SimpleCredentialsMatcher();
			matches = simpleMatcher.doCredentialsMatch(token, info);
		} else {
			throw new RuntimeException("not support login type :" + token.getLoginType());
		}
		
		if (matches) passwordRetryCache.remove(username);
		
		return matches;
	}

	public int getAllowRetryCount() {
		return allowRetryCount;
	}

	public void setAllowRetryCount(int allowRetryCount) {
		this.allowRetryCount = allowRetryCount;
	}
}
