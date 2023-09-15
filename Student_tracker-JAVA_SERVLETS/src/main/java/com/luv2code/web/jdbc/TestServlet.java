package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Define DataSource/Connection Pool for Resource Injection.
	// Or in other words get a reference to the connection pool.
	// The Java EE or Tomcat will Inject the connection pool for us.
	// This is done by a special annotation "@Resource(...)" giving
	// the name we used in the context.xml. And then I give a field
	// for the data members, a Datasource obj., which is the
	// handler/reference to our connection pool. Be careful
	// we import javax.sql.DataSource.
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Step 1. Set up the printWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain"); // plain, no html here
		
		// Step 2. Get a connection to the database
		// We set up some JDBC objects and set them to null
		// in order to make use of them later in the code
		// To do all the imports Source->Organize Imports
		Connection myCon = null;
		Statement myStm = null;
		ResultSet myRs = null;
		
		try {

			// Getting a connection from the connection pool
			// DataSource is the same as connection pool
			myCon = dataSource.getConnection();

			// Step 3. Create a SQL statement
			String sql = "SELECT * FROM student";
			// Creating a sql statement object
			myStm = myCon.createStatement();
			
			// Step 4. Execute the SQL statement
			// Which gives us a ResutlSet
			myRs = myStm.executeQuery(sql);
			
			// Step 5. Process the result set
			// by looping though it with a while
			while (myRs.next()) {
				String email = myRs.getString("email");
				out.println(email);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}

}
