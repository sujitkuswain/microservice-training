package com.example.demo.pojo;

public class Doctor {
	private int docterId;
	private String doctorName;
	private String specialization;
	private int hospitalId;

	public Doctor() {

	}

	public Doctor(int docterId, String doctorName, String specialization, int hospitalId) {
		super();
		this.docterId = docterId;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.hospitalId = hospitalId;
	}

	public int getDocterId() {
		return docterId;
	}

	public void setDocterId(int docterId) {
		this.docterId = docterId;
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
		return "Doctor [docterId=" + docterId + ", doctorName=" + doctorName + ", specialization=" + specialization
				+ ", hospitalId=" + hospitalId + "]";
	}

}
