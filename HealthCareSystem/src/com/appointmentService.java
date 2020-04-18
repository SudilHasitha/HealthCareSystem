package com;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.AppointmentDBHandler;

@Path("/appointments")
public class appointmentService {

	AppointmentDBHandler appointment = new AppointmentDBHandler();
	
	@GET
	@Path("/lastID")
	@Produces(MediaType.APPLICATION_JSON)
	public int getAppointmentID() {
		return appointment.getAppointmentID();
	}
	
}
