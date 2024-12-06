package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DoctorDAO;
import com.example.demo.pojo.Doctor;

@RestController
public class DoctorController {

	@Autowired
	DoctorDAO doctorDAO;


    @PutMapping("/doctor/{doctorId}")
    public ResponseEntity<String> updateDoctor(@PathVariable int doctorId, @RequestBody Doctor updatedDoctor) {
        if (doctorId != updatedDoctor.getDoctorId()) {
            return ResponseEntity.badRequest().body("Doctor ID in URL does not match ID in request body.");
        }

        try {
            int updated = doctorDAO.updateDoctor(updatedDoctor);
            if (updated > 0) {
                return ResponseEntity.ok("Doctor updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error updating doctor");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating doctor: " + e.getMessage());
        }
    }
}
