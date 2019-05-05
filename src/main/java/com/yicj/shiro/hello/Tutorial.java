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
        System.exit(0);
    }


    public void test003(){
        Subject currentUser = SecurityUtils.getSubject() ;
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
