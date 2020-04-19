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

import controller.DVisitDBHandler;
import model.DVisit;
import model.Doctor;



@Path("dvisit")
public class DVisitService implements DVisitInterface {
	  DVisitDBHandler visrepo = new DVisitDBHandler();		
		   
		@POST
		@Path("/InsertVisit")
		@Produces(MediaType.APPLICATION_JSON)//, MediaType.APPLICATION_XML
		public DVisit DVisitInsert(DVisit visit) {
			visrepo.DVisitInsert(visit);
			return visit;
		}
	
		
		@GET
		@Path("/readVisit")
		@Produces(MediaType.APPLICATION_JSON)
		public String dVisitRead() {
			System.out.println("getalldoctors is called");
			return visrepo.dVisitRead();
		}
		
		
		
		@PUT
		@Path("/updateVisit")
		@Produces({MediaType.APPLICATION_JSON})//, MediaType.APPLICATION_XML
		public DVisit updateDVisit(DVisit visit) {
			visrepo.updateDVisit(visit);
			return visit;
		}
		
		@DELETE
		@Path("/delVisit/{id}")
		public String deleteDVisit(@PathParam("id") int id) {
			String output = visrepo.deleteDVisit(id);
			return output;
		}
		
}
