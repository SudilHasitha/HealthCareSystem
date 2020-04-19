package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Appointments;

public class AppointmentDBHandler {
	// connect to dbHandler

	private Connection connect() {
		Connection con = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String insertAppointment(String appointment_Code, int patient_id, String visit_ID, String status) {
		/*
		 * String hospitalID, Date AppointmentDate, LocalDateTime appointmentTime,
		 * String doctorID,
		 */

		String output = "";

		try {

			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = "insert into appointment(appointment_Code,patient_id ,visit_ID,status) values(?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, appointment_Code);
			preparedStmt.setInt(2, patient_id);
			preparedStmt.setString(3, visit_ID);
			preparedStmt.setString(4, status);

			// execution
			preparedStmt.execute();
			con.close();

			output = "Inserted Successfully";

		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;

	}

	public String getAppointmentID(String code) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			String query = "Select appointment_ID from appointment WHERE appointment_ID =?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, code);

			ResultSet rs = preparedStmt.executeQuery(query);

			String appointment_ID = Integer.toString(rs.getInt("appointment_ID"));

			output = appointment_ID;

			con.close();
		} catch (Exception e) {
			output = "Error while reading the Hos_id.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readAppointment() {

		String output = "";

		try {

			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>appointment_Code</th><th>patient_id</th><th>visit_ID</th>"
					+ "<th>status</th></tr>";

			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String appointment_ID = Integer.toString(rs.getInt(1));
				String appointment_Code = rs.getString(2);
				String patient_id = Integer.toString(rs.getInt(3));
				String visit_ID = rs.getString(4);
				String status = rs.getString(5);
				/*
				 * String doctor_ID = rs.getString("doctor_ID"); String hospital_ID =
				 * rs.getString("hospital_ID");
				 */

				// Add into the html table
				output += "<tr><td>" + appointment_Code + "</td>";
				output += "<td>" + patient_id + "</td>";
				output += "<td>" + visit_ID + "</td>";
				output += "<td>" + status + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" "
						+ "value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"appointments.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"      "
						+ "class=\"btn btn-danger\">" + "<input name=\"appointment_ID\" type=\"hidden\" value=\""
						+ appointment_ID + "\">" + "</form></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {

			output = "Error while reading the Appointment.";
			System.err.println(e.getMessage());

		}

		return output;

	}

	public String updateAppointment(Appointments appo) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "UPDATE appointment SET appointment_Code=?, patient_id=?, visit_ID=?, status=?"
					+ "WHERE appointment_ID=?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, appo.getAppointment_Code());
			preparedStatement.setInt(2, appo.getPatient_id());
			preparedStatement.setString(3, appo.getVisit_ID());
			preparedStatement.setString(4, appo.getStatus());
			preparedStatement.setInt(5, appo.getAppointment_ID());
		
			

			// execution
			preparedStatement.execute();
			con.close();

			output = "Updated Successfully";

		} catch (Exception e) {
			output = "Error while updating";
			System.err.println(e.getMessage());
			;
		}

		return output;
	}

	public String deleteAppointment(String ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from appointment where appointment_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Appointment.";
			System.err.println(e.getMessage()+e);
		}
		return output;
	}
	
	public int getAppointmentID() {
		int id = 0;

		try {
			Connection con = connect();

			String query = "select appointment_ID from appointment order by appointment_ID DESC Limit 1";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				id = resultSet.getInt(1);
				return id;
			}
			
			
			
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}
		return id;
		
		
	}

}
