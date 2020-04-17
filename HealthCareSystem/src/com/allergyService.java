package com;

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
import model.Allergies;
import model.Patient;

@Path("allergies")
public class allergyService {
	
	
	
	PatientsDBHandler repo = new PatientsDBHandler();
	
	@GET
	@Produces({MediaType.APPLICATION_XML ,MediaType.APPLICATION_JSON})
	public List<Allergies> getAllergies() {
		
		System.out.println("getAllergies is called");
		return repo.getAllergies() ;
	}
	
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Allergies createAllergy(Allergies a1) {
		
		System.out.println(a1.getPatientID());
		System.out.println(a1.getAllergyname());

	
		repo.addAllergies(a1);	
		return a1;
	}
	
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Allergies> getAllergiesbyPateintID(@PathParam("id") int id) {	
		System.out.println("GetByPatient");
		return repo.getAllergybyPatientID(id) ;
	}

	
	@GET
	@Path("allergyID/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Allergies getAllergiesbyID(@PathParam("id") int id) {
		
		System.out.println("GetByID");
		return repo.getAllergybyID(id) ;
	}
	
	@PUT
	@Path("updateAllergy")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Allergies updateAllergy(Allergies a1) {
		
		System.out.println("Updated");
		
		System.out.println(a1.getAllergyID());
		System.out.println(a1.getPatientID());
		System.out.println(a1.getAllergyname());
		
		
		if(repo.getAllergybyID(a1.getAllergyID()).getAllergyID()==0) {
			
			repo.addAllergies(a1);;
		}
		else {
			repo.updateAllergy(a1);;
	}
			
		return a1;
	}
	
	@DELETE
	@Path("{id}")
	public Allergies deleteAllergy(@PathParam("id") int id) {
		
		Allergies a = repo.getAllergybyID(id);
		
		if(a.getAllergyID()!=0)
			repo.deleteAllergy(id);;
		
		return a;
	}

}
