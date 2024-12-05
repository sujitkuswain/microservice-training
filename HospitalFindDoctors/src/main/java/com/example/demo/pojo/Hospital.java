package com.example.demo.pojo;

import java.util.List;


public class Hospital {
	
	private int hospitalRegistrationId;
	private String hospitalName;
	private String hospitalAddress;
	
	private List<Doctor> doctors;

	public Hospital() {
	}

	public Hospital(int hospitalRegistrationId, String hospitalName, String hospitalAddress, List<Doctor> doctors) {
		super();
		this.hospitalRegistrationId = hospitalRegistrationId;
		this.hospitalName = hospitalName;
		this.hospitalAddress = hospitalAddress;
		this.doctors = doctors;
	}

	public int getHospitalRegistrationId() {
		return hospitalRegistrationId;
	}

	public void setHospitalRegistrationId(int hospitalRegistrationId) {
		this.hospitalRegistrationId = hospitalRegistrationId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	@Override
	public String toString() {
		return "Hospital [hospitalRegistrationId=" + hospitalRegistrationId + ", hospitalName=" + hospitalName
				+ ", hospitalAddress=" + hospitalAddress + ", doctors=" + doctors + "]";
	}

	

}
