<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TopNews</title>
<style>
a {
	pointer-events: auto;
}

.btn-delete {
	display: inline-block;
	padding: 10px 20px;
	background-color: #dc3545;
	color: #fff;
	text-decoration: none;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	position: relative;
	z-index: 10;
}

.btn-edit {
	display: inline-block;
	padding: 10px 20px;
	background-color: #000;
	color: #fff;
	text-decoration: none;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	position: relative;
	z-index: 10;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="news-container">
		<c:forEach var="news" items="${mainNews}">
			<section class="news-block">
				<div class="article-content">
					<h2>
						<img src="<c:url value='/resources/images/${news.imgPath}'/>"
							alt="Image">
					</h2>
					<h2>${news.title}</h2>
					<p class="category">${news.category}</p>
					<c:choose>
						<c:when
							test="${user.userRole.title == 'reader' || user.userRole.title == 'admin' || user.userRole.title == 'moderator'}">
							<p>
								<a href="<c:url value='/news/viewArticle?id=${news.id}'/>"
									class="read-more"> Читать далее </a>
							</p>
						</c:when>
					</c:choose>
					<c:if
						test="${user.userRole.title == 'admin' || user.userRole.title == 'moderator'}">
						<a href="<c:url value='/news/goToEditArticle?id=${news.id}'/>"
							class="btn-edit">Edit</a>
						<a href="<c:url value='/news/deleteArticle?id=${news.id}'/>"
							class="btn-delete">Delete</a>
					</c:if>
				</div>
			</section>
		</c:forEach>
	</div>
</body>
</html>