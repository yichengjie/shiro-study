package com.yicj.shiro.hello;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager; 

public class Demo01 {
	
	public static void main(String[] args) {
		
		Realm realm = null ;
		SecurityManager securityManager = new DefaultSecurityManager(realm) ;
		SecurityUtils.setSecurityManager(securityManager);
		
		
		
	}

}
