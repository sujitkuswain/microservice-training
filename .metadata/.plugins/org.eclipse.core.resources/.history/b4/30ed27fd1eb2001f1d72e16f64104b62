package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	List<Doctor> findByHospitalId(int hospitalId);	

}
