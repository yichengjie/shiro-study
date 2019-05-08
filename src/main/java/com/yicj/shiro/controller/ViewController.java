package com.yicj.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping("/admin")
	public String admin() {
		return "admin" ;
	}
	@GetMapping("/index")
	public String index() {
		return "index" ;
	}
}
