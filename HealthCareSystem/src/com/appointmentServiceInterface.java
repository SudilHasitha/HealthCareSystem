package com;

import model.Appointments;

public interface appointmentServiceInterface {
	
	public Appointments insertAppointment(Appointments appoint);
	
	public String readAppointment();
	
	public Appointments updateAppointment(Appointments appo);
	//public Appointments updateAppointment(Appointments appoint);
	
	public String deleteAppointment(String ID);


}
