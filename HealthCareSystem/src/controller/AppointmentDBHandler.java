package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AppointmentDBHandler {
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/appointment?autoReconnect=true&useSSL=false", "root", "Pa$$w0rd");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public int getAppointmentID() {
		int id = 0;

		try {
			Connection con = connect();

			String query = "select * from appointment.appointments order by appointment_date DESC Limit 1";
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
