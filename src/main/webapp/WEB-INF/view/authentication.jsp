<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
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
            height: 500px;
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
        input[type="checkbox"] {
            margin-right: 5px;
            vertical-align: middle;
        }
        label[for="rememberMe"] {
            vertical-align: middle;
            margin-left: 5px;
        }
        .btn {
            width: 100%;
        }
        .error {
            color: #ff0000;
            font-size: 0.9em;
            margin-bottom: 15px;
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    
    <div class="background-box">
        <div class="container">
            <h1 class="text-java text-center"><spring:message code="local.auth.enter.site"/></h1>
             <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <form:form action="doAuthentication" modelAttribute="userAuth" method="post">
                <div class="form-group">
                    <form:input class="form-control" type="email" path="email" placeholder="Email" required="true" />
                </div>
                <div class="form-group">
                    <form:input class="form-control" type="password" path="password" placeholder="Password" required="true" />
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" name="rememberMe" id="rememberMe" class="form-check-input" />
                    <label for="rememberMe" class="form-check-label"><spring:message code="local.auth.remember.me"/></label>
                </div>
                <button class="btn btn-lg btn-success" type="submit">
                    <spring:message code="local.auth.enter.site"/>
                </button>
                <div class="text-center mt-3">
                    <a href="registration"><spring:message code="local.auth.registration"/></a>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>
