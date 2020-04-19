package com;

import model.Doctor;



public interface DoctorInterface {
	
	public Doctor DoctorInsert(Doctor doc);
	
	public String doctorRead();
	
	public Doctor updateDoc(Doctor doc);

	public String deleteDoc(int id);
}


