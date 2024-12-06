package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.MyConfiguration;

@RestController
public class MyController {
//
//	@Autowired
//	MyConfiguration c;
	
	@Autowired
	RestTemplate restTemplate;

//	@GetMapping("/")
//	public String getData() {
//		return "Hello from:" + c.getName() + " number:" + c.getPhoneno() + " with common:" + c.getCommonVar();
//	}
	
	@GetMapping("/call-ms2")
	public String callMs2() {
		return restTemplate.getForObject("http://localhost:8081/service2", String.class);
	}
}