package com.sitexa.common.shiro.sso;

import com.www.mall.common.shiro.principal.UserPrincipal;
import org.apache.shiro.authc.AuthenticationToken;

public class SSOAuthenticationToken implements AuthenticationToken{

	private static final long serialVersionUID = -8252775566358643274L;

	/** 用户id */
    private String userId;

    /** token */
    private String token;
    
    
    private UserPrincipal user;
    
//    private String userName;
//    private String password;
    private AuthType type;
   
    public SSOAuthenticationToken(){}

	public SSOAuthenticationToken(AuthType type,String userId, String token) {
        this.userId = userId;
        this.token = token;
//        this.userName = userId;
//        this.password=token;
        this.type=type;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public AuthType getType() {
		return type;
	}

	public void setType(AuthType type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public String getToken() {
		return token;
	}

	public UserPrincipal getUser() {
		return user;
	}

	public void setUser(UserPrincipal user) {
		this.user = user;
	}
	
	
}
