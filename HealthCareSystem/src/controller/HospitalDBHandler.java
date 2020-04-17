package controller;

import java.sql.*;

import model.Hospital;

public class HospitalDBHandler {
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital", "root", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String HospitalInsert(Hospital hos) {
		
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "Insert into hospital (hospital_reg_no,hos_name,hos_type,"
					+ "AddressLine1,city,province,telephone,hospital_fee,hos_password,hos_email) "
					+ "values (?,?,?,?,?,?,?,?,?,?) ";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, hos.getHospital_reg_no());
			preparedStatement.setString(2, hos.getHos_name());
			preparedStatement.setString(3, hos.gethos_type());
			preparedStatement.setString(4, hos.getAddressLine1());
			preparedStatement.setString(5, hos.getCity());
			preparedStatement.setString(6, hos.getProvince());
			preparedStatement.setString(7, hos.getTelephone());
			preparedStatement.setDouble(8, hos.getHospital_fee());
			preparedStatement.setString(9, hos.getHos_password());
			preparedStatement.setString(10, hos.getHos_email());
			

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
	
	public String updateHos(Hospital hos) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "UPDATE hospital SET hospital_reg_no=?,hos_name=?,hos_type=?,AddressLine1=?,city=?,province=?,telephone=?,hospital_fee=?,hos_password=?,hos_email=?"
					+ " WHERE hos_id =?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, hos.getHospital_reg_no());
			preparedStatement.setString(2, hos.getHos_name());
			preparedStatement.setString(3, hos.gethos_type());
			preparedStatement.setString(4, hos.getAddressLine1());
			preparedStatement.setString(5, hos.getCity());
			preparedStatement.setString(6, hos.getProvince());
			preparedStatement.setString(7, hos.getTelephone());
			preparedStatement.setDouble(8, hos.getHospital_fee());
			preparedStatement.setString(9, hos.getHos_password());
			preparedStatement.setString(10, hos.getHos_email());
			preparedStatement.setInt(11, hos.getHos_id());
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
	
	public String deleteHos(String RegNo) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "Delete from hospital where  Hospital_reg_no= ?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1,RegNo);

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
	
	public String readHospital(String regno) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Registration No</th><th>Name</th><th>Type</th>"
					+ "<th>Addressline</th><th>city</th><th>province</th><th>Contact</th><th>Fee</th><th>E-mail</th></tr>";
			
			String query = "select * from hospital where hospital_reg_no = "+regno;
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// iterate through the results
			while (resultSet.next()) {
				String hos_id = Integer.toString(resultSet.getInt(1));
				String hospital_reg_no = resultSet.getString(2);
				String hos_name = resultSet.getString(3);
				String type_private = resultSet.getString(4);
				String AddressLine1 = resultSet.getString(5);
				String city = resultSet.getString(6);
				String province = resultSet.getString(7);
				String telephone = resultSet.getString(8);
				String hospital_fee = Double.toString(resultSet.getDouble(9));
				String hos_email = Double.toString(resultSet.getDouble(11));
				
				// add to html table
				output += "<tr><td>" + hospital_reg_no + "</td>";
				output += "<td>" + hos_name + "</td>";
				output += "<td>" + type_private + "</td>";
				output += "<td>" + AddressLine1 + "</td>";
				output += "<td>" + city + "</td>";
				output += "<td>" + province + "</td>";
				output += "<td>" + telephone + "</td>";
				output += "<td>" + hospital_fee + "</td>";
				output += "<tr><td>" + hos_email + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + hos_id + "\">" + "</form></td></tr>";
			}

			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readAllHospital() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Registration No</th><th>Name</th><th>Type</th>"
					+ "<th>Addressline</th><th>city</th><th>province</th><th>Contact</th><th>Fee</th><th>E-mail</th></tr>";
			
			String query = "select * from hospital";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// iterate through the results
			while (resultSet.next()) {
				String hos_id = Integer.toString(resultSet.getInt(1));
				String hospital_reg_no = resultSet.getString(2);
				String hos_name = resultSet.getString(3);
				String type_private = resultSet.getString(4);
				String AddressLine1 = resultSet.getString(5);
				String city = resultSet.getString(6);
				String province = resultSet.getString(7);
				String telephone = resultSet.getString(8);
				String hospital_fee = Double.toString(resultSet.getDouble(9));
				String hos_email = Double.toString(resultSet.getDouble(11));
				
				// add to html table
				output += "<tr><td>" + hospital_reg_no + "</td>";
				output += "<td>" + hos_name + "</td>";
				output += "<td>" + type_private + "</td>";
				output += "<td>" + AddressLine1 + "</td>";
				output += "<td>" + city + "</td>";
				output += "<td>" + province + "</td>";
				output += "<td>" + telephone + "</td>";
				output += "<td>" + hospital_fee + "</td>";
				output += "<tr><td>" + hos_email + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + hos_id + "\">" + "</form></td></tr>";
			}

			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
/*	public String readHospitalDoctersDetail() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Registration No</th><th>Hospital Name</th><th>Hospital fee</th>"
					+ "<th>Docter ID</th><th>Docter Name</th><th>Docter fee</th></tr>";
			
			String query = "select h.hospital_reg_no,h.hos_name,h.hospital_fee,d.doc_id,d.docname,d.docfee from hospital h,doctor d"
					+ "where hos_id=?"
					+ "order by h.hospital_reg_no";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// iterate through the results
			while (resultSet.next()) {
				String hospital_reg_no = resultSet.getString(1);
				String hos_name = resultSet.getString(2);
				String hospital_fee = Double.toString(resultSet.getDouble(3));
				String Docid = resultSet.getString(4);
				String DocName = resultSet.getString(5);
				String Docfee = Double.toString(resultSet.getDouble(6));
				
				// add to html table
				output += "<tr><td>" + hospital_reg_no + "</td>";
				output += "<td>" + hos_name + "</td>";
				output += "<td>" + hospital_fee + "</td>";
				output += "<td>" + Docid + "</td>";
				output += "<td>" + DocName + "</td>";
				output += "<td>" + Docfee + "</td>";
			}

			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}
	*/
	/*public String readHospitalPatientDetail() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Registration No</th><th>Hospital Name</th><th>Hospital fee</th>"
					+ "<th>Docter ID</th><th>Docter Name</th><th>Docter fee</th></tr>";
			
			String query = "select h.hospital_reg_no,h.hos_name,h.hospital_fee,d.doc_id,d.docname,d.docfee from hospital h,doctor d"
					+ "order by h.hospital_reg_no";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// iterate through the results
			while (resultSet.next()) {
				String hospital_reg_no = resultSet.getString(1);
				String hos_name = resultSet.getString(2);
				String hospital_fee = Double.toString(resultSet.getDouble(3));
				String Docid = resultSet.getString(4);
				String DocName = resultSet.getString(5);
				String Docfee = Double.toString(resultSet.getDouble(6));
				
				// add to html table
				output += "<tr><td>" + hospital_reg_no + "</td>";
				output += "<td>" + hos_name + "</td>";
				output += "<td>" + hospital_fee + "</td>";
				output += "<td>" + Docid + "</td>";
				output += "<td>" + DocName + "</td>";
				output += "<td>" + Docfee + "</td>";
			}

			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}*/
	
}
