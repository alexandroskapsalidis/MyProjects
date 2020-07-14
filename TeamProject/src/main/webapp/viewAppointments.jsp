<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <title>View students</title>

        <style>

            body {
                font-family: sans-serif;
                /*background-image: url('Math_Photos_001.jpg');*/
                background-repeat: no-repeat;
                background-size: cover;                
            }

            .outerClass {
                text-align: center;
            }

            .innerClass {
                display: inline-block; 
                width: 85%;               
                /* background-color: rgba(230, 239, 240, 0.972); */
                margin-top: 5%;
                margin-bottom: 5%;
            }
            
            
                         ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
            }



            li {
                float: left;
            }



            li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }



            li a:hover {
                background-color: #111;
            }

        </style>

    </head>
    <body>
        <ul>
            <li><a class="active" href="#home">Home</a></li>
            <li><a href="${contextPath}/viewstudents">Students</a></li>
            <li><a href="${contextPath}/viewAppointments">Appointments</a></li>
            <li><a href="${contextPath}/viewfiles">Files</a></li>
            <li><a href="${contextPath}/preInsertStudent">Add Students</a></li>
            <li><a href="${contextPath}/preaddappointment">Add Appointments</a>
            <li><a href="/chat">Chat</a></li>
        </ul>

        <div class="container">
            <div class="col-4">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>

                    <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
                </c:if>
            </div>

        </div>
        
        

        <div class="outerClass">
            <div class="container innerClass shadow rounded">                

                <table border = "1" class="table table-bordered" style="margin-top: 10px;"> 
                    <thead style="vertical-align: text-top;">
                        <tr>                       
                            <th colspan="9" style="text-align: center;">
                                <h3><b>Appointments</b> </h3>                            
                            </th>
                        </tr>
                        <tr>
                            <th hidden="true">Id</th>
                            <th>Title</th>
                            <th>Date</th>
                            <th>Start time</th>
                            <th>End time</th>
                            <th>Amount</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="st" items="${list}">   
                            <tr>                        
                                <td hidden="true">${st.appointmentId}</td>  
                                <td>${st.title}</td>  
                                <td>${st.day}</td>  
                                <td>${st.startTime}</td>  
                                <td>${st.endTime}</td>  
                                <td>${st.amount}</td>                                
                                <td>
                                    <a href="deleteappo/${st.appointmentId}"></a>
                                        <button id="myBtn2" class="btn btn-secondary" 
                                                onclick="showAlert()" style="background-color: rgb(168, 26, 26)">Delete</button>
                                </td>
                            </tr>  
                        </c:forEach> 
                    </tbody>
                </table>  

                <div style="text-align: left; margin:2%">
                    <a href="/preaddappointment"><button class="btn btn-light" ><b>Add Appointment</b></button></a>
                </div>

            </div>

        </div>

        <script>

            function showAlert() {
                alert("Are you sure you want to delete a Student")
            }
            ;
        </script>


    </body>
</html>

