package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.DVisit;

public class DVisitDBHandler {
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

	public String DVisitInsert(DVisit visit) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "Insert into visit (visitID, doctor_ID, hospital_ID, date, time) "
					+ "values (?,?,?,?,?) ";

			PreparedStatement dc = con.prepareStatement(query);

			// binding values
			dc.setInt(1, visit.getVisitId());
			dc.setString(2, visit.getDoctor_id());
			dc.setString(3, visit.getHospital_id());
			dc.setDate(4, visit.getDate());
			dc.setString(5, visit.getTime());

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
	public String dVisitRead() {

		String output = "";

		try {

			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// A Html table to view
			output = "<table border=\"1\"><tr><th>visitId</th><th>doctor_id</th>"
					+ "<th>hospital_id</th><th>date</th><th>time</th></tr>";

			String query = "select * from visit";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			
				while (rs.next()) {

				String visitId = Integer.toString(rs.getInt(1));
				String doctor_id = rs.getString(2);
				String hospital_id = rs.getString(3);
				String date = rs.getDate(4).toString();
				String time = rs.getString(5);

				// Adding to the html table
				output += "<tr><td>" + visitId + "</td>";
				output += "<td>" + doctor_id + "</td>";
			  output += "<td>" + hospital_id + "</td>";
				output += "<td>" + date + "</td>";
        output += "<td>" + time + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" "
						+ "value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"dvisit.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"      "
						+ "class=\"btn btn-danger\">" + "<input name=\"visitId\" type=\"hidden\" value=\"" + visitId
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
	
	public String updateDVisit(DVisit visit)
	{

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "UPDATE visit SET doctor_id =?, hospital_id=?, date=?, time=?"
					+ " WHERE visitId=?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1,visit.getDoctor_id());
			preparedStatement.setString(2, visit.getHospital_id());
			preparedStatement.setDate(3, visit.getDate());
			preparedStatement.setString(4, visit.getTime());
			preparedStatement.setInt(5,visit.getVisitId());
			
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

	
	public String deleteDVisit(int id) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "Delete from visit where visitId= ?";

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
