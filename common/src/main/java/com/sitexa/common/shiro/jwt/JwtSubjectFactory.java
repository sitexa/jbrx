package com.sitexa.common.shiro.jwt;

import com.sitexa.common.shiro.sso.SSOAuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * jwt 不创建 session
 * @author Rlax
 *
 */
public class JwtSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        if (context.getAuthenticationToken() instanceof SSOAuthenticationToken) {
            // jwt 不创建 session
            context.setSessionCreationEnabled(false);
        }

        return super.createSubject(context);
    }
}
