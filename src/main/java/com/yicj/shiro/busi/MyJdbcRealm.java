package com.yicj.shiro.busi;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.yicj.shiro.dao.UserDao;
import com.yicj.shiro.entity.UserInfo;

public class MyJdbcRealm extends AuthorizingRealm {
    private UserDao dao = new UserDao() ;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	// 从 principals获取主身份信息
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo
    	//认证通过填充到SimpleAuthenticationInfo中身份类型），
    	String username = (String)principals.getPrimaryPrincipal() ;
    	//根据身份信息获取权限信息
    	//连接数据库..
    	//模拟从数据库获取到数据
    	List<String> permissions = new ArrayList<String>() ;
    	permissions.add("user:create") ;
    	permissions.add("item:add") ;
    	//查到权限数据，返回授权信息(要包括上边的permissions)
    	SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo() ;
    	simpleAuthorizationInfo.addStringPermissions(permissions);
    	return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String username = token.getPrincipal().toString() ;
        char [] credentials = (char[])token.getCredentials() ;
        System.out.println("password : " + new String(credentials));
        try {
            UserInfo user = dao.queryUserByName(username) ;
            AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),"salt");
            return info ;
        }catch (Exception e){
            return  null ;
        }
    }

}
