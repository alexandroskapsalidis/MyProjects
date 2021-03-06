<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Insert Student</title>
        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    </head>
    <body>

        <div class="container">

            <form:form action="/insertStudent" modelAttribute="studentFromForm" class="form-signin">
                <h2 class="form-signin-heading">Insert a student</h2>

                <spring:bind path="studentFirstName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="studentFirstName" class="form-control" placeholder="First name"
                                    autofocus="true"></form:input>
                        <form:errors path="studentFirstName"></form:errors>
                        </div>
                </spring:bind>

                <spring:bind path="studentLastName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="studentLastName" class="form-control" placeholder="Last name"
                                    autofocus="true"></form:input>
                        <form:errors path="studentLastName"></form:errors>
                        </div>
                </spring:bind>


                <spring:bind path="studentLevel">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="studentLevel" class="form-control" placeholder="Student's level"
                                    autofocus="true"></form:input>
                        <form:errors path="studentLevel"></form:errors>
                        </div>
                </spring:bind>

                <spring:bind path="streetName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="streetName" class="form-control" placeholder="Street name"></form:input>
                        <form:errors path="streetName"></form:errors>
                        </div>
                </spring:bind>

                <spring:bind path="addressNumber">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="number" path="addressNumber" class="form-control"
                                    placeholder="Number"></form:input>
                        <form:errors path="addressNumber"></form:errors>
                        </div>
                </spring:bind>
                <spring:bind path="addressArea">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="addressArea" class="form-control"
                                    placeholder="Area"></form:input>
                        <form:errors path="addressArea"></form:errors>
                        </div>
                </spring:bind>
                <spring:bind path="addressTK">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="number" path="addressTK" class="form-control"
                                    placeholder="Zip code"></form:input>
                        <form:errors path="addressTK"></form:errors>
                        </div>
                </spring:bind>
                <spring:bind path="phone1">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="phone1" class="form-control"
                                    placeholder="Phone 1"></form:input>
                        <form:errors path="phone1"></form:errors>
                        </div>
                </spring:bind>
                <spring:bind path="phone2">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="phone2" class="form-control"
                                    placeholder="Phone 2"></form:input>
                        <form:errors path="phone2"></form:errors>
                        </div>
                </spring:bind>
                <spring:bind path="parentName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="parentName" class="form-control"
                                    placeholder="Parent's name"></form:input>
                        <form:errors path="parentName"></form:errors>
                        </div>
                </spring:bind>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form:form>

        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    </body>
</html>
