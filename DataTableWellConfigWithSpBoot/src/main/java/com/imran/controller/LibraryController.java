package com.imran.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library")
public class LibraryController {
	
	
	@RequestMapping("/")
	public String Home() {
//		return "beanValidation/request";
		return "library/index";
	}

}
