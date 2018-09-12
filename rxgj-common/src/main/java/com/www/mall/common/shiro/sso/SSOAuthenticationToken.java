package com.www.mall.common.shiro.sso;

import org.apache.shiro.authc.AuthenticationToken;

import com.www.mall.common.shiro.principal.User;

public class SSOAuthenticationToken implements AuthenticationToken{

	private static final long serialVersionUID = -8252775566358643274L;

	/** 用户id */
    private String userId;

    /** token */
    private String token;
    
    
    private User user;
    
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
