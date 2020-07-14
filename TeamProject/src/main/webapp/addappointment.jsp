

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Appointmemt</title>
        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">

            <form:form action="/addappointment" modelAttribute="appointmentForm" class="form-signin">
                <h2 class="form-signin-heading">Insert an appointment</h2>

                <spring:bind path="title">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="title" class="form-control" placeholder="title"
                                    autofocus="true"></form:input>
                        <form:errors path="title"></form:errors>
                        </div>
                </spring:bind>
                <spring:bind path="day">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="date" path="day" class="form-control" placeholder="date"
                                    autofocus="true"></form:input>
                        <form:errors path="day"></form:errors>
                        </div>
                </spring:bind>

                <spring:bind path="startTime">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="time" path="startTime" class="form-control" placeholder="startTime"
                                    autofocus="true"></form:input>
                        <form:errors path="startTime"></form:errors>
                        </div>
                </spring:bind>
                <spring:bind path="endTime">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="time" path="endTime" class="form-control" placeholder="endTime"></form:input>
                        <form:errors path="endTime"></form:errors>
                        </div>
                </spring:bind>

                <spring:bind path="amount">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="number" path="amount" class="form-control"
                                    placeholder="amount"></form:input>
                        <form:errors path="amount"></form:errors>
                        </div>
                </spring:bind>


                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form:form>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    </body>
</html>

