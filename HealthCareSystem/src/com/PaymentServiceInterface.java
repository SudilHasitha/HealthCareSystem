package com;

import java.util.List;

import javax.ws.rs.*;

import model.Appointments;
import model.Payments;


public interface PaymentServiceInterface {

	public String getPaymentAll();
	
	public List<Payments> getPaymentAllJSON();

	public List<Payments> getPaymentJSON(@PathParam("ID") int id);

	public String getPayment(String paymentID);

	public String updatePayment(@FormParam("paymentID") String id, @FormParam("paymentType") String type,
			@FormParam("appointmentID") String App_id, @FormParam("paymentAmount") String amount);

	public String updatePayment(String data);

	public String addPayment(@FormParam("paymentAmount") String amount, @FormParam("paymentType") String type,
			@FormParam("appointmentID") String App_id);

	public String addPayment(String data);

	public String deletePayment(String id);

	public int getAppointmentID();
}
