package com;

import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;

public interface HospitalInterface {
	
	//request admin only
	public String readAllHospital();
	
	//request admin only
	public String HospitalInsert(@FormParam("hospital_reg_no") String hospital_reg_no,
			@FormParam("hos_name") String hos_name,
			@FormParam("hos_type") String hos_type,
			@FormParam("AddressLine1") String AddressLine1,
			@FormParam("city") String city,
			@FormParam("province") String province,
			@FormParam("telephone") String telephone,
			@FormParam("hospital_fee") String hospital_fee,
			@FormParam("hos_password") String hos_password);
	
	public String readHospital(@PathParam("reg") int id);
	
	public String updateHosPassword(String hosData);
	
	public String deleteHospital(String ID);
	
	public boolean CheckLogin(@PathParam("regNo") String no,
			@PathParam("password") String password);
	
	//public String readHospitalDoctersDetail(@PathParam("reg") int id)
}
