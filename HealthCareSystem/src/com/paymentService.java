package com;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.Payments;
import model.Appointments;

//for JSON
import com.google.gson.*;

import controller.PaymentsDBHandler;

@Path("/paymentService")
public class paymentService implements PaymentServiceInterface {

	PaymentsDBHandler dbHandler = new PaymentsDBHandler();

	@GET
	@Path("/Admin/getAll/")
	@Produces(MediaType.TEXT_HTML)
	public String getPaymentAll() {
		return dbHandler.readAll();
	}

	@GET
	@Path("/Admin/getAllJSON/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Payments> getPaymentAllJSON() {
		return dbHandler.readAllJSON();
	}

	@Override
	@GET
	@Path("/getPayment/{ID}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Payments> getPaymentJSON(@PathParam("ID") int id) {
		return dbHandler.readJSON(String.valueOf(id));
	}

	@POST
	@Path("/getPayment/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String getPayment(String paymentID) {

		// Convert input string to json object
		JsonObject itemObject = new JsonParser().parse(paymentID).getAsJsonObject();

		String paymentID1 = itemObject.get("paymentID").getAsString();

		return dbHandler.read(paymentID1);
	}

	@PUT
	@Path("/Admin/updatePayment/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String updatePayment(@FormParam("paymentID") String id, @FormParam("paymentType") String type,
			@FormParam("appointmentID") String App_id, @FormParam("paymentAmount") String amount) {

		Payments payments = new Payments();

		payments.setPaymentID(Integer.parseInt(id));
		payments.setPaymentType(type);
		payments.setPaymentAmount(Double.parseDouble(App_id));
		payments.setAppointmentID(Integer.parseInt(amount));

		return dbHandler.update(payments);

	}

	@PUT
	@Path("/Admin/updatePaymentJSON/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String updatePayment(String data) {

		Payments payments = new Payments();

		// Convert input string to json object
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();

		payments.setPaymentID(itemObject.get("paymentID").getAsInt());
		payments.setPaymentType(itemObject.get("paymentType").getAsString());
		payments.setPaymentAmount(itemObject.get("paymentAmount").getAsDouble());
		payments.setAppointmentID(itemObject.get("appointmentID").getAsInt());

		return dbHandler.update(payments);
	}

	@POST
	@Path("/insertPayment/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String addPayment(@FormParam("paymentAmount") String amount, @FormParam("paymentType") String type,
			@FormParam("appointmentID") String App_id) {

		Payments payments = new Payments();

		payments.setPaymentType(type);
		payments.setPaymentAmount(Double.parseDouble(App_id));
		payments.setAppointmentID(Integer.parseInt(amount));
		
		return dbHandler.insert(payments);

	}

	@POST
	@Path("/insertPaymentJSON/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String addPayment(String data) {

		Payments payments = new Payments();
		
		// Convert input string to json object
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();

		payments.setPaymentType(itemObject.get("paymentType").getAsString());
		payments.setPaymentAmount(itemObject.get("paymentAmount").getAsDouble());
		payments.setAppointmentID(itemObject.get("appointmentID").getAsInt());

		return dbHandler.insert(payments);
	}

	@DELETE
	@Path("/Admin/deletePaymentJSON/")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String deletePayment(String id) {

		// Convert input string to json object
		JsonObject itemObject = new JsonParser().parse(id).getAsJsonObject();

		String ID = itemObject.get("paymentID").getAsString();
		return dbHandler.delete(ID);

	}

	@GET
	@Path("/getAppointmentID/")
	@Produces(MediaType.TEXT_HTML)
	public int getAppointmentID() {
		Appointments appointment = new Appointments();
		return appointment.getId();
	}

}
