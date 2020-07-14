<%-- 
    Document   : adminpage
    Created on : 26 Απρ 2020, 12:14:01 πμ
    Author     : ioann
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">



<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>Work Diary</title>
</head>

<style>
    body {
        font-family: Arial, Helvetica, sans-serif;
        background-color: rgb(230, 225, 225);

    }

    header {
        padding: 5px;
       

    }

    #headerdiv {
        padding: 10px;
    }

    table {
        border-collapse: collapse;
        width: 100%;
    }

    table,
    td,
    th {
        border: solid grey 1px;
        padding: 3px;
        text-align: center;
    }

    th {
        background-color: rgb(192, 188, 188);
    }

    td {

        background-color: rgb(255, 250, 250);
    }

    #searchbar {
   
  width: 100%; /* Full-width */
  font-size: 16px; /* Increase font-size */
  padding: 12px 20px 12px 40px; /* Add some padding */
  border: 1px solid #ddd; /* Add a grey border */
  margin-bottom: 12px; /* Add some space below the input */

    }
</style>


<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <!-- header -->
    <header>



  <c:if test="${pageContext.request.userPrincipal.name != null}">
                                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>  
                                </c:if>
        

               
        


        <div class="row row-no-gutters">
            <div class="col-10">
                <div id="headerdiv">
                    <h2> Welcome ${pageContext.request.userPrincipal.name}</h2>
                </div>
            </div>
            <div class="col-2">
                <div id="topmenu">
                    <a onclick="document.forms['logoutForm'].submit()">Logout</a><span></span>
                    <a id="myaccount" href="">My Account</a> <span><i class="far fa-address-card"></i></span>
                </div>
            </div>
        </div>

    </header>
    <hr>

    <div class="row row-no-gutters">
        <div class="col-3"></div>
        <div class="col-6" id="searchdiv">

            <input type="text" placeholder="Search..." id="myInput" onkeyup="myFunction()">


        </div>
        <div class="col3"></div>
    </div>




    <div class="row row-no-gutters">

        <div class="col-2"></div>
        <div class="col-8">
            <h4>Users</h4>
            <hr>
            <div class="studenttable">
                <table id="myTable">
                    <thead>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>email</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </thead>
                    <tbody>
                        <c:forEach var="st" items="${list}">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.firstname}</td>
                            <td>${u.lastname}</td>
                            <td>${u.username}</td>
                            <td>${u.password}</td>
                            <td>${u.email}</td>
                            <td>
                                <div style="text-align: center;" class="col-md-4">
                                    <a href="/edit/${u.id}" target="_blank">
                                        <button id="myBtn2" class="btn btn-secondary" type="submit">Updade</button>
                                    </a>

                                </div>
                            </td>
                            <td>
                                <div style="text-align: right" class="col-md-4">
                                    <a href="/delete/${u.id}" target="_blank">
                                        <button id="myBtn3" class="btn btn-secondary" type="submit">Delete</button>
                                    </a>

                                </div>

                            </td>
                        </tr>
<c:forEach var="st" items="${list}">
                    </tbody>
                </table>

            </div>


        </div>
        <div class="col-2"></div>
    </div>


    <!-- -->
    <footer>

    </footer>




</body>

</html>

