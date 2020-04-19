package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controller.AppointmentDBHandler;
import model.Appointments;
import model.Patient;

@Path("/Appointment")
public class appointmentService {

	AppointmentDBHandler appointObj = new AppointmentDBHandler();

	@GET
	@Path("readAllAppoint")
	@Produces(MediaType.APPLICATION_JSON)
	public String readAppointment() {
		System.out.println("getAllAppointment is called");
		return appointObj.readAppointment();
	}

	/*
	 * @GET
	 * 
	 * @Path("readAllAppointments")
	 * 
	 * @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }) public
	 * String readAppointment() {
	 * 
	 * System.out.println("readAppointment is called"); return
	 * appointObj.readAppointment();
	 * 
	 * }
	 */

	@POST
	@Path("insertAppoint")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("appointment_Code") String appointment_Code,
			@FormParam("patient_id") int patient_id, @FormParam("visit_ID") String visit_ID,
			@FormParam("status") String status) {

		System.out.println("inserted the application");

		String output = appointObj.insertAppointment(appointment_Code, patient_id, visit_ID, status);
		return output;
	}

	@PUT
	@Path("/updateApp")
	@Produces({ MediaType.APPLICATION_JSON }) // MediaType.APPLICATION_XML
	public Appointments updateAppointment(Appointments appo) {

		System.out.println(appo.getAppointment_Code());
		System.out.println(appo.getPatient_id());
		System.out.println(appo.getVisit_ID());
		System.out.println(appo.getStatus());

		System.out.println("Updated the appointment");

		appointObj.updateAppointment(appo);

		return appo;
	}

	/*
	 * @PUT
	 * 
	 * @Path("updateAppointment")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String updateAppointment(String
	 * appoin) {
	 * 
	 * // Convert the input string to a JSON object JsonObject AppointObject = new
	 * JsonParser().parse(appoin).getAsJsonObject();
	 * 
	 * String appointment_Code =
	 * AppointObject.get("appointment_Code").getAsString(); String patient_id =
	 * AppointObject.get("patient_id").getAsString(); String visit_ID =
	 * AppointObject.get("visit_ID").getAsString(); String status =
	 * AppointObject.get("status").getAsString();
	 * 
	 * String output = appointObj.updateAppointment(appointment_Code, patient_id,
	 * visit_ID, status);
	 * 
	 * return output; }
	 */

	/*
	 * @PUT
	 * 
	 * @Path("updateAppoin")
	 * 
	 * @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) public
	 * Patient updatePatient(Appointments appoint) {
	 * 
	 * System.out.println("Updated");
	 * System.out.println(appoint.getAppointment_ID());
	 * System.out.println(appoint.getAppointment_Code());
	 * System.out.println(appoint.getPatient_id());
	 * System.out.println(appoint.getVisit_ID());
	 * System.out.println(appoint.getStatus());
	 * 
	 * if(appointObj.getAppointments(appoint.getAppointment_ID()).getAppointment_ID(
	 * )==0)
	 * 
	 * //if(repo.getPatient(p1.getId()).getId()==0) {
	 * 
	 * appointObj.insertAppointment(appoint); } else { repo.updatePatient(p1); }
	 * 
	 * return p1; }
	 */

	@DELETE
	@Path("deleteAppoint")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String ID) {
		// Convert the input string to an JSON document
		JsonObject jsonObject = new JsonParser().parse(ID).getAsJsonObject();

		String output = appointObj.deleteAppointment(jsonObject.get("ID").getAsString());
		return output;
	}

	/*
	 * @POST
	 * 
	 * @Path("/")
	 * 
	 * @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String
	 * insertAppointment(@FormParam("appointment_Code") String
	 * appointment_Code, @FormParam("patient_id") int patient_id,
	 * 
	 * @FormParam("visit_ID") String visit_ID, @FormParam("status") String status) {
	 * 
	 * String output = appointObj.insertAppointment(appointment_Code, patient_id,
	 * visit_ID, status); return output; }
	 * 
	 * 
	 * @GET
	 * 
	 * @Path("/Admin/getAppointmentID/")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public String getAppointmentID() {
	 * return appointDBHandler.getAppointmentID(); }
	 * 
	 */

	AppointmentDBHandler appointment = new AppointmentDBHandler();

	@GET
	@Path("/lastID")
	@Produces(MediaType.APPLICATION_JSON)
	public int getAppointmentID() {
		return appointment.getAppointmentID();
	}

}
