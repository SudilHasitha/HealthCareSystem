package model;

import java.sql.Date;

public class Doctor {
	private int doctor_id;
	private String doctor_name;
	private Date dob;
	private String gender;
	private String telephone;
	private Double doc_fee;
	private String nic;
	
	
		public int getDoctor_id() {
			return doctor_id;
		}
		public void setDoctor_id(int doctor_id) {
			this.doctor_id = doctor_id;
		}
		public String getDoctor_name() {
			return doctor_name;
		}
		public void setDoctor_name(String doctor_name) {
			this.doctor_name = doctor_name;
		}
		public Date getDob() {
			return dob;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public Double getDoc_fee() {
			return doc_fee;
		}
		public void setDoc_fee(Double doc_fee) {
			this.doc_fee = doc_fee;
		}
		public String getNic() {
			return nic;
		}
		public void setNic(String nic) {
			this.nic = nic;
		}
		
}
