package com.example.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class GatewayRouting{

	@Bean
	public RouteLocator configureRoute(RouteLocatorBuilder builder){
		return builder.routes()
//		.route("hospital_route", r -> r.path("/hospitals-feign/**").uri("http://localhost:9091/hospitals-feign"))
//		.route("doctor_route", r -> r.path("/doctor/**").uri("lb://DOCTOR-FIND-ALL/doctor"))
//		.route("doctor_route", r -> r.path("/doctor/**").filters(f->f.setRequestHeader("sort", "Medicine")).uri("lb://DOCTOR-FIND-ALL/doctor"))
//		.route("doctor_route", r -> r.path("/doctor/**").filters(f->f.addRequestParameter("dept", "Heart")).uri("lb://DOCTOR-FIND-ALL/doctor"))
		.route("doctor_route", r -> r.path("/doctor/**")
				.filters(f -> f.modifyResponseBody(String.class, String.class, (exchange, body) -> {
			        return Mono.just(body.replace("Spec1", "Ortho"));
			    }))
				.uri("lb://DOCTOR-FIND-ALL/doctor"))
        // Route for doctor delete
        .route("doctor-delete", r -> r.path("/doctor-delete/**")
            .filters(f -> f.rewritePath("/doctor-delete/(?<segment>.*)", "/doctor/$\\{segment}"))
            .uri("http://localhost:8085/doctor"))
        // Route for adding a doctor
        .route("doctor-add", r -> r.path("/doctor-add")
            .filters(f -> f.rewritePath("/doctor-add", "/doctors")) // Rewrite the path correctly
            .uri("http://localhost:8081/doctors"))
        // Route for updating a doctor
        .route("doctor-update", r -> r.path("/doctor-update/**")
            // Rewrite the path from /doctor-update/{id} to /doctor/{id} for the PUT request
            .filters(f -> f.rewritePath("/doctor-update/(?<segment>.*)", "/doctor/$\\{segment}"))
            .uri("http://localhost:8086/doctor"))
        .build();
	}
}