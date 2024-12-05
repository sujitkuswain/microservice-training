package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
