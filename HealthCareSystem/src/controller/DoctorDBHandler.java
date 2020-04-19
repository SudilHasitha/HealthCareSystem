package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Doctor;
import model.Hospital;

public class DoctorDBHandler {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// Inset doctor SQL query//

	public String DoctorInsert(Doctor doc) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "Insert into doctor (doctor_id,doctor_name,dob," + "gender,telephone,doc_fee,nic) "
					+ "values (?,?,?,?,?,?,?) ";

			PreparedStatement dc = con.prepareStatement(query);

			// binding values
			dc.setInt(1, doc.getDoctor_id());
			dc.setString(2, doc.getDoctor_name());
			dc.setDate(3, doc.getDob());
			dc.setString(4, doc.getGender());
			dc.setString(5, doc.getTelephone());
			dc.setDouble(6, doc.getDoc_fee());
			dc.setString(7, doc.getNic());

			// execution
			dc.execute();
			con.close();

			output = "Inserted Successfully";

		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
			;
		}

		return output;

	}

//read sql queay
	public String doctorRead() {

		String output = "";

		try {

			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// A Html table to view
			output = "<table border=\"1\"><tr><th>doctor_id</th><th>doctor_name</th>"
					+ "<th>gender</th><th>telephon</th><th>doc_fee</th><th>nic</th></tr>";

			String query = "select * from doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			
				while (rs.next()) {

				String doctor_id = Integer.toString(rs.getInt(1));
				String doctor_name = rs.getString(2);
				// String dob = Date.toString(rs.getDate(3));
				String gender = rs.getString(4);
				String telephone = rs.getString(5);
				String doc_fee = Double.toString(rs.getDouble(6));
				String nic = rs.getString(7);

				// Adding to the html table
				output += "<tr><td>" + doctor_id + "</td>";
				output += "<td>" + doctor_name + "</td>";
			//	output += "<td>" + dob + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + telephone + "</td>";
				output += "<td>" + doc_fee + "</td>";
				output += "<td>" + nic + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" "
						+ "value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"doctor.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"      "
						+ "class=\"btn btn-danger\">" + "<input name=\"doctor_id\" type=\"hidden\" value=\"" + doctor_id
						+ "\">" + "</form></td></tr>";
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

	
	//update query//
	
	public String updateDoctor(Doctor doc) 
	{

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "UPDATE doctor SET doctor_name=?, dob=?, gender=?, telephone=?, doc_fee=?, nic=?"
					+ " WHERE doctor_id=?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1,doc.getDoctor_name());
			preparedStatement.setDate(2, doc.getDob());
			preparedStatement.setString(3, doc.getGender());
			preparedStatement.setString(4, doc.getTelephone());
			preparedStatement.setDouble(5,doc.getDoc_fee());
			preparedStatement.setString(6,doc.getNic());
			preparedStatement.setInt(7,doc.getDoctor_id());
			
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

	
	public String deleteDoc(int id) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "Delete from doctor where doctor_id= ?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setInt(1,id);

			// execution
			preparedStatement.execute();
			con.close();

			output = "Deleted Successfully";

		} catch (Exception e) {
			output = "Error while Deleting";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
}
