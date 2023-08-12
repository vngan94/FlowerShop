package com.tttn.flowershop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/blog")
public class BlogController {
	// Mapping jsp
		@RequestMapping("main")
		public String index() {
			return "blog/main";
		}
		
		@RequestMapping("detail")
		public String detail() {
			return "blog/detail";
		}
		
}
