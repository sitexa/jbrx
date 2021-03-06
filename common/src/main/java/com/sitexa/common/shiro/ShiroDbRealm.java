package com.sitexa.common.shiro;

import com.sitexa.common.shiro.auth.MultiAuthenticator;
import com.sitexa.common.shiro.cache.ShiroCacheUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * ShiroDbRealm
 * @author Rlax
 *
 */
public class ShiroDbRealm extends AuthorizingRealm {

	/**
	 * 认证器
	 */
	private MultiAuthenticator multiAuthenticator;

	@Override
	public void setCacheManager(CacheManager cacheManager) {
		super.setCacheManager(cacheManager);
		ShiroCacheUtils.setCacheManager(cacheManager);
	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		if (!multiAuthenticator.hasToken(authcToken)) {
			/** 无认证信息 */
			throw new UnknownAccountException();
		}

		if (multiAuthenticator.wasLocked(authcToken)) {
			/** 认证被锁定 */
			throw new LockedAccountException();
		}

		return multiAuthenticator.buildAuthenticationInfo(authcToken);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return multiAuthenticator.buildAuthorizationInfo(principals);
	}


	public MultiAuthenticator getMultiAuthenticator() {
		return multiAuthenticator;
	}

	public void setMultiAuthenticator(MultiAuthenticator multiAuthenticator) {
		this.multiAuthenticator = multiAuthenticator;
	}
}
