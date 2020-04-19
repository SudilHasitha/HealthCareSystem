package model;

import java.sql.Date;

public class DVisit {
	private int visitId;
	private String doctor_id;
	private String hospital_id;
	private Date date;
	private String time;
	
	
	public int getVisitId() {
		return visitId;
	}
	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}
	public String getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getHospital_id() {
		return hospital_id;
	}
	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = Date.valueOf(date);
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
