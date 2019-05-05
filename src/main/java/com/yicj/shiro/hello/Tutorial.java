package com.yicj.shiro.hello;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Tutorial {

    private static final transient Logger log = LoggerFactory.getLogger(Tutorial.class);

    public static void main(String[] args) {
        log.info("My First Apache Shiro Application");
        Tutorial tutorial = new Tutorial() ;
        tutorial.test001();
        tutorial.test002();
        tutorial.test003();
        tutorial.test004();
        System.exit(0);
    }


    public void test004(){
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isPermitted("lightsaber:weild")) {
            log.info("You may use a lightsaber ring. Use it wisely.");
        }else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        //test a typed permission (not instance-level)
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }
        currentUser.logout();
    }



    public void test003(){
        Subject currentUser = SecurityUtils.getSubject() ;
        Session session = currentUser.getSession();
        Object someKey = session.getAttribute("someKey");
        log.info("someKey : " + someKey);
        if (currentUser.hasRole("schwartz")){
            log.info("May the Schwartz be with you !");
        }else {
            log.info("Hello, mere mortal.");
        }
    }

    public void  test002(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("someKey","aValue");
        if (!currentUser.isAuthenticated()){
            String username = "lonestarr" ;
            String password = "vespa" ;
            UsernamePasswordToken token = new UsernamePasswordToken(username,password) ;
            token.setRememberMe(true);
            currentUser.login(token);
        }
    }

    public  void test001(){
        //1.
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "classpath:shiro.ini") ;
        //2.
        SecurityManager securityManager = factory.getInstance() ;
        //3.
        SecurityUtils.setSecurityManager(securityManager);
    }
}
