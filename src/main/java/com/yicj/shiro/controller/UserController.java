package com.yicj.shiro.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class) ;
	@PostMapping("/login")
	public String login(String username, String password,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = 
					new UsernamePasswordToken(username,password) ;
			currentUser.login(token);
			request.getSession().setAttribute("username", username);
			//认证成功跳转到成功页面
			return "index" ;
		} catch (Exception e) {
			logger.error("认证失败", e);
			response.sendRedirect(request.getContextPath() +"/login.jsp");
			return null ;
		}
	}

}
