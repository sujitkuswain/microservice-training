package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.exception.DoctorAlreadyExistsException;

@Repository
public class DoctorDAOImpl implements DoctorDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int deleteDoctor(int doctorId) {
		// TODO Auto-generated method stub
		int deleted=0;
		String delete_doctor = "delete from doctor where doctorId=?";
		
		try {
			deleted = jdbcTemplate.update(delete_doctor, doctorId);
			return deleted;
		}
		catch(Exception e) {
			throw new DoctorAlreadyExistsException("Error in deleting doctor.");
		}
	}
	
	

}
