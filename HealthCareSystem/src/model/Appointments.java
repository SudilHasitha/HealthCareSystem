package model;

public class Appointments {
	
	private int id;
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getAppointmentID() {
		
		Appointments a = new Appointments();
		a.setId(4); // result of db query
		//DB Query 
		//select * from appointments order by appointment_date DESC Limit 1 ;
		//		
		return String.valueOf(a.getId());
	}

}
