package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.exception.DoctorAlreadyExistsException;
import com.example.demo.pojo.Doctor;

@Repository
public class DoctorDAOImpl implements DoctorDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	@Override
	public int addDoctor(Doctor d) {
		// TODO Auto-generated method stub
		int added = 0;
		String insert_doctor = "insert into doctor values(?,?,?,?)";
		
		try {
			added = jdbcTemplate.update(insert_doctor, d.getDocterId(), d.getDoctorName(), d.getSpecialization(),d.getHospitalId());
		}
		catch(Exception e) {
			throw new DoctorAlreadyExistsException("Already exists");
		}
		
		return added;
	}
	
	

}
