package com;

import model.Hospital;

public interface HospitalInterface {
	public String readAllHospital();
	
	public Hospital HospitalInsert(Hospital hos);
	
	public Hospital updateHos(Hospital hos);
	
	public String deleteHos(String id);
	
	
}
