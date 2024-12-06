package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.MyConfiguration;

@RestController
public class MyController {

	@Autowired
	MyConfiguration c;

	@GetMapping("/service2")
	public String getData() {
		return "From ms-2";
	}
}