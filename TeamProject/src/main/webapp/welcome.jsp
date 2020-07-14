<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Work diary</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">        

        <link href="$/resources/css/bootstrap.min.css" rel="stylesheet">
       
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
      

        <style>
            body {
                font-family: Arial, Helvetica, sans-serif;
                background-color: rgb(230, 225, 225);

            }

            header {
                padding: 5px;
                margin: 10px;
            }

            #headerdiv {
                padding: 15px;
            }

            #topmenu {
                text-align: right;
                float: right;
                text-decoration: none;
                text-decoration-line: none;
            }

            /* menu */
            #menu {

                text-align: left;
                margin-left: 20px;
            }

            #menulist {
                width: 220px;
                float: left;
            }

            #mlist {
                list-style: none;
                padding: 0;
                margin: 0;
            }

            #menulist a {
                display: block;
                background-color: #CCC;
                text-decoration: none;
                padding: 10px;
                color: #000;
            }

            #m1 a {
                background-color: rgb(126, 123, 123);
            }

            #menulist a:hover {
                background-color: #C5C5C5;
            }

            #m1 a:hover {
                background-color: rgb(126, 123, 123);
            }

            #calendar {
                border: solid grey 2px;
                border-radius: 5px;
                width: 700px;
                height: 500px;
                padding: 10px;
                margin-left: 40px;
                overflow: scroll;
                padding: 3px;

            }

            #calendardiv {
                width: 800px;
                height: 600px;
                border: solid 1px grey;
                overflow: scroll;
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
            <li><a href="${contextPath}/chat">Chat</a></li>
        </ul>

        <div class="container">
            <div class="col-4">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>

                    <div class="row row-no-gutters">
                        <div class="col-10">
                            <h2>Welcome ${pageContext.request.userPrincipal.name}  </h2> 
                        </div>
                        <div class="col-2"><a onclick="document.forms['logoutForm'].submit()">Logout</a> </div>
                    </div>
                     
                </c:if>
            </div>

        </div>


        <div class="row row-no-gutters"> 

            <div class="col-1"></div>

            <div class="col-9">
                <div id="calendardiv">

                    <div class="card">
                        <h3 class="card-header" id="monthAndYear"></h3>
                        <table class="table table-bordered table-responsive-sm" id="calendar">
                            <thead>
                                <tr>
                                    <th>Sun</th>
                                    <th>Mon</th>
                                    <th>Tue</th>
                                    <th>Wed</th>
                                    <th>Thu</th>
                                    <th>Fri</th>
                                    <th>Sat</th>
                                </tr>
                            </thead>

                            <tbody id="calendar-body">

                            </tbody>
                        </table>

                        <div class="form-inline">

                            <button class="btn btn-outline-primary col-sm-6" id="previous" onclick="previous()">Previous</button>

                            <button class="btn btn-outline-primary col-sm-6" id="next" onclick="next()">Next</button>
                        </div>
                        <br/>
                        <form class="form-inline">
                            <label class="lead mr-2 ml-2" for="month">Jump To: </label>
                            <select class="form-control col-sm-4" name="month" id="month" onchange="jump()">
                                <option value="0">Jan</option>
                                <option value="1">Feb</option>
                                <option value="2">Mar</option>
                                <option value="3">Apr</option>
                                <option value="4">May</option>
                                <option value="5">Jun</option>
                                <option value="6">Jul</option>
                                <option value="7">Aug</option>
                                <option value="8">Sep</option>
                                <option value="9">Oct</option>
                                <option value="10">Nov</option>
                                <option value="11">Dec</option>
                            </select>


                            <label for="year"></label><select class="form-control col-sm-4" name="year" id="year" onchange="jump()">
                                <option value="1990">1990</option>
                                <option value="1991">1991</option>
                                <option value="1991">1992</option>
                                <option value="1993">1993</option>
                                <option value="1994">1994</option>
                                <option value="1995">1995</option>
                                <option value="1996">1996</option>
                                <option value="1997">1997</option>
                                <option value="1998">1998</option>
                                <option value="1999">1999</option>
                                <option value="2000">2000</option>
                                <option value="2001">2001</option>
                                <option value="2002">2002</option>
                                <option value="2003">2003</option>
                                <option value="2004">2004</option>
                                <option value="2005">2005</option>
                                <option value="2006">2006</option>
                                <option value="2007">2007</option>
                                <option value="2008">2008</option>
                                <option value="2009">2009</option>
                                <option value="2010">2010</option>
                                <option value="2011">2011</option>
                                <option value="2012">2012</option>
                                <option value="2013">2013</option>
                                <option value="2014">2014</option>
                                <option value="2015">2015</option>
                                <option value="2016">2016</option>
                                <option value="2017">2017</option>
                                <option value="2018">2018</option>
                                <option value="2019">2019</option>
                                <option value="2020">2020</option>
                                <option value="2021">2021</option>
                                <option value="2022">2022</option>
                                <option value="2023">2023</option>
                                <option value="2024">2024</option>
                                <option value="2025">2025</option>
                                <option value="2026">2026</option>
                                <option value="2027">2027</option>
                                <option value="2028">2028</option>
                                <option value="2029">2029</option>
                                <option value="2030">2030</option>
                            </select></form>
                    </div>
                </div>


            </div>

            <div class="col-1"></div>

        </div>

        <!-- -->







        <!-- -->
        <footer>

        </footer> 
        
         

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
                integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
                integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
        crossorigin="anonymous"></script>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        
        
        <script>
            
            let today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();
let selectYear = document.getElementById("year");
let selectMonth = document.getElementById("month");

let months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

let monthAndYear = document.getElementById("monthAndYear");
showCalendar(currentMonth, currentYear);





function next() {
    currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
    currentMonth = (currentMonth + 1) % 12;
    showCalendar(currentMonth, currentYear);
}

function previous() {
    currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
    currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
    showCalendar(currentMonth, currentYear);
}

function jump() {
    currentYear = parseInt(selectYear.value);
    currentMonth = parseInt(selectMonth.value);
    showCalendar(currentMonth, currentYear);
}

function showCalendar(month, year) {

    let firstDay = (new Date(year, month)).getDay();
    let daysInMonth = 32 - new Date(year, month, 32).getDate();

    let tbl = document.getElementById("calendar-body"); // body of the calendar

    // clearing all previous cells
    tbl.innerHTML = "";

    // filing data about month and in the page via DOM.
    monthAndYear.innerHTML = months[month] + " " + year;
    selectYear.value = year;
    selectMonth.value = month;

    // creating all cells
    let date = 1;
    for (let i = 0; i < 6; i++) {
        // creates a table row
        let row = document.createElement("tr");

        //creating individual cells, filing them up with data.
        for (let j = 0; j < 7; j++) {
            if (i === 0 && j < firstDay) {
                let cell = document.createElement("td");
                let cellText = document.createTextNode("");
                cell.appendChild(cellText);
                row.appendChild(cell);
            }
            else if (date > daysInMonth) {
                break;
            }

            else {
                let cell = document.createElement("td");
                let cellText = document.createTextNode(date);
                if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                    cell.classList.add("bg-info");
                } // color today's date
                cell.appendChild(cellText);
                row.appendChild(cell);
                date++;
            }


        }

        tbl.appendChild(row); // appending each row into calendar body.
    }

}
            
            
            
        </script>
        

    </body>
</html>

