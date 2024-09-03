<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" />
<title>TopNews</title>
</head>
<style>
.news-container {
	width: 85%;
	max-width: 900px;
	background-color: transparent;
	box-shadow: none;
	padding: 25px;
	box-sizing: border-box;
	border-radius: 10px;
	margin: 0 auto;
}

.news-block {
	margin: 25px 0;
	background-color: #fafafa;
	padding: 20px;
	position: relative;
	transition: box-shadow 0.3s ease;
	width: 100%;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	overflow: hidden;
}

.news-block:hover {
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}

.news-block .category {
	position: absolute;
	top: 15px;
	right: 15px;
	background-color: #0066cc;
	color: #fff;
	padding: 7px 15px;
	font-size: 12px;
	border-radius: 15px;
}

.news-block h2 {
	margin-top: 0;
	font-size: 24px;
	color: #0066cc;
}

.news-block img {
	max-width: 100%;
	height: auto;
	margin-bottom: 15px;
	border-radius: 8px;
	background-color: #e6e6e6;
}

.article-content p {
	font-size: 16px;
	line-height: 1.7;
	margin-bottom: 15px;
}

.article-content .read-more {
	display: inline-block;
	margin-top: 15px;
	color: #0066cc;
	text-decoration: none;
	font-weight: bold;
	border-bottom: 2px solid transparent;
	transition: border-bottom-color 0.3s ease;
}

.article-content .read-more:hover {
	border-bottom-color: #0066cc;
}

.news-block::before {
	content: '';
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: linear-gradient(to bottom, rgba(255, 255, 255, 0.5) 0%,
		rgba(255, 255, 255, 0) 100%);
	z-index: 1;
	border-radius: 8px;
}
</style>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<main>
		<div class="news-container">
			<section class="news-block">
				<div class="article-content">
					<img src="<c:url value='/resources/images/${article.imgPath}'/>"
						alt="Image">
					<p class="category">${article.category}</p>
					<p>${article.content}</p>
				</div>
			</section>
		</div>
	</main>
</body>
</html>
