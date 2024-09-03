<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<header
	style="display: flex; justify-content: space-between; align-items: center; padding: 10px;">
	<!-- User icon and login button -->
	<div style="display: flex; align-items: center;">
		<a href="<c:url value='/user/profile'/>"
			style="display: flex; align-items: center; text-decoration: none;">
			<img src="<c:url value='/resources/images/icon.svg'/>"
			alt="User Icon"
			style="width: 20px; height: 20px; vertical-align: middle;" /> <span
			style="margin-left: 5px; color: black;"><spring:message
					code="local.header.profile" /></span>
		</a>
	</div>

	<!-- Localization and Navigation -->
	<div style="flex-grow: 1; text-align: center;">
		<div>
			<a
				href="${pageContext.request.contextPath}/general/changeLocale?lang=en">EN</a>
			<a
				href="${pageContext.request.contextPath}/general/changeLocale?lang=ru">RU</a>

		</div>


		<nav>
			<ul
				style="list-style-type: none; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center;">
				<li><a href="<c:url value='/news/startPage'/>"><spring:message
							code="local.header.news" /></a></li>
				<li><a
					href="<c:url value='/news/showArticleFromCategory?category=Спорт'/>"><spring:message
							code="local.footer.sport" /></a></li>
				<li><a
					href="<c:url value='/news/showArticleFromCategory?category=Технологии'/>"><spring:message
							code="local.footer.technology" /></a></li>
				<li><a
					href="<c:url value='/news/showArticleFromCategory?category=Экономика'/>"><spring:message
							code="local.footer.economy" /></a></li>
				<c:if
					test="${sessionScope.user.userRole.title == 'admin' or sessionScope.user.userRole.title == 'moderator'}">
					<li><a href="<c:url value='/news/goToAddArticle'/>"><spring:message
								code="local.admin.add" /></a></li>
				</c:if>
			</ul>
		</nav>
	</div>
</header>
