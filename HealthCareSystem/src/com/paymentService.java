package com;


import javax.ws.rs.*;

import javax.ws.rs.core.*;

import model.Payments;

//for JSON
import com.google.gson.*;

@Path("/paymentService")
public class paymentService {

	Payments payments = new Payments();

	@GET
	@Path("/getAll/")
	@Produces(MediaType.TEXT_HTML)
	public String getPaymentAll() {
		return payments.readAll();
	}

	@POST
	@Path("/getPayment/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String getPayment(String paymentID) {

		// Convert input string to json object
		JsonObject itemObject = new JsonParser().parse(paymentID).getAsJsonObject();

		String paymentID1 = itemObject.get("paymentID").getAsString();

		return payments.read(paymentID1);
	}

	@PUT
	@Path("/updatePayment/")
	@Produces(MediaType.TEXT_HTML)
	public String updatePayment(@FormParam("paymentID") String id, @FormParam("paymentType") String type,
			@FormParam("appointmentID") String App_id, @FormParam("paymentAmount") String amount) {

		return payments.update(id, type, amount, App_id);

	}

	@PUT
	@Path("/updatePaymentJSON/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String updatePayment(String data) {
		// Convert input string to json object
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();

		String paymentID = itemObject.get("paymentID").getAsString();
		String paymentType = itemObject.get("paymentType").getAsString();
		String paymentAmount = itemObject.get("paymentAmount").getAsString();
		String appointmentID = itemObject.get("appointmentID").getAsString();

		return payments.update(paymentID, paymentType, paymentAmount, appointmentID);
	}

	@POST
	@Path("/insertPayment/")
	@Produces(MediaType.TEXT_HTML)
	public String addPayment(@FormParam("paymentAmount") String amount, @FormParam("paymentType") String type,
			@FormParam("appointmentID") String App_id, String data) {

		return payments.insert(type, amount, App_id);

	}

	@POST
	@Path("/insertPaymentJSON/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String addPayment(String data) {
		
		// Convert input string to json object
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();

		String paymentType = itemObject.get("paymentType").getAsString();
		String paymentAmount = itemObject.get("paymentAmount").getAsString();
		String appointmentID = itemObject.get("appointmentID").getAsString();

		return payments.insert(paymentType, paymentAmount, appointmentID);
	}

	@DELETE
	@Path("/deletePaymentJSON/")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String deletePayment(String id) {
		
		// Convert input string to json object
		JsonObject itemObject = new JsonParser().parse(id).getAsJsonObject();
						
		String ID = itemObject.get("paymentID").getAsString();
		return payments.delete(ID);

	}

	@GET
	@Path("/getAppointment/")
	@Produces(MediaType.TEXT_HTML)
	public String getAppointmentID() {
		return payments.readAll();
	}
}
