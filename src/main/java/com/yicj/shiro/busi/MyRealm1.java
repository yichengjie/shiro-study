package com.yicj.shiro.busi;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

    public String getName() {
        return "MyRealm1";
    }

    public boolean supports(AuthenticationToken token) {
        // 仅支持UsernamePasswordToken 类型的Token
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) 
    		throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        char[] credentials = (char[])token.getCredentials();
        String password = new String((char[])credentials); //得到密码
        if (!"yicj".equals(userName)) {
            throw new UnknownAccountException(); // 如果用户名错误
        }
        if (!"456".equals(password)) {
            throw new IncorrectCredentialsException(); // 如果密码错误
        }
        // 如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(userName, password, getName());
    }
}

