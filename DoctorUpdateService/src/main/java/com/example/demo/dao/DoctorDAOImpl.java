package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Doctor;

@Repository
public class DoctorDAOImpl implements DoctorDAO {
	
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int updateDoctor(Doctor doctor) {
        String updateDoctorSql = "UPDATE doctor SET doctorName=?, specialization=?, hospitalId=? WHERE doctorId=?";

        try {
            return jdbcTemplate.update(updateDoctorSql, doctor.getDoctorName(), doctor.getSpecialization(),
                    doctor.getHospitalId(), doctor.getDoctorId());
        } catch (Exception e) {
            throw new RuntimeException("Error updating doctor: " + e.getMessage());
        }
    }
}
