package com.yicj.shiro.busi;

import org.junit.Test;

import com.yicj.shiro.common.ShiroHelper;

public class ShiroRealmTest {

	@Test
	public void testMyRealm1() {
		ShiroHelper helper = new ShiroHelper("classpath:shiro-realm.ini") ;
        helper.checkLogin("yicj","456");
        helper.logout();
	}
	
}
