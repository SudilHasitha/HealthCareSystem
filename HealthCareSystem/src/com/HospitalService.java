package com;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controller.HospitalDBHandler;

@Path("/hospital")
public class HospitalService implements HospitalInterface{
	 
	// Admin username : Admin | Password =Admin123
	
	HospitalDBHandler HosObj = new HospitalDBHandler();
	@RolesAllowed("ADMIN")
	@GET
	@Path("readallHos")
	@Produces(MediaType.TEXT_HTML)
	public String readAllHospital() {
		System.out.println("getAllHospital is called");
		return HosObj.readAllHospital() ;
	}
	
	@RolesAllowed("ADMIN")
	@POST
	@Path("insertHos")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String HospitalInsert(@FormParam("hospital_reg_no") String hospital_reg_no,
			@FormParam("hos_name") String hos_name,
			@FormParam("hos_type") String hos_type,
			@FormParam("AddressLine1") String AddressLine1,
			@FormParam("city") String city,
			@FormParam("province") String province,
			@FormParam("telephone") String telephone,
			@FormParam("hospital_fee") String hospital_fee,
			@FormParam("hos_password") String hos_password) 
	{
		
		String output = HosObj.HospitalInsert(hospital_reg_no, hos_name, hos_type, AddressLine1,city,province,telephone,hospital_fee,hos_password);
		return output;
	}
	
	@PUT
	@Path("updatehos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHos(String hosData) {
		
		//Convert the input string to a JSON object
		JsonObject HospObject = new JsonParser().parse(hosData).getAsJsonObject();
			
		 String hospital_reg_no= HospObject.get("hospital_reg_no").getAsString();
		 String hos_name= HospObject.get("hos_name").getAsString();
		 String hos_type= HospObject.get("hos_type").getAsString();
		 String AddressLine1= HospObject.get("AddressLine1").getAsString();
		 String city= HospObject.get("city").getAsString();
		 String province= HospObject.get("province").getAsString();
		 String telephone= HospObject.get("telephone").getAsString();
		 String hospital_fee = HospObject.get("hospital_fee").getAsString();

		 String output = HosObj.updateHos(hospital_reg_no, hos_name, hos_type, AddressLine1, city, province, telephone, hospital_fee);
		
		 return output;
	}
	
	@GET
	@Path("{reg}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
	public String readHospital(@PathParam("reg") int id) {
		System.out.println("getHospital is called");
		String output = HosObj.readHospital(id);
		return output;
	
	}
	
	/*@GET
	@Path("dochos/{reg}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
	public String readHospitalDoctersDetail(@PathParam("reg") int id) {
		System.out.println("getHospital is called");
		String output = HosObj.readHospitalDoctersDetail(id);
		return output;
	
	}*/
	@PUT
	@Path("updatePassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHosPassword(String hosData) {
		
		//Convert the input string to a JSON object
		JsonObject HospObject = new JsonParser().parse(hosData).getAsJsonObject();
			
		 String hospital_reg_no= HospObject.get("hospital_reg_no").getAsString();
		 String hos_password = HospObject.get("hos_password").getAsString();

		 String output = HosObj.updateHosPassword(hospital_reg_no, hos_password);
		
		 return output;
	}
	

	@DELETE
	@Path("deletehos")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String ID)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(ID, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String hos_id = doc.select("hos_id").text();
		
		String output = HosObj.deleteHospital(hos_id);
		return output;
	}
	
	@GET
	@Path("loginCheck/{regNo}/{password}")
	@Produces(MediaType.TEXT_HTML)
	public boolean CheckLogin(@PathParam("regNo") String no, @PathParam("password") String password) {
		
		return HosObj.loginHos(no, password);	
	}
}


