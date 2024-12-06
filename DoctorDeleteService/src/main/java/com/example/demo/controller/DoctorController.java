package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DoctorDAO;
import com.example.demo.pojo.Doctor;

@RestController
public class DoctorController {

	@Autowired
	DoctorDAO doctorDAO;

	@DeleteMapping("/doctor/{doctorId}")
	public ResponseEntity<Doctor> createDoctor(@PathVariable int doctorId) {
		int deleted = 0;
		try {
			deleted = doctorDAO.deleteDoctor(doctorId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		if (deleted > 0) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
