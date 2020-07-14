<%-- 
    Document   : allgood
    Created on : Apr 17, 2020, 7:58:26 PM
    Author     : Ioanna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>chat</title>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        
    </head>


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
        
    <noscript>
    <h2>Sorry! Your browser doesn't support Javascript</h2>
</noscript>
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

                    <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
                </c:if>
            </div>

        </div>
        
<div id="username-page">
    <div class="username-page-container">
        <h1 class="title">Type your username</h1>
        <form id="usernameForm" name="usernameForm">
            <div class="form-group">
                <input type="text" id="name" placeholder="Username" autocomplete="off" class="form-control" />
            </div>
            <div class="form-group">
                <button type="submit" class="accent username-submit">Start Chatting</button>
            </div>
        </form>
    </div>
</div>

<div id="chat-page" class="hidden">
    <div class="chat-container">
        <div class="chat-header">
            <h2>Spring WebSocket Chat Demo</h2>
        </div>
        <div class="connecting">
            Connecting...
        </div>
        <ul id="messageArea">

        </ul>
        <form id="messageForm" name="messageForm" nameForm="messageForm">
            <div class="form-group">
                <div class="input-group clearfix">
                    <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                    <button type="submit" class="primary">Send</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/js/main.js"></script>
    </body>
</html>
