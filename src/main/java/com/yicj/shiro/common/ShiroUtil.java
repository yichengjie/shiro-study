package com.yicj.shiro.common;

import com.yicj.shiro.entity.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShiroUtil {

    /**
     * 获取ActiveUser并保存至session中一份
     * @return
     */
    public static ActiveUser getActiveUser(){
        //从shiro的session中取出activeUser
        Subject subject = SecurityUtils.getSubject() ;
        //取出身份信息
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal() ;
        if(activeUser !=null){
            Session session = subject.getSession() ;
            ActiveUser user = (ActiveUser) session.getAttribute("user") ;
            if(user == null){
                session.setAttribute("user",activeUser);
            }
            return activeUser ;
        }
        return null ;
    }

    /**
     * 根据sessionid 获取用户信息
     * @param sessionId
     * @param request
     * @param response
     * @return
     */
    public static ActiveUser getActiveUser(String sessionId,
           HttpServletRequest request, HttpServletResponse response){
        boolean status = false ;
        SessionKey key = new WebSessionKey(sessionId,request,response) ;
        Session se = SecurityUtils.getSecurityManager().getSession(key) ;
        Object obj = se.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        SimplePrincipalCollection coll = (SimplePrincipalCollection) obj ;
        ActiveUser activeUser = (ActiveUser)coll.getPrimaryPrincipal() ;
        if(activeUser != null){
            ActiveUser user = (ActiveUser) se.getAttribute("user") ;
            if(user == null){
                se.setAttribute("user",activeUser);
            }
            return  activeUser ;
        }
        return  null ;
    }

}
