package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Payments {

	private int paymentID;
	private String paymentType;
	private Double paymentAmount;
	private int appointmentID;
	
	

	public Payments() {
		super();
	}

	public Payments(int paymentID, String paymentType, Double paymentAmount, int appointmentID) {
		super();
		this.paymentID = paymentID;
		this.paymentType = paymentType;
		this.paymentAmount = paymentAmount;
		this.appointmentID = appointmentID;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	
	
	@Override
	public String toString() {
		return "Payments [paymentID=" + paymentID + ", paymentType=" + paymentType + ", paymentAmount=" + paymentAmount
				+ ", appointmentID=" + appointmentID + "]";
	}
}
	