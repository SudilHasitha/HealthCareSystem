package model;

import java.sql.*;

public class Payments {

	// Connect to DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Con details
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payments", "root", "Pa$$w0rd");
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return con;
	}

	public String readAll() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting";
			}

			// create html table
			output = "<table border=\"1\" class=\"table table-dark table-hover\"><tr><th>Payment ID</th>"
					+ "<th>Payment Type</th><th>Payment Amount</th><th>Appointment ID</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from payments";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// iterate through the results
			while (resultSet.next()) {
				String paymentID = Integer.toString(resultSet.getInt("paymentID"));
				String paymentType = resultSet.getString("paymentType");
				String paymentAmount = Double.toString(resultSet.getDouble("paymentAmount"));
				String AppointmentID = resultSet.getString("AppointmentID");

				// add to html table
				output += "<tr><td>" + paymentID + "</td>";
				output += "<td>" + paymentType + "</td>";
				output += "<td>" + paymentAmount + "</td>";
				output += "<td>" + AppointmentID + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + paymentID + "\">" + "</form></td></tr>";
			}

			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String read(String paymentID1) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting";
			}

			String query = "select * from payments where paymentID = " + paymentID1;
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				String paymentID = Integer.toString(resultSet.getInt("paymentID"));
				String paymentType = resultSet.getString("paymentType");
				String paymentAmount = Double.toString(resultSet.getDouble("paymentAmount"));
				String AppointmentID = resultSet.getString("AppointmentID");

				// add to html table
				output += "<tr><td>" + paymentID + "</td>";
				output += "<td>" + paymentType + "</td>";
				output += "<td>" + paymentAmount + "</td>";
				output += "<td>" + AppointmentID + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + paymentID + "\">" + "</form></td></tr>";
			}

			con.close();

		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		// TODO Auto-generated method stub
		return output;
	}

	public String update(String paymentID, String paymentType, String paymentAmount, String appointmentID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "UPDATE payments SET paymentType=?,paymentAmount=?,appointmentID=? WHERE paymentID=?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, paymentType);
			preparedStatement.setDouble(2, Double.parseDouble(paymentAmount));
			preparedStatement.setInt(3, Integer.parseInt(appointmentID));
			preparedStatement.setInt(4, Integer.parseInt(paymentID));

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

	public String insert(String paymentType, String paymentAmount, String appointmentID) {
	
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "Insert into payments (`paymentType`,`paymentAmount`,`appointmentID`) values (?,?,?) ";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, paymentType);
			preparedStatement.setDouble(2, Double.parseDouble(paymentAmount));
			preparedStatement.setInt(3, Integer.parseInt(appointmentID));

			// execution
			preparedStatement.execute();
			con.close();

			output = "Inserted Successfully";

		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
			;
		}

		return output;

	}

	public String delete(String ID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "Delete from payments where  paymentID = ?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setInt(1, Integer.parseInt(ID));

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
