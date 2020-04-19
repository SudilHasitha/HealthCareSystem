package model;

public class Appointments {

	private int appointment_ID;
	private String appointment_Code;
	private int patient_id; // patient_id
	private String visit_ID;
	private String status;

	public Appointments(int appointment_ID, String appointment_Code, int patient_id, String visit_ID, String status) {
		super();
		this.appointment_ID = appointment_ID;
		this.appointment_Code = appointment_Code;
		this.patient_id = patient_id;
		this.visit_ID = visit_ID;
		this.status = status;
	}

	public Appointments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAppointment_ID() {
		return appointment_ID;
	}

	public void setAppointment_ID(int appointment_ID) {
		this.appointment_ID = appointment_ID;
	}

	public String getAppointment_Code() {
		return appointment_Code;
	}

	public void setAppointment_Code(String appointment_Code) {
		this.appointment_Code = appointment_Code;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getVisit_ID() {
		return visit_ID;
	}

	public void setVisit_ID(String visit_ID) {
		this.visit_ID = visit_ID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Appointments [appointment_Code=" + appointment_Code + ", patient_id=" + patient_id + ", visit_ID=" + visit_ID
				+ ", status=" + status + "]";
	}

	/*
	 * public int getId() { return id; }
	 * 
	 * 
	 * 
	 * public void setId(int id) { this.id = id; }
	 * 
	 * 
	 * 
	 * public String getAppointmentID() {
	 * 
	 * Appointments a = new Appointments(); a.setId(4); // result of db query //DB
	 * Query //select * from appointments order by appointment_date DESC Limit 1 ;
	 * // return String.valueOf(a.getId()); }
	 */

}
