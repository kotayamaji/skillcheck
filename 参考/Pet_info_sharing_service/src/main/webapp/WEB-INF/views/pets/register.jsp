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
<title>登録画面</title>
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

	<h1>登録</h1>
	<p>
		<span class="required"></span>は必須です
	</p>
	<form:form action="/pets/register" method="post"
		modelAttribute="register">
		<div>
			<p>
				<label class="required"> 名前</label>
				<form:input path="name" />
				<form:errors path="name" class="error" />
			</p>
			<p>
				種類
				<form:select path="animalTypeId">
					<form:options items="${sessionInfo.animalTypes }" itemLabel="name"
						itemValue="id" />
				</form:select>
			</p>
			<p>
				説明
				<form:textarea path="description" />
			</p>
		</div>
		<form:button>登録</form:button>
	</form:form>
</body>
</html>