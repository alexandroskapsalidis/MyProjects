<!-- THE ADD STUDENT FORM -->

<!--  -->

<!Doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="author" content="Alex">
	<meta name="description" content="Add Student">
	<title>Add Student</title>
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
		<h3>Add Student</h3>
		<!-- When the form data is submited it will call the method on doGet()  -->
		<form action="StudentControllerServlet" method="GET">
			<!-- We put a hidden field to use it to the Servlet -->
			<input type="hidden" name="command" value="ADD">
			<table>
				<tr>
					<td><label>First Name:</label></td>
					<td><input type="text" name="firstName"></td>
				</tr>
				<tr>
					<td><label>Last Name:</label></td>
					<td><input type="text" name="lastName"></td>
				</tr>
				<tr>
					<td><label>Email:</label></td>
					<td><input type="text" name="email"></td>
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