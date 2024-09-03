<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css'/>" />
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }
        .background-box {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 500px;
            height: 600px;
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .container {
            width: 100%;
            max-width: 400px;
            padding: 50px;
            background-color: white;
            border-radius: 7px;
            box-sizing: border-box;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .error {
            color: #ff0000;
            font-size: 0.9em;
            margin-bottom: 15px;
        }
        .btn {
            width: 100%;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>

    <div class="background-box">
        <div class="container">
            <h1 class="text-java text-center">
                <fmt:message key="local.registration.registration.site" />
            </h1>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <form:form action="saveUser" modelAttribute="user" method="post">
                <div class="form-group">
                    <form:input class="form-control" type="email" path="userData.email" placeholder="Email" />
                </div>
                <div class="form-group">
                    <form:input class="form-control" path="name" placeholder="Name" />
                </div>
                <div class="form-group">
                    <form:input class="form-control" type="password" path="userData.password" placeholder="Password" />
                </div>
                <div class="form-group">
                    <form:input class="form-control" type="password" path="userData.confirmPassword" placeholder="Confirm Password" />
                </div>
                <button class="btn btn-lg btn-success" type="submit">
                    <fmt:message key="local.registration.registration" />
                </button>
            </form:form>
        </div>
    </div>
</body>
</html>
