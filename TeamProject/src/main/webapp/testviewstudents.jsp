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

        </style>

    </head>
    <body>

        <div class="outerClass">
            <div class="container innerClass shadow rounded">                

                <table border = "1" class="table table-bordered" style="margin-top: 10px;"> 
                    <thead style="vertical-align: text-top;">
                        <tr>                       
                            <th colspan="9" style="text-align: center;">
                                <h3><b>Students</b> </h3>                            
                            </th>
                        </tr>
                        <tr>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Level</th>
                            <th>Parent's Name</th>                            
                            <th>Phone</th>
                            <th>Address</th>
                            <th>View Map</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="st" items="${list}">   
                            <tr>                        
                                <td>${st.studentFirstName}</td>  
                                <td>${st.studentLastName}</td> 
                                <td>${st.studentLevel}</td> 
                                <td>${st.parentName}</td> 
                                <td>${st.phone1}</td>                                
                                <td>${st.streetName} ${st.addressNumber}, ${st.addressArea}</td> 
                                <td>
                                    <a href="/viewmap/${st.studentId}">
                                        <button class="btn btn-info" >Map</button>
                                </td>
                                <td>
                                    <a href="/preUpdate/${st.studentId}">
                                        <button class="btn btn-info" >Update</button>
                                </td>
                                <td>
                                    <a href="/delete/${st.studentId}">
                                        <button id="myBtn2" class="btn btn-secondary" 
                                                onclick="showAlert()" style="background-color: rgb(168, 26, 26)">Delete</button>
                                </td>
                            </tr>  
                        </c:forEach> 
                    </tbody>
                </table>  

                <div style="text-align: left; margin:2%">
                    <a href="/preInsertStudent"><button class="btn btn-light" >Add Student</button></a>
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

