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
<title>編集</title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
	<header>
		<nav>
			<ul class="header-flex">
				<li><span><fmt:message key="title.system" /></span></li>
				<li><a href="/students/menu"><fmt:message key="btn.menu" /></a>
				</li>
				<li><span>${fn:escapeXml(sessionInfo.loginUser.roleName)}</span>
				</li>
				<li><span>${fn:escapeXml(sessionInfo.loginUser.dispName)}</span>
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
		編集
	</h1>

	<p>
		<span class="required"></span>は必須です
	</p>

	<form:form action="/students/updateResult" method="post"
		modelAttribute="updateForm">
		<fieldset class="label-60">
			<div>
				<label class="required"><fmt:message key="lbl.student.name" /></label>
				<form:input path="upstudentName" />
				<form:errors path="upstudentName" class="error" />
			</div>
			<div>
				<label class="required"><fmt:message key="lbl.grade" /></label>
				<form:input path="upgrade" />
				<form:errors path="upgrade" class="error" />
			</div>
			<div>
				<label>出身</label>
				<form:input path="hometown" />
			</div>
			<div>
			<label>専攻</label>
				<form:select path="major">
					<form:options items="${majorList}" itemLabel="majorName"
						itemValue="majorId" />
				</form:select>
			</div>
		</fieldset>
		<form:button name="back" >
			戻る
		</form:button>
		<form:button name="edit" >
			確定
		</form:button>
	</form:form>
</body>
</html>
