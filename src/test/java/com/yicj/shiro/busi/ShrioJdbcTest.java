package com.yicj.shiro.busi;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.yicj.shiro.common.ShiroHelper;

public class ShrioJdbcTest {

    @Test
    public void testJdbc1(){
        ShiroHelper helper = new ShiroHelper("classpath:shiro_jdbc.ini") ;
        helper.login("yicj","123");
        Assert.assertEquals(true, helper.checkLogined());
        helper.logout();
    }

    @Test
    public void testJdbc2(){
        ShiroHelper helper = new ShiroHelper("classpath:shiro_jdbc_sql.ini") ;
        helper.login("yicj","123");
        Assert.assertEquals(true, helper.checkLogined());
        helper.logout();
    }

    @Test
    public void testJdbc3(){
        //https://www.cnblogs.com/zerotomax/p/7420100.html
        ShiroHelper helper = new ShiroHelper("classpath:shiro_jdbc_my_sql.ini") ;
        helper.login("yicj","123");
        Assert.assertEquals(true, helper.checkLogined());
        Subject currentUser = helper.getCurrentUser();
        //检查是否有关用户新建权限
        if(currentUser.isPermitted("user:create")) {
        	System.out.println("用户有user:create权限");
        }else {
        	System.out.println("用户无user:create权限");
        }
        helper.logout();
    }
}
