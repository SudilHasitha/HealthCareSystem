package controller;


import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import model.Patient;

public class PatientsDBHandler {

	Connection con = null;

	public PatientsDBHandler()  {
		
		String url = "jdbc:mysql://localhost:3308/paf";
		String username ="root";
		String password = "admin";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	public List<Patient> getPatients() {

		List<Patient> patients = new ArrayList<>();
		String sql = "Select * from patients";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			

			while (rs.next()) {
				Patient p = new Patient();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setAge(rs.getInt(3));
				
				patients.add(p);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return patients;
	}

	public Patient getPatient(int id) {

		String sql = "Select * from patients where patientID="+id;
		Patient p = new Patient();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if(rs.next()) {
				
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setAge(rs.getInt(3));

				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
  
		return p;
	}

	public void createPatient(Patient p1) {
		// TODO Auto-generated method stub
		String sql = "insert into patients ( patientName,patientAge) values (?, ?);" ; 
		
		try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1 , p1.getName());
			st.setInt(2, p1.getAge());
			st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void updatePatient(Patient p1) {
		// TODO Auto-generated method stub
		String sql = "update patients set patientName=? , patientAge=? where patientID=? "; 
		
		try {
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1 , p1.getName());
			st.setInt(2, p1.getAge());
			st.setInt(3, p1.getId());

			st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	public void deletePatient(int id) {
		// TODO Auto-generated method stub
		
 String sql = "delete from patients where patientID=?"; 
		
		try {
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1,id );

			st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	

}
	

