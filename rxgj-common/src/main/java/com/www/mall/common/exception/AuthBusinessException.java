package com.www.mall.common.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 认证业务异常
 */
public class AuthBusinessException extends AuthenticationException {

	private static final long serialVersionUID = 8542988372215757854L;

	public AuthBusinessException() {
        super();
    }

    public AuthBusinessException(String message) {
        super(message);
    }
    
    public AuthBusinessException(Throwable cause) {
        super(cause);
    }

    public AuthBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
