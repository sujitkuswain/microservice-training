package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DoctorDAO;
import com.example.demo.exception.DoctorAlreadyExistsException;
import com.example.demo.pojo.Doctor;

@RestController
public class DoctorController {

	@Autowired
	DoctorDAO doctorDAO;

	@Autowired
	StreamBridge bridge;

	@PostMapping(path = "/doctors", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor d) {
		int added = 0;
		try {
			added = doctorDAO.addDoctor(d);
		} catch (DoctorAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		if (added > 0) {
			boolean isPublished = bridge.send("doctorSupplier-out-0", MessageBuilder.withPayload(d).build());
			System.out.println("data published ="  + isPublished);
			return ResponseEntity.status(HttpStatus.CREATED).body(d);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
