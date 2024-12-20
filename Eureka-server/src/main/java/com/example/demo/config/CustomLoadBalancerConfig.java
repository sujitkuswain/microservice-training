package com.example.demo.config;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomLoadBalancerConfig {

	// based on previous execution preference
//	@Bean
//	public ServiceInstanceListSupplier provideSameInstance(ConfigurableApplicationContext context) {
//		return ServiceInstanceListSupplier.builder().withBlockingDiscoveryClient().withSameInstancePreference()
//				.build(context);
//	}
//
//	// Based on zone
//	@Bean
////	@LoadBalancerClient(name="doctor-find-all", configuration= CustomLoadBalancerConfig.class, value="doctor-find-all") use in rest template
//	public ServiceInstanceListSupplier zoneBasedInstance(ConfigurableApplicationContext context) {
//		return ServiceInstanceListSupplier.builder().withDiscoveryClient().withZonePreference().withCaching()
//				.build(context);
//	}
//
//	// Based on weight
//	@Bean
//	public ServiceInstanceListSupplier withWeightService(ConfigurableApplicationContext context) {
//		return ServiceInstanceListSupplier.builder().withDiscoveryClient().withWeighted().withCaching().build(context);
//	}
//
//	// Based on health check
//	@Bean
//	public ServiceInstanceListSupplier withHealthCheck(ConfigurableApplicationContext context) {
//		return ServiceInstanceListSupplier.builder().withDiscoveryClient().withHealthChecks().build(context);
//	}
}
