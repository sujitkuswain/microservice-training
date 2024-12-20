package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Doctor;
import com.example.demo.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getCustomerById(@PathVariable int id) {
	    return doctorService.findDoctorById(id)
	    		.map(ResponseEntity::ok)
	    		.orElseGet(() -> ResponseEntity.notFound().build());
	}

}