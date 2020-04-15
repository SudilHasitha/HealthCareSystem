package com;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.PatientsDBHandler;
import model.Patient;


@Path("patients")
public class PatientService {
	 
	
	PatientsDBHandler repo = new PatientsDBHandler();
	
	@GET
	@Produces({MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON})
	public List<Patient> getPatients() {
		
		System.out.println("getPatient is called");
		
	
		return repo.getPatients() ;
	}
	   
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Patient getPatient(@PathParam("id") int id) {
		
		return repo.getPatient(id) ;
	}

	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Patient createPatient(Patient p1) {
		
		System.out.println(p1.getAge());
		System.out.println(p1.getName() );
		repo.createPatient(p1);
		
		return p1;
	}
	
	@PUT
	@Path("updatePatient")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Patient updatePatient(Patient p1) {
		
		System.out.println(p1.getId());
		System.out.println(p1.getAge());
		System.out.println(p1.getName() );
		if(repo.getPatient(p1.getId()).getId()==0) {
			
			repo.createPatient(p1);
		}
		else {
			repo.updatePatient(p1);
		}
			

		
		return p1;
	}
	
 
	@DELETE
	@Path("{id}")
	public Patient deletePatient(@PathParam("id") int id) {
		
		Patient p = repo.getPatient(id);
		
		if(p.getId()!=0)
			repo.deletePatient(id);
		
		return p;
	}
	
}


