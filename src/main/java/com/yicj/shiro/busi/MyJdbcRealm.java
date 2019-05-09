package com.yicj.shiro.busi;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.yicj.shiro.dao.UserDao;
import com.yicj.shiro.entity.UserInfo;

public class MyJdbcRealm extends AuthorizingRealm {
    private UserDao dao = new UserDao() ;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String username = token.getPrincipal().toString() ;
        try {
            UserInfo user = dao.queryUserByName(username) ;
            AuthenticationInfo info =new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),"salt");
            return info ;
        }catch (Exception e){
            return  null ;
        }
    }

}
