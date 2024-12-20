package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.DoctorException;
import com.example.demo.pojo.Doctor;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;
	
	public Optional<Doctor> findDoctorById (int id) {
//		return customerRepository.findById(id);
		Optional<Doctor> customer = doctorRepository.findById(id);
		
		if(!customer.isPresent()) {
			throw new DoctorException("Doctor with id:" + id +" not found");
		}
		
		return customer;
	}
}
