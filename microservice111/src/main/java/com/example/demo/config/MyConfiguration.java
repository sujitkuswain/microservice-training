package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@ConfigurationProperties(prefix="microservice111")
public class MyConfiguration {
	
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

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
