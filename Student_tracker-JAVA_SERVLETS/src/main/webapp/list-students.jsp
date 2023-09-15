<!-- VIEW -->

<!-- At the first edition we used scriplets but we will update it with JSTL
	 - I add the JSTL jar files in the WEB-INF/lib
	 - Make modifications to this JSP file to use JSTL
	 - We remove the page import, when we use JSTL we don't need any imports
	   of Java class libraries.
	 - Add the reference taglib for core
	 - Delete the scriplet where we retrieve the object from the request attribute
	   We don't need it, JSTL will do a lot of these works behind the scenes
	 - Replace the for loop with JSTL forEach-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!Doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Alex">
<meta name="description" content="MVC Demo">
<title>Student Tracker App</title>

<!-- USING CSS 
	 We make a new folder => css, under webapp. Because JSP is just like
     HTML, so I put a link tag here, linking it to my css -->
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>

<body>

	<!-- I use divs to apply CSS styles -->

	<div id="wraper">
		<div id="header">
			<h2>Foo University</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<!-- Put new button and tell the system to show the html adding form-->
			<input type="button" value="Add Student"
				onclick="window.location.href='add-student-form.jsp'"
				class="add-student-button">
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- At items we use the same attr. name set by the servlet -->
				<c:forEach var="tempStudent" items="${student_list}">

					<!-- Set up a link for each student to use in updating.
					 	 I'll use the JSTL url which defines
						 a link to a url and we can also pass some parameters to it. I have 
						 two parameters that I want to send to the StudentControllerServlet.
						 The first is the command, the next is the current student id. In
						 effect it will create multiple links for each student with their
						 embeded ID already there -->
					<c:url var="tempLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD"/>
						<c:param name="studentId" value="${tempStudent.id}"/>
					</c:url>
					
					<!-- Set up a link to delete a student -->
					<c:url var="deleteLink" value="StudentControllerServlet">
						<c:param name="command" value="DELETE"/>
						<c:param name="studentId" value="${tempStudent.id}"/>
					</c:url>					
					
					<tr>
						<!-- Calling the getter methods with JSP expression language -->
						<td>${tempStudent.firstName}</td>
						<td>${tempStudent.lastName}</td>
						<td>${tempStudent.email}</td>
												
						<td>
							<!-- Adding an update button -->
							<!-- href points to the tempLink above which has a command and the ID-->
							<a href="${tempLink}">Update</a>
							
							<!-- Adding a delete button. Prompting before deleting-->
							 |							
							<a href="${deleteLink}"
							onclick = "if (!(confirm('Are you sure you want to delete this student?'))) return false">
							Delete</a>
						</td>
					</tr>
				
				</c:forEach>

			</table>

		</div>
	</div>

</body>
</html>