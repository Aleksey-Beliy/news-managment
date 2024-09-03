<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><spring:message code="local.auth.enter.site" />
	${category}</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>">
<style>
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
	<jsp:include page="header.jsp"></jsp:include>

	<div class="news-container">

		<c:if test="${empty listNews}">
			<p class="no-articles">
				<spring:message code="local.auth.enter.site" />
			</p>
		</c:if>

		<c:if test="${not empty listNews}">
			<div class="news-container">
				<c:forEach var="news" items="${listNews}">
					<section class="news-block">
						<div class="article-content">
							<h2>
								<img src="<c:url value='/resources/images/${news.imgPath}'/>"
									alt="Image">
							</h2>
							<h2 class="news-title">${news.title}</h2>
							<p class="category">
								<strong></strong> ${news.category}
							</p>

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
		</c:if>
	</div>
</body>
</html>
