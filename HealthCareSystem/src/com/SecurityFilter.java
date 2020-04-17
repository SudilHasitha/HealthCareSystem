package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class SecurityFilter implements javax.ws.rs.container.ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORIZATION_PREFIX = "Basic ";
	private static final String URL_PREFIX_ADMIN = "Admin";
	private static final String URL_PREFIX_HOSPITAL = "hospital";
	
	// Create DB connection
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

	@Override
	public void filter(ContainerRequestContext requestcontext) throws IOException {

		if (requestcontext.getUriInfo().getPath().contains(URL_PREFIX_ADMIN)) {
			List<String> authHeader = requestcontext.getHeaders().get(AUTHORIZATION_HEADER);

			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_PREFIX, "");

				String decodeString = new String(Base64.getDecoder().decode(authToken));

				StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");

				String userName = tokenizer.nextToken();
				String password = tokenizer.nextToken();

				
				// find whether user in the DB
				Connection con = connect();

				try {
					String query = "select * from payments.users where Password = " + password;
					Statement statement = con.createStatement();
					ResultSet resultSet = statement.executeQuery(query);
					
					System.out.println("Header password "+password);
					while (resultSet.next()) {

						String Password = Integer.toString(resultSet.getInt(1));
						System.out.println("DB password "+Password);
						if (password.equalsIgnoreCase(Password)) {
							return;
						}
					}
					con.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			// if authentication fails
			Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
					.entity("User cannot access the responce.").build();

			requestcontext.abortWith(unauthorizedStatus);
		}
	}
}
