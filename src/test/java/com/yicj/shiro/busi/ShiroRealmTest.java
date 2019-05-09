package com.yicj.shiro.busi;

import org.junit.Assert;
import org.junit.Test;

import com.yicj.shiro.common.ShiroHelper;

public class ShiroRealmTest {

	@Test
	public void testMyRealm1() {
		ShiroHelper helper = new ShiroHelper("classpath:shiro-realm.ini") ;
        helper.login("yicj","456");
        Assert.assertEquals(true, helper.checkLogined());
        helper.logout();
	}
	
}
