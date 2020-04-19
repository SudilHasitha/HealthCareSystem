package com;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.DoctorDBHandler;

import model.Doctor;



@Path("doctor")
public class DoctorService implements DoctorInterface {

		
	
	DoctorDBHandler Docrepo = new DoctorDBHandler();		
		   
		@POST
		@Path("/InsertDoc")
		@Produces({MediaType.APPLICATION_JSON})//, MediaType.APPLICATION_XML
		public Doctor DoctorInsert(Doctor doc) {
			
			
			
			System.out.println(doc.getDoctor_id());
			System.out.println(doc.getDoctor_name());
			System.out.println(doc.getDob());
			System.out.println(doc.getGender());
			System.out.println(doc.getTelephone());
			System.out.println(doc.getDoc_fee());
			System.out.println(doc.getNic());
			
			
			Docrepo.DoctorInsert(doc);
			
			return doc;
		}
	
		
		@GET
		@Path("readdoc")
		@Produces(MediaType.APPLICATION_JSON)
		public String doctorRead() {
			System.out.println("getalldoctors is called");
			return Docrepo.doctorRead();
		}
		
		
		
		@PUT
		@Path("/updateDoc")
		@Produces({MediaType.APPLICATION_JSON})//, MediaType.APPLICATION_XML
		public Doctor updateDoc(Doctor doc) {
			
			System.out.println(doc.getDoctor_id());
			System.out.println(doc.getDoctor_name());
			System.out.println(doc.getDob());
			System.out.println(doc.getGender());
			System.out.println(doc.getTelephone());
			System.out.println(doc.getDoc_fee());
			System.out.println(doc.getNic());
			
			
			Docrepo.updateDoctor(doc);
			
			return doc;
		}
		
		@DELETE
		@Path("/delDoc/{id}")
		
		public String deleteDoc(@PathParam("id") int id) {
			
			String output = Docrepo.deleteDoc(id);
			return output;
		}
		
}
