<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title.pets.search" /></title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
	<header>
		<nav>
			<ul class="header-flex">
				<li><span><fmt:message key="title.system" /></span></li>
				<li><a href="/pets/menu"><fmt:message key="btn.menu" /></a></li>
				<li>
					<p>${sessionInfo.user.dispName}</p>
				</li>
				<li>
					<form action="/logout" method="post">
						<button type="submit">
							<fmt:message key="btn.logout" />
						</button>
					</form>
				</li>
			</ul>
		</nav>
	</header>

	<h1>
		<fmt:message key="title.pets.search" />
	</h1>

	<c:if test="${not empty errMsg}">
		<p class="error">${fn:escapeXml(errMsg)}</p>
	</c:if>

	<form:form action="/pets/searchResult" modelAttribute="searchForm"
		method="get">
		<fieldset>
			<div>
				<label> <fmt:message key="lbl.pet.name" />
				</label>
				<form:input path="name" />
			</div>
			<div>
				<label>種類</label>
				<form:select path="animalTypeId">
					<form:options items="${sessionInfo.animalTypes}" itemLabel="name"
						itemValue="id" />
				</form:select>
			</div>
		</fieldset>
		<form:button>
			<fmt:message key="btn.search" />
		</form:button>
	</form:form>
</body>
</html>
