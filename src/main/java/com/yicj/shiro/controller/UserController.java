package com.yicj.shiro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yicj.shiro.common.ShiroUtil;
import com.yicj.shiro.entity.ActiveUser;
import com.yicj.shiro.entity.ResultSupportApp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class) ;
	@PostMapping("/login")
	public String login(String username, String password,
						HttpServletRequest request) throws Exception {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = 
					new UsernamePasswordToken(username,password) ;
			currentUser.login(token);
			request.getSession().setAttribute("username", username);
			//认证成功跳转到成功页面
			return "/index" ;
		} catch (Exception e) {
			request.setAttribute("loginError", "用户名或密码错误，请重新登录!");
			return "/login" ;
		}
	}


	@GetMapping("/userLogin")
	@ResponseBody
	public ResultSupportApp superAppLogin(String username, String password,HttpServletRequest request){

		String error = "未知错误异常" ;
		if(username == null || password == null){
			return ResultSupportApp.getFailureInstance("参数为空",300) ;
		}
		try{
			Subject currentUser = SecurityUtils.getSubject() ;
			UsernamePasswordToken token = new UsernamePasswordToken(
					username,password,false,request.getRemoteAddr()) ;
			currentUser.login(token);

			Subject subject = SecurityUtils.getSubject() ;
			String sessionId = (String) subject.getSession().getId() ;

			ActiveUser activeUser = ShiroUtil.getActiveUser() ;
			return ResultSupportApp.getSuccess(activeUser,"登陆成功") ;

		}catch (Exception e){
			//根据与异常信息跑出对应的异常
			if (e.getClass().getName()!=null){
				if(UnknownAccountException.class.getName().equals(e.getClass().getName())){
					//抛出账号不存在异常
					error="账号不存在";
				}else    if(IncorrectCredentialsException.class.getName().equals(e.getClass().getName())){
					//
					//throw new CustomException("密码错误");
					error = "用户名密码错误";
				}else{
					//密码错误
					//throw new CustomException("未知错误异常");
					error = "未知错误异常";
				}
			}
			return ResultSupportApp.getFailureInstance(error,499) ;
		}



	}


}
