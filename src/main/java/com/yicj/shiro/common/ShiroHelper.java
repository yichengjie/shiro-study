package com.yicj.shiro.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroHelper {

    private Logger logger = LoggerFactory.getLogger(ShiroHelper.class) ;
    private String iniPath ;
    private Subject currentUser ;

    public ShiroHelper(String iniPath){
        this.iniPath = iniPath ;
        this.init();
    }

    public  void init(){
        //取得SecurityManager工厂
        Factory<SecurityManager> factory=new IniSecurityManagerFactory(this.iniPath);
        //取得SecurityManager实例
        SecurityManager securityManager=factory.getInstance();
        //将securityManager绑定到SecurityUtil
        SecurityUtils.setSecurityManager(securityManager);
        /*至此为止，简单的从mysql数据库读取realm信息的shiro环境就配置好了    */
        //取得当前用户
        this.currentUser = SecurityUtils.getSubject();
    }

    public  void checkLogin(String username,String password){
        //使用shiro来进行登陆验证
        if(!this.currentUser.isAuthenticated()) {
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            try {
                this.currentUser.login(token);
                logger.info("登陆成功！！！");
            } catch (Exception e) {
                logger.error("认证失败...");
                throw new RuntimeException(e) ;
            }
        }
    }


    public void logout(){
        this.getCurrentUser().logout();
    }

    public Subject getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Subject currentUser) {
        this.currentUser = currentUser;
    }
}
