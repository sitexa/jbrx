package com.www.mall.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.www.mall.common.shiro.auth.MuitiAuthenticatied;
import com.www.mall.common.shiro.cache.ShiroCacheUtils;

/**
 * ShiroDbRealm
 * @author Rlax
 *
 */
public class ShiroDbRealm extends AuthorizingRealm {

	/**
	 * 认证器
	 */
	private MuitiAuthenticatied muitiAuthenticatied;

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
		if (!muitiAuthenticatied.hasToken(authcToken)) {
			/** 无认证信息 */
			throw new UnknownAccountException();
		}

		if (muitiAuthenticatied.wasLocked(authcToken)) {
			/** 认证被锁定 */
			throw new LockedAccountException();
		}

		return muitiAuthenticatied.buildAuthenticationInfo(authcToken);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return muitiAuthenticatied.buildAuthorizationInfo(principals);
	}


	public MuitiAuthenticatied getMuitiAuthenticatied() {
		return muitiAuthenticatied;
	}

	public void setMuitiAuthenticatied(MuitiAuthenticatied muitiAuthenticatied) {
		this.muitiAuthenticatied = muitiAuthenticatied;
	}
}
