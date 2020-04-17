package com;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Appointments;

@Path("/appointmentService")
public class appointmentService {

	Appointments appointment = new Appointments();
	
	@GET
	@Path("/Admin/getAppointmentID/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAppointmentID() {
		return appointment.getAppointmentID();
	}
	
}
