<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Редактировать статью</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" />
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	margin: 0;
	padding: 0;
}

.container {
	width: 80%;
	max-width: 1200px;
	margin: 20px auto;
	background: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	color: #333;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
	color: #555;
}

.form-group input, .form-group textarea, .form-group select {
	width: 100%;
	padding: 10px;
	border-radius: 4px;
	border: 1px solid #ddd;
	box-sizing: border-box;
}

.form-group textarea {
	height: 200px;
	resize: vertical;
}

.category-scroll {
	overflow-y: auto;
	max-height: 150px;
	border: 1px solid #ddd;
	border-radius: 4px;
	padding: 10px;
	background: #fafafa;
}

.category-scroll label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

.form-actions {
	text-align: center;
}

.form-actions button {
	background-color: #000;
	color: #fff;
	border: none;
	padding: 10px 20px;
	font-size: 16px;
	cursor: pointer;
	border-radius: 4px;
}

.form-actions button:hover {
	background-color: #333;
}

.btn-cancel {
	background-color: #dc3545;
	color: #fff;
	text-decoration: none;
	padding: 10px 20px;
	border-radius: 5px;
	display: inline-block;
	margin-top: 10px;
	cursor: pointer;
}

.btn-cancel:hover {
	background-color: #c82333;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<h1>
			<spring:message code="local.article.edit.article" />
		</h1>
		<form:form action="editArticle" modelAttribute="editArticle"
			method="post">
			<form:hidden path="id" />
			<div class="form-group">
				<label for="title"><spring:message code="local.admin.title" /></label>
				<form:input id="title" class="form-control" path="title"
					placeholder="Введите заголовок" required="true" />
			</div>
			<div class="form-group">
				<label for="content"><spring:message
						code="local.admin.content" /></label>
				<form:textarea id="content" class="form-control" path="content"
					placeholder="Введите содержание статьи" required="true" />
			</div>
			<div class="form-group">
				<label for="category"><spring:message
						code="local.admin.category" /></label>
				<div class="category-scroll">
					<form:select path="category" class="form-control" id="category">
						<form:option value="" label="Выберите категорию" />
						<form:option value="Спорт">Спорт</form:option>
						<form:option value="Технологии">Технологии</form:option>
						<form:option value="Экономика">Экономика</form:option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="image"><spring:message code="local.admin.image" /></label>
				<form:input type="file" id="image" class="form-control"
					path="imgPath" />
			</div>
			<div class="form-actions">
				<button type="submit">
					<spring:message code="local.article.save.changes" />
				</button>
			</div>
		</form:form>
	</div>
</body>
</html>
