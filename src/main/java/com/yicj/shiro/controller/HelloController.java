package com.yicj.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	 @RequestMapping("/success")    
	 public String hello() {
	     System.out.println("hello world");        
	     return "success";
	 }
}
