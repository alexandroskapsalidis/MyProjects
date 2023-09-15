// THE CONTROLLER

// When creating the servlet we uncheck the item for constructor and the doPost.

package com.luv2code.web.jdbc;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// We set a reference to the StudentDBUtil
	private StudentDBUtil studentDBUtil;

	// I make use of the Java EE resource injection, Tomcat server will inject
	// the connection pool object and assign it to this variable dataSource.
	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	// Now we want to initialize this studentDBUtil. In the servlet life cycle
	// there is a special method that you can override called init(). This method
	// will be called by the app server (Tomcat) when this servlet is
	// initialized/loaded.
	// I go to Source -> Override/Implement Methods -> GenericServlet -> init()
	// This method is inherited from the parent GenericServlet. It's part of the
	// API.
	// This is a good place to put your own custom initialization work.
	// So work that you would normally put on a constructor, when you're working
	// with servlets, you put it in here.
	@Override
	public void init() throws ServletException {
		super.init();

		// Create an instance of StudentDBUtil and pass in the connection
		// pool/dataSource.
		// The constructor wants that dataSource, which is the
		// resource injection item / our connection pool.
		try {
			studentDBUtil = new StudentDBUtil(dataSource);
		} catch (Exception ex) {
			// Maybe for some reason our database wasn't up and running or
			// occur some permission's problem we'll throw this ServletException
			// and it will show it in the browser.
			throw new ServletException(ex);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// At first we only list the students but to do other handling, we have to read
			// the command from JSP form, and route the code accordingly

			// Read the "command parameter"
			String theCommand = request.getParameter("command");

			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}

			// Route to the appropriate method
			switch (theCommand) {
			case "LIST":
				listStudents(request, response);
				break;
			case "ADD":
				addStudent(request, response);
				break;
			case "LOAD":
				loadStudent(request, response);
				break;
			case "UPDATE":
				updateStudent(request, response);
				break;
			case "DELETE":
				deleteStudent(request, response);
				break;
			default:
				listStudents(request, response);

			}

		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}



	// List the students .. in MVC fashion.
	// Get the data, set the attribute, and use the request dispatcher and
	// send it over to the JSP. I'll define a helper method to do this for me
	// and I'll pass the request and response objects. We do this to have some
	// clean code, some Best Practices. The listStudents() throws exception so
	// I use a try catch block.
	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Get students from DB Util
		List<Student> students = studentDBUtil.getStudents();

		// Add students to the request
		request.setAttribute("student_list", students);

		// Send to the JSP page (View)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);

		// I have to handle some Exceptions here. Instead of handling it locally in the
		// method, I will throw it to the calling program so they can handle it in one
		// central location.
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		// Create a new student object
		// We will use the information above to create it.
		Student theStudent = new Student(firstName, lastName, email);

		// Add the student to the database
		studentDBUtil.addStudent(theStudent);

		// Send back to main page (the student list)
		// We have to handle the exception. We throw the Exception.
		listStudents(request, response);
	}

	// Read the LOAD command and route it accordingly
	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Read student id from form data
		String theStudentId = request.getParameter("studentId");

		// Get student from database (db util)
		Student theStudent = studentDBUtil.getStudent(theStudentId);

		// Place student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);

		// Send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Read student info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		// Create a new student object
		Student theStudent = new Student(id, firstName, lastName, email);

		// Perform update on database
		studentDBUtil.updateStudent(theStudent);

		// Send them back to the "list students" page
		listStudents(request, response);

	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Read student id from form data
		String theStudentId = request.getParameter("studentId");
		
		// Delete student from database
		studentDBUtil.deleteStudent(theStudentId);
		
		// Send them back to "list students" page
		listStudents(request, response);
		
	}

}
