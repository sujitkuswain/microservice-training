package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.feign.Hospital_Doctor_Feign;
import com.example.demo.pojo.Doctor;
import com.example.demo.pojo.Hospital;
import com.example.demo.repository.HospitalRepo;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class HospitalController {

	@Autowired
	HospitalRepo hospitalRepo;

	@Autowired
	Hospital_Doctor_Feign feignClient;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/hospitals/{hospitalId}")
	@CircuitBreaker(name = "circuitbreaker-for-doctor", fallbackMethod = "fallBackMethod")
	public ResponseEntity<Hospital> findAllDoctorInHospital(@PathVariable int hospitalId) {

		Hospital hospital = hospitalRepo.findHospitalById(hospitalId);

		if (hospital == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		ResponseEntity<Doctor[]> entity = restTemplate
				.getForEntity("http://doctor-find-all/doctor/doctors/{hospitalId}", Doctor[].class, hospitalId);

		if (entity.getBody() != null) {
			hospital.setDoctors(Arrays.asList(entity.getBody()));
		}

		return new ResponseEntity<>(hospital, HttpStatus.OK);
	}

	@GetMapping("/hospitals-feign/{hospitalId}")
//	@CircuitBreaker(name = "circuitbreaker-for-doctor", fallbackMethod = "fallBackMethod")
	public ResponseEntity<Hospital> findAllDoctorInHospital_Feign(@PathVariable int hospitalId) {

		Hospital hospital = hospitalRepo.findHospitalById(hospitalId);

		List<Doctor> doctors = new ArrayList<>();

		if (hospital != null) {
			ResponseEntity<Doctor[]> entity = feignClient.findAllDoctorsByHospitalId(hospitalId);
			hospital.setDoctors(Arrays.asList(entity.getBody()));
			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		}
		return new ResponseEntity<Hospital>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<Hospital> fallBackMethod(int hospitalId, Throwable e) {
		Hospital hospital = new Hospital();
		hospital.setHospitalName("fromFallBack");
		hospital.setHospitalAddress("fromFallBack");
		return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
	}

	@GetMapping("/hospitals-feign-retry/{hospitalId}")
	@Retry(name = "retry-for-doctor", fallbackMethod = "fallBackMethod")
	public ResponseEntity<Hospital> findAllDoctorInHospital_Feign_Retry(@PathVariable int hospitalId) {

		Hospital hospital = hospitalRepo.findHospitalById(hospitalId);

		List<Doctor> doctors = new ArrayList<>();

		if (hospital != null) {
			ResponseEntity<Doctor[]> entity = feignClient.findAllDoctorsByHospitalId(hospitalId);
			hospital.setDoctors(Arrays.asList(entity.getBody()));
			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		}
		return new ResponseEntity<Hospital>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/hospitals-feign-rate-limiter/{hospitalId}")
	@RateLimiter(name = "rate-limiter-for-doctor", fallbackMethod = "fallBackMethodRateLimiter")
	public ResponseEntity<Hospital> findAllDoctorInHospital_Feign_ratelimiter(@PathVariable int hospitalId) {

		Hospital hospital = hospitalRepo.findHospitalById(hospitalId);

		List<Doctor> doctors = new ArrayList<>();

		if (hospital != null) {
			ResponseEntity<Doctor[]> entity = feignClient.findAllDoctorsByHospitalId(hospitalId);
			hospital.setDoctors(Arrays.asList(entity.getBody()));
			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		}
		return new ResponseEntity<Hospital>(HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<Hospital> fallBackMethodRateLimiter(int hospitalId, Throwable e) {
		return new ResponseEntity<Hospital>(HttpStatus.TOO_MANY_REQUESTS);
	}
	
	@GetMapping("/hospitals-feign-bulkhead/{hospitalId}")
	@Bulkhead(name = "bulkhead-for-doctor", fallbackMethod = "fallBackMethodBulkhead")
	public ResponseEntity<Hospital> findAllDoctorInHospital_Feign_bulkhead(@PathVariable int hospitalId) {

		Hospital hospital = hospitalRepo.findHospitalById(hospitalId);

		List<Doctor> doctors = new ArrayList<>();

		if (hospital != null) {
			ResponseEntity<Doctor[]> entity = feignClient.findAllDoctorsByHospitalId(hospitalId);
			hospital.setDoctors(Arrays.asList(entity.getBody()));
			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		}
		return new ResponseEntity<Hospital>(HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<Hospital> fallBackMethodBulkhead(int hospitalId, Throwable e) {
		Hospital hospital = new Hospital();
		hospital.setHospitalName("fromFallBack");
		hospital.setHospitalAddress("fromFallBack");
		return new ResponseEntity<Hospital>(HttpStatus.TOO_MANY_REQUESTS);
	}
}
