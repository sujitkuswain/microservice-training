package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Doctor;
import com.example.demo.pojo.Hospital;

@RestController
public class Find_Doctors_in_Hospital_Controller {

	List<Doctor> doctors_received = new ArrayList<>();

	@Bean
	public Consumer<String> readDoctors() {
		System.out.println("***********received doctor details**********");

		return (doctor) -> {
			System.out.println("Inside the consumer");
			System.out.println("Consumer Received : " + doctor);
		};
	}

	@GetMapping("/hospitals/{hospitalId}")
	ResponseEntity<Hospital> findAllDoctorsInHospitals(@PathVariable int hospitalId) {
		List<Doctor> doctors = new ArrayList<>();
		Hospital hospital = new Hospital();
		hospital.setDoctors(doctors_received);

		return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
	}

}