package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Doctor;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.service.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	DoctorRepository repo;
	
	@GetMapping
	public List<Doctor> getDoctors() {
		return doctorService.getAllDoctors();
	}

//	@GetMapping
//	public List<Doctor> getDoctors(@RequestHeader(name="sort", defaultValue="all") String sort) {
//		System.out.println("Found header:" + sort);
//		return doctorService.getAllDoctors();
//	}
	
//	@GetMapping
//	public List<Doctor> getDoctors(@RequestParam(name="dept") String dept) {
//		System.out.println("Found param:" + dept);
//		return doctorService.getAllDoctors();
//	}
	
	@Operation(summary="Get hostpial doctors by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description="Success"),
			@ApiResponse(responseCode="400", description="Invalid")
	})
	@GetMapping("/doctors/{hospitalId}")
	public ResponseEntity<List<Doctor>> findAllDoctorByHospitalId(@PathVariable int hospitalId){
		List<Doctor> doctors = new ArrayList<>(); 
		
		doctors = repo.findByHospitalId(hospitalId);
		
		if(doctors.size() > 0) {
			return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
		}
		return new ResponseEntity<List<Doctor>>(HttpStatus.NOT_FOUND);
	}

}