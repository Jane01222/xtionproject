package com.xuanwu.xtion.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {

	@RequestMapping("/name")
	public String name() {
		return "111";
	}
	
}
