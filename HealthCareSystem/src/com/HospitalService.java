package com;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import controller.HospitalDBHandler;
import model.Hospital;

@Path("hospital")
public class HospitalService implements HospitalInterface{
	 
	
	HospitalDBHandler Hosrepo = new HospitalDBHandler();
	
	@GET
	@Path("/readAllHos")
	@Produces(MediaType.TEXT_HTML)
	public String readAllHospital() {
		System.out.println("getAllHospital is called");
		return Hosrepo.readAllHospital() ;
	}
	   
	@POST
	@Path("/InsertHos")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Hospital HospitalInsert(Hospital hos) {
		
		System.out.println(hos.getHospital_reg_no());
		System.out.println(hos.getHos_name());
		System.out.println(hos.gethos_type());
		System.out.println(hos.getAddressLine1());
		System.out.println(hos.getCity());
		System.out.println(hos.getProvince());
		System.out.println(hos.getTelephone());
		System.out.println(hos.getHos_password());
		System.out.println(hos.getHos_email() );
		
		Hosrepo.HospitalInsert(hos);
		
		return hos;
	}
	
	@PUT
	@Path("/updateHos")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Hospital updateHos(Hospital hos) {
		
		System.out.println(hos.getHospital_reg_no());
		System.out.println(hos.getHos_name());
		System.out.println(hos.gethos_type());
		System.out.println(hos.getAddressLine1());
		System.out.println(hos.getCity());
		System.out.println(hos.getProvince());
		System.out.println(hos.getTelephone());
		System.out.println(hos.getHos_password());
		System.out.println(hos.getHos_email() );
		
			Hosrepo.updateHos(hos);
			
			return hos;
	}
	
 
	@DELETE
	@Path("/del")
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteHos(String id) {
		
		String output = Hosrepo.deleteHos(id);
		return output;
	}
	
}


