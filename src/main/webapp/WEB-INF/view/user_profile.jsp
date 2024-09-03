<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" />
<title>Профиль пользователя</title>

<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	background-color: #f5f5f5;
}

.container {
	max-width: 900px;
	padding: 20px;
	background-color: #fff;
	border-radius: 12px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	position: relative;
}

.btn-logout {
	display: inline-block;
	padding: 12px 20px;
	font-size: 1rem;
	color: #fff;
	background-color: #dc3545;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	position: absolute;
	top: 20px;
	right: 20px;
	transition: background-color 0.3s ease;
}

.btn-logout:hover {
	background-color: #c82333;
}

h2, h3 {
	color: #333;
}

.profile-info p {
	font-size: 1rem;
	color: #555;
	margin: 10px 0;
}

.profile-info strong {
	font-weight: bold;
}

.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	font-size: 0.9rem;
	color: #333;
	margin-bottom: 8px;
}

.form-control {
	width: 100%;
	padding: 12px;
	border: 1px solid #ddd;
	border-radius: 8px;
	box-sizing: border-box;
	font-size: 1rem;
	transition: border-color 0.3s ease;
}

.form-control.error {
	border-color: #dc3545;
}

.form-control:focus {
	border-color: #007bff;
	outline: none;
	box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.25);
}

.error {
	color: #dc3545;
	font-size: 0.8rem;
	margin-top: 5px;
}

.btn-primary {
	display: inline-block;
	padding: 12px 20px;
	font-size: 1rem;
	color: #fff;
	background-color: #007bff;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	transition: background-color 0.3s ease;
}

.btn-primary:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<!-- Кнопка выхода из аккаунта -->
		<form action="<c:url value='/user/logout'/>" method="post">
			<button type="submit" class="btn-logout">Выйти</button>
		</form>

		<h2>
			<spring:message code="local.header.user.profile" />
		</h2>

		<div class="profile-info">
			<p>
				<strong><spring:message code="local.profile.name" />:</strong>
				${user.name}
			</p>
			<p>
				<strong><spring:message code="local.profile.email" />:</strong>
				${user.userData.email}
			</p>
		</div>
		<c:if test="${not empty successMessage}">
			<div class="alert alert-success">${successMessage}</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger">${errorMessage}</div>
		</c:if>

		<h3>
			<spring:message code="local.profile.change.password" />
		</h3>
		<form method="post" action="<c:url value='/user/updatePassword'/>">
			<div class="form-group">
				<label for="currentPassword"><spring:message
						code="local.profile.current.password" />:</label> <input type="password"
					name="currentPassword" id="currentPassword" class="form-control" />
				<span class="error">${errors.currentPassword}</span>
			</div>
			<div class="form-group">
				<label for="newPassword"><spring:message
						code="local.profile.new.password" />:</label> <input type="password"
					name="newPassword" id="newPassword" class="form-control" /> <span
					class="error">${errors.newPassword}</span>
			</div>
			
			<div class="form-group">
				<label for="confirmPassword"><spring:message
						code="local.profile.confirm.password" />:</label> <input type="password"
					name="confirmPassword" id="confirmPassword" class="form-control" />
				<span class="error">${errors.confirmPassword}</span>
			</div>
			<button type="submit" class="btn btn-primary">
				<spring:message code="local.profile.change.password" />
			</button>
		</form>

	</div>
</body>
</html>
