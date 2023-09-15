<!-- THE ADD STUDENT FORM -->

<!--  -->

<!Doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="author" content="Alex">
	<meta name="description" content="Update Student">
	<title>Update Student</title>
	<link  rel="stylesheet" href="css/styles.css">
	<!-- We link also another stylesheet specific for this form -->
	<link  rel="stylesheet" href="css/add-student-style.css">
	
</head>

<body>
	
	<div id="wraper">
		<div id="header">
			<h2>Foo University</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Student</h3>
		
		<form action="StudentControllerServlet" method="GET">
		
			<!-- We put a hidden field to use it to the Servlet -->
			<input type="hidden" name="command" value="UPDATE" />
						
			<!-- We need a new hidden field, to track that student id when we make an update
			     and we send it over to the servlet. The Servlet knows which student id to 
			     perform an update for -->
			<input type="hidden" name="studentId" value="${THE_STUDENT.id}" />
			
			<table>
				<tr>
					<td><label>First Name:</label></td>
					<!-- We add a value here to pre-populate the field with student's first name
					     THE_STUDENT comes from StudentControllerServlet, loadStudents()  -->
					<td><input type="text" name="firstName" value="${THE_STUDENT.firstName}"></td>
				</tr>
				<tr>
					<td><label>Last Name:</label></td>
					<td><input type="text" name="lastName" value="${THE_STUDENT.lastName}"></td>
				</tr>
				<tr>
					<td><label>Email:</label></td>
					<td><input type="text" name="email" value="${THE_STUDENT.email}"></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save"></td>
				</tr>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		<p><a href="StudentControllerServlet">Back to List</a></p>
	</div>
	 
</body>
</html>