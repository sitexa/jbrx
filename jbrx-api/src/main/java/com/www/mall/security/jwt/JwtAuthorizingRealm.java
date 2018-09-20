package com.www.mall.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.www.mall.common.bean.Menu;
import com.www.mall.common.shiro.cache.CacheKey;
import com.www.mall.common.shiro.cache.ShiroCacheUtils;
import com.www.mall.common.shiro.sso.SSOAuthenticationToken;

import io.jboot.Jboot;

public class JwtAuthorizingRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof SSOAuthenticationToken;
    }

	@Override
	public void setCacheManager(CacheManager cacheManager) {
		super.setCacheManager(cacheManager);
		ShiroCacheUtils.setCacheManager(cacheManager);
	}
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	SSOAuthenticationToken jwtToken = (SSOAuthenticationToken) token;
        String uid = (String) jwtToken.getPrincipal();

        Object uidCache = ShiroCacheUtils.getCacheManager().getCache(CacheKey.CACHE_JWT_TOKEN).get(uid);
        if (uidCache!=null) {
            /** 说明改 token 已被加入黑名单 */
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(uid, jwtToken.getCredentials(), this.getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取当前登录的用户名
		Object subject = principals.getPrimaryPrincipal();//super.getAvailablePrincipal(principals);
		Object uidCache = ShiroCacheUtils.getCacheManager().getCache(CacheKey.CACHE_VISIT).get(subject+"");
		try {
			if(uidCache==null) {
				//根据用户名称，获取该用户所有的权限列表
//				List<Menu> menus = Jboot.impl(MenuService.class).queryMenuByUser(Long.valueOf(subject+""));
				List<String> authorities=new ArrayList<String>();
//				for (int i = 0; i < menus.size(); i++) {
//					Menu menu=menus.get(i);
//					String visitCode=menu.getVisitCode();
//					if(StringUtils.isNotBlank(visitCode)){
//						authorities.add(visitCode);
//					}
//				}
				
//				List<Role> roles = Jboot.impl(RoleService.class).queryRoleByUser(admins);
//				List<String> rolelist=new ArrayList<String>();
//				for (int i = 0; i < roles.size(); i++) {
//					Role role=roles.get(i);
//					rolelist.add(role.getId()+"");
//				}
				info.addStringPermissions(authorities);
				ShiroCacheUtils.getCacheManager().getCache(CacheKey.CACHE_VISIT).put(subject,authorities);
			}
		} catch(RuntimeException e) {
			throw new AuthorizationException("用户【" + subject + "】授权失败");
		}
		return info;
    }
}
