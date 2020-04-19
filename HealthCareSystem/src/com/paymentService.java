package com;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import model.Payments;


//for JSON
import com.google.gson.*;

import controller.PaymentsDBHandler;

@Path("/paymentService")
public class paymentService implements PaymentServiceInterface {

	public paymentService() {
		super();
	}

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
	@Path("/patients/secured/getPayment/{ID}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Payments> getPaymentJSON(@PathParam("ID") int id) {
		return dbHandler.readJSON(String.valueOf(id));
	}

	@POST
	@Path("/patients/secured/getPayment/")
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
		
		
		
		int ID =Integer.parseInt(id);
		String TYPE  = type;
		Double AMOUNT = Double.parseDouble(amount);
		int APPID = Integer.parseInt(App_id);
		
		
		payments.setPaymentID(ID);
		payments.setPaymentType(TYPE);
		payments.setPaymentAmount(AMOUNT);
		payments.setAppointmentID(APPID);

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
	@Path("/patients/secured/insertPayment/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String addPayment(@FormParam("paymentAmount") String amount, @FormParam("paymentType") String type) {

		paymentService ps = new paymentService();
		Payments payments = new Payments();

		payments.setPaymentType(type);
		payments.setAppointmentID(ps.getAppointmentID());
		payments.setPaymentAmount(Double.parseDouble(amount));
		
		return dbHandler.insert(payments);

	}

	@POST
	@Path("/patients/secured/insertPaymentJSON/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String addPayment(String data) {

		Payments payments = new Payments();
		paymentService ps = new paymentService();
		
		// Convert input string to json object
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();

		payments.setPaymentType(itemObject.get("paymentType").getAsString());
		payments.setPaymentAmount(itemObject.get("paymentAmount").getAsDouble());
		payments.setAppointmentID(ps.getAppointmentID());

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

	//implement communication between services
	public int getAppointmentID() {
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8080/HealthCareSystem/Services/appointments").path("lastID");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		 
		Integer id = response.readEntity(Integer.class);
		     
		System.out.println(response.getStatus());
		System.out.println(id);
		 
		return id;
	}

}
