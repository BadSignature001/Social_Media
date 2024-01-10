package com.social.media.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/print")
	public String getMessage() {
		return "hello this is my first rest endpoint" ;
	}

}
