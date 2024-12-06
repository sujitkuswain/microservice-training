package com.example.demo.pojo;

public class Doctor {
	private int doctorId;
	private String doctorName;
	private String specialization;
	private int hospitalId;

	public Doctor() {

	}

	public Doctor(int docterId, String doctorName, String specialization, int hospitalId) {
		super();
		this.doctorId = docterId;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.hospitalId = hospitalId;
	}

	

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	@Override
	public String toString() {
		return "Doctor [docterId=" + doctorId + ", doctorName=" + doctorName + ", specialization=" + specialization
				+ ", hospitalId=" + hospitalId + "]";
	}

}
