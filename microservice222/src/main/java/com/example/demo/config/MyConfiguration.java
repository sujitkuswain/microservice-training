package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix="microservice222")
public class MyConfiguration {

	private String name;
	private int phoneno;
//	@Value("${custom.commonproperty}")
	private String commonVar;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(int phoneno) {
		this.phoneno = phoneno;
	}
	public String getCommonVar() {
		return commonVar;
	}
	public void setCommonVar(String commonVar) {
		this.commonVar = commonVar;
	}
	
	
}
