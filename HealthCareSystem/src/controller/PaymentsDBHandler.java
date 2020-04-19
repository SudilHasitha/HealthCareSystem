package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.paymentService;

import model.Payments;

public class PaymentsDBHandler {
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

				String query = "select * from payments";
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(query);

				// iterate through the results
				while (resultSet.next()) {
					String paymentID = Integer.toString(resultSet.getInt(1));
					String paymentType = resultSet.getString(2);
					String paymentAmount = Double.toString(resultSet.getDouble(3));
					String AppointmentID = resultSet.getString(4);

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
				
		public List<Payments> readAllJSON() {
			List<Payments> Results = new ArrayList<Payments>(); 

			try {
				Connection con = connect();

				String query = "select * from payments";
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(query);

				// iterate through the results
				while (resultSet.next()) {
					
					Payments payment = new Payments();
					
					payment.setPaymentID(resultSet.getInt(1));
					payment.setPaymentType(resultSet.getString(2));
					payment.setPaymentAmount(resultSet.getDouble(3));
					payment.setAppointmentID(resultSet.getInt(4));
					
					Results.add(payment);
				}
				
			} catch (Exception e) {
				
				System.err.println(e.getMessage());
			}
			return Results;
			
			
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
	
		public 	List<Payments> readJSON(String paymentID1) {

			List<Payments> Results = new ArrayList<Payments>(); 

			try {
				Connection con = connect();


				String query = "select * from payments where paymentID = " + paymentID1;
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					
					Payments payments = new Payments();
					
					payments.setPaymentID(resultSet.getInt("paymentID"));
					payments.setPaymentType(resultSet.getString("paymentType"));
					payments.setPaymentAmount(resultSet.getDouble("paymentAmount"));
					payments.setAppointmentID(resultSet.getInt("AppointmentID"));

					Results.add(payments);
				}

				con.close();

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			// TODO Auto-generated method stub
			return Results;
		}

		public String update(Payments payments) {

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
				preparedStatement.setString(1, payments.getPaymentType());
				preparedStatement.setDouble(2, payments.getPaymentAmount());
				preparedStatement.setInt(3, payments.getAppointmentID());
				preparedStatement.setInt(4, payments.getPaymentID());

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

		public String insert(Payments payments) {
		
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
				preparedStatement.setString(1, payments.getPaymentType());
				preparedStatement.setDouble(2, payments.getPaymentAmount());
				preparedStatement.setInt(3, payments.getAppointmentID());

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
