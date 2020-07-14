<%-- 
    Document   : home
    Created on : 24 Φεβ 2020, 11:17:47 πμ
    Author     : Ioanna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Work Diary Index</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <style>
            .main {
                border-collapse: collapse;
                background-color: rgb(146, 155, 155);

            }

            .container{
                /* border-collapse: collapse; */
                background-color:  rgb(183, 189, 209);
                /* padding: 5%; */
                /* margin: 5%; */
            }

            .div1{
                border-collapse: separate;
                padding: 15%;
                margin: 5%;

            }

            .titlebox, .summarybox{
                padding: 5%;
                background-color: rgb(47, 90, 90);
                color: thistle;
                border-collapse: collapse;
                margin: 5%;
                border-radius: 10px;
                text-align: center;
                box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
            }

            #header1{
                color: white;
                font-family: Arial, Helvetica, sans-serif;

            }

            .somebuttons{
                text-align: right;

            }
            #contactButton{
                background-color: rgb(98, 171, 231);
                color: rgb(3, 92, 92);
                margin-top: 20px;
                margin-right: 10px;
            }

            button.register_login{

                width: 40%;
                text-align: center;
                padding: 5px;
                border-radius: 5px;
                height: 30px;

                background-color: white;
                color: black;
                border: 2px solid rgb(53, 151, 146); 

                box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);

            }

            button.register_login:hover {
                background-color: rgb(53, 151, 146); 
                color: white;
            }




        </style>  
    </head>
    <body>
        <div class="main">
            <div class="container">

                <div class="row row-no-gutters">
                    <div col-sm-4></div>

                    <div class="div1">

                        <div col-sm-4>
                            <div class="titlebox">
                                <h1 id="header1">
                                    Work Diary
                                </h1>
                            </div>
                            <div class="summarybox">
                                <p id="summary">
                                    With this web application teachers can organize their time and
                                    finances. Never miss a lesson with your students again!
                                </p>
                            </div>

                            <div style="text-align: center;">
                                <form action="/registration" type="GET">
                                    <button class="register_login" type="submit">Register</button>
                                </form>


                                <!--                            <button class="register_login">Register</button>                     -->


                                <form action="/loginpage" type="GET">
                                    <button class="register_login" type="submit">Login</button>
                                </form>
                                
                            </div>

                        </div>


                    </div>
                    <div col-sm-4></div>
                </div>

                <div class="row row-no-gutters">
                    <div class="col-sm-4">
                        <div class="col-sm-4"></div>
                    </div>

                </div>
                <hr>

                <div class="row-no-gutters">
                    <footer>
                        <div class="col-sm-3"></div>
                        <div class="col-sm-6">
                            <p>
                                <a href="/"><i>Contact us</i></a>
                            </p>
                            <p>
                                <i>Copy Right @Project Team</i>
                            </p>
                        </div>
                        <div class="col-sm-3"></div>
                    </footer>
                </div>



            </div>
        </div>






        <!--   <a href="trainerform">Που πάμε???</a>
           
           <a href="registerform">Go  for Register</a>-->




    </body>
</html>
