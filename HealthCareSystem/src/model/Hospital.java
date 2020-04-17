package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hospital {
	
	private int hos_id;
	private String hospital_reg_no;
	private String hos_name;
	private String hos_type;
	private String AddressLine1;
	private String city;
	private String province;
	private String telephone;
	private double hospital_fee;
	private String hos_password;
	
	public int getHos_id() {
		return hos_id;
	}
	
	public String getHospital_reg_no() {
		return hospital_reg_no;
	}
	public void setHospital_reg_no(String hospital_reg_no) {
		this.hospital_reg_no = hospital_reg_no;
	}
	public String getHos_name() {
		return hos_name;
	}
	public void setHos_name(String hos_name) {
		this.hos_name = hos_name;
	}
	public String gethos_type() {
		return hos_type;
	}
	public void sethos_type(String hos_type) {
		this.hos_type = hos_type;
	}
	public String getAddressLine1() {
		return AddressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public double getHospital_fee() {
		return hospital_fee;
	}
	public void setHospital_fee(double hospital_fee) {
		this.hospital_fee = hospital_fee;
	}
	public String getHos_password() {
		return hos_password;
	}
	public void setHos_password(String hos_password) {
		this.hos_password = hos_password;
	}


	@Override
	public String toString() {
		return "Hospital [hospital_reg_no=" + hospital_reg_no + ", hos_name=" + hos_name + ", hos_type="
				+ hos_type + ", AddressLine1=" + AddressLine1 + ", city=" + city + ", province=" + province
				+ ", telephone=" + telephone + ", hospital_fee=" + hospital_fee + ", hos_password=" + hos_password
				+ ", hos_email=" + "]";
	}
	
}
