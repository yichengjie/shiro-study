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
        boolean isPermitted1 = currentUser.isPermitted("user:create") ;
        System.out.println("单个权限判断[user:create]:" + isPermitted1);
        boolean isPermitted2 = currentUser.isPermitted("user:create:1");
        System.out.println("单个权限判断[user:create:1]:" + isPermitted2);
        boolean isPermitted3 = currentUser.isPermittedAll("user:create:1",
                "user:create");
        System.out.println("多个权限判断[user:create,user:create:1]:" + isPermitted3);
        // 使用check方法进行授权，如果授权不通过会抛出异常
        currentUser.checkPermission("items:add:1");
        helper.logout();
    }
}
