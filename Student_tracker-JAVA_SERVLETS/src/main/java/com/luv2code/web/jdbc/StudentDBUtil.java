package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {

	// Setting a reference to our DataSource
	private DataSource dataSource;

	// Constructor
	// Our Servlet will create this StudentDBUtil and will pass a reference to
	// our dataSource object.
	public StudentDBUtil(DataSource thedataSource) {
		dataSource = thedataSource;
	}

	// The main thing here is a method which will return a list of all students
	// from the Database. I place the code inside of the try block and I make
	// use of finally to make use of Best Practices and close the JDBC objects.
	// It's not good to hold open them forever, because we'll eventually have
	// a memory leak and we'll run out of statements, cursors, or connections.
	// Especially in a production application, over time, after some days,
	// or weeks we'll run out of some resources.
	public List<Student> getStudents() throws Exception {

		List<Student> students = new ArrayList<>();

		// Setting up the JDBC objects
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// Get a connection
			myConn = dataSource.getConnection();

			// Create sql statement
			String sql = "SELECT * FROM student ORDER BY last_name";

			myStmt = myConn.createStatement();

			// Execute query
			myRs = myStmt.executeQuery(sql);

			// Process result set
			// We use this loop to keep processing a result set and adding the data we get
			// to our list
			while (myRs.next()) {

				// Retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// Create new Student object
				// I use the data for the row, I retrieved, to pass to the constructor
				Student tempStudent = new Student(id, firstName, lastName, email);

				// Add it to the list of students
				students.add(tempStudent);

//				System.out.println(id + " " + firstName + " " + lastName + " " + email);

			}

			return students;

		} finally {

			// close JDBC objects
			// I create a method bellow to do the closings.
			close(myConn, myStmt, myRs);
		}

	}

	// Adding new student
	public void addStudent(Student theStudent) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {

			// Get DB connection
			myConn = dataSource.getConnection();

			// Create SQL for insert
			// For values I'll make use of ? placeholders because I'm using
			// Prepared Statements. We will fill those with data
			String sql = "INSERT INTO student" + "(first_name, last_name, email)" + "VALUES (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);

			// Set the param values for the student
			// The parameter values for prepared statements are 1 based not zero
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());

			// Execute SQL insert
			myStmt.execute();

		} finally {

			// Clean up JDBC objects
			// The null is for resultSet, we don't have one
			close(myConn, myStmt, null);
		}

	}

	// Getting the student (Update feature)
	public Student getStudent(String theStudentId) throws Exception {

		Student theStudent = null;

		// Define JDBC Objects
		Connection myCon = null;
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		int studentId;

		try {
			// Convert student id to int
			studentId = Integer.parseInt(theStudentId);

			// Get connection to database
			myCon = dataSource.getConnection();

			// Create sql to get selected student
			String sql = "SELECT * FROM student WHERE id = ?";

			// Create prepared statement
			myStm = myCon.prepareStatement(sql);

			// Set params
			myStm.setInt(1, studentId);

			// Execute statement
			myRs = myStm.executeQuery();

			// Retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// Creating new Student using studentId in the constructor
				theStudent = new Student(studentId, firstName, lastName, email);
			} else {
				throw new Exception("Could not find student id: " + studentId);
			}

			return theStudent;

		} finally {
			// Clean up JDBC objects
			close(myCon, myStm, null);
		}

	}

	// Updating a student
	public void updateStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "UPDATE student "
						+ "SET first_name=?, last_name=?, email=? "
						+ "WHERE id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			
			myStmt.setInt(4, theStudent.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			
			close(myConn, myStmt, null);
		}
	}
	
	// Deleting a student
	public void deleteStudent(String theStudentId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			// Convert student id to int
			int studentId = Integer.parseInt(theStudentId);
			
			// Get connection to database
			myConn = dataSource.getConnection();
			
			// Create sql to delete student
			String sql = "DELETE FROM student WHERE id = ?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1,  studentId);
			
			// execute SQL statement
			myStmt.execute();
			
		} finally {
			
			// clean up JDBC code
			close(myConn, myStmt, null);
			
		}
		
		
	}
	
	// I close inside the try block, each of those objects.
	// I first check if they are not null, if there is a connection.
	// One thing about myConn.close() is that it doesn't close the
	// database connection, all it does is put it back into the connection pool,
	// making it, this way, available for someone else to use it.
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myConn != null) {
				myConn.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myRs != null) {
				myRs.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}



}
