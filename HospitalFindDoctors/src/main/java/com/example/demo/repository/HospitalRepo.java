package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Hospital;

@Repository
public class HospitalRepo {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Hospital findHospitalById(int hospitalId) {
		Hospital hospital = null;

		String find_hospital_by_id = "select * from hospital where hospitalRegistrationId=?";

		try {
			hospital = jdbcTemplate.queryForObject(find_hospital_by_id, new RowMapper<Hospital>() {
				@Override
				public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
					Hospital hospital = new Hospital();
					hospital.setHospitalRegistrationId(rs.getInt(1));
					hospital.setHospitalName(rs.getString(2));
					hospital.setHospitalAddress(rs.getString(3));
					return hospital;
				}
			}, hospitalId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return hospital;
	}

}
