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
	
	public String HospitalInsert(String hospital_reg_no,
			 String hos_name,
			 String hos_type,
			 String AddressLine1,
			 String city,
			 String province,
			 String telephone,
			 String hospital_fee,
			 String hos_password) {
		
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "Insert into hospital (hospital_reg_no,hos_name,hos_type,"
					+ "AddressLine1,city,province,telephone,hospital_fee,hos_password) "
					+ "values (?,?,?,?,?,?,?,?,?) ";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, hospital_reg_no);
			preparedStatement.setString(2, hos_name);
			preparedStatement.setString(3, hos_type);
			preparedStatement.setString(4, AddressLine1);
			preparedStatement.setString(5, city);
			preparedStatement.setString(6, province);
			preparedStatement.setString(7, telephone);
			preparedStatement.setDouble(8, Double.parseDouble(hospital_fee));
			preparedStatement.setString(9, hos_password);
			

			// execution
			preparedStatement.execute();
			con.close();

			output = "Inserted Successfully";

		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;

	}
	
	public String getHosID(String reg_no) {
		
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}
			
			String query = "Select hos_id from hospital WHERE hospital_reg_no =?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(1, reg_no);
			
			ResultSet rs = preparedStmt.executeQuery(query);
			
			String hos_id = Integer.toString(rs.getInt("hos_id"));
				
			output =hos_id;
				
			con.close();
			}
			catch (Exception e)
			{
				output = "Error while reading the Hos_id.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	
	public String updateHos(String hospital_reg_no,	String hos_name,String hos_type,String AddressLine1,String city, String province, String telephone,String hospital_fee) 
	{

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "UPDATE hospital SET hos_name=?,hos_type=?,AddressLine1=?,city=?,province=?,telephone=?,hospital_fee=?"
					+ " WHERE hospital_reg_no =?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, hos_name);
			preparedStatement.setString(2, hos_type);
			preparedStatement.setString(3, AddressLine1);
			preparedStatement.setString(4, city);
			preparedStatement.setString(5, province);
			preparedStatement.setString(6, telephone);
			preparedStatement.setDouble(7, Double.parseDouble(hospital_fee));
			preparedStatement.setString(8, hospital_reg_no);
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
	
	public String updateHosPassword(String hospital_reg_no,String password) 
	{

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to DB";
			}

			// prepared statement
			String query = "UPDATE hospital SET hos_password=?"
					+ " WHERE hospital_reg_no =?";

			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, hospital_reg_no);
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
	
	public String deleteHospital(String ID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			// create a prepared statement
			String query = "delete from hospital where hos_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the item.";
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
					+ "<th>Addressline</th><th>city</th><th>province</th><th>Contact</th><th>Fee</th><th>Update</th><th>Remove</th></tr>";
			
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

				
				// add to html table
				output += "<tr><td>" + hospital_reg_no + "</td>";
				output += "<td>" + hos_name + "</td>";
				output += "<td>" + type_private + "</td>";
				output += "<td>" + AddressLine1 + "</td>";
				output += "<td>" + city + "</td>";
				output += "<td>" + province + "</td>";
				output += "<td>" + telephone + "</td>";
				output += "<td>" + hospital_fee + "</td>";
		

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
	
	public String readHospital(int id) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting";
			}
			
			String query = "select * from hospital where hos_id = " +id;
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
				
				
				// Prepare the html table to be displayed
				output = "<table><tr><th> Hospital ID</th><td> : </td><td>"+ hos_id + "</td></tr>";
				output += "<tr><th> Hospital hospital_reg_no</th><td> : </td><td>" + hospital_reg_no + "</td></tr>";
				output += "<tr><th> Hospital Name</th><td> : </td><td>" + hos_name + "</td></tr>";
				output += "<tr><th> Hospital Type</th><td> : </td><td>" + type_private + "</td></tr>";
				output += "<tr><th> Hospital Address line 1</th><td> : </td><td>" + AddressLine1 + "</td></tr>";
				output += "<tr><th> City</th><td> : </td><td>" + city + "</td></tr>";
				output += "<tr><th> Province </th><td> : </td><td>" + province + "</td></tr>";
				output += "<tr><th>Telephone</th><td> : </td><td>" + telephone + "</td></tr>";
				output += "<tr><th> Hospital fee</th><td> : </td><td>" + hospital_fee + "</td></tr>";
		
	
			}
			
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
/*	public String readHospitalDoctersDetail(int id) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting";
			}
			
			String query = "select distinct  doctor.docid, hospital.hos_name,doctor.doc_name"
					+ "from hospital "
					+ " left join (visit cross join doctor) "
					+ "on (hospital.hos_id = visit.hosid "
					+ "and visit.docid = doctor.docid )"
					+ "where hospital.hos_id=" +id;
			
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// iterate through the results
			while (resultSet.next()) {
				String hospital_reg_no = resultSet.getString(1);
				String hos_name = resultSet.getString(2);
				String docid =Integer.toString(resultSet.getInt(4));
				String doc_name = resultSet.getString(3);
				
				// Prepare the html table to be displayed
				output = "<table>";
				output += "<tr><th> Hospital Name</th><td> : </td><td>" + hos_name + "</td></tr>";
				output += "</table><table><tr><th>Docter id</th><th> DOcter name</th></tr>";
				output += "<tr><td>" + docid + "</td><td>" + doc_name + "</td></tr>";
	
			}
			
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
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
