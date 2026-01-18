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
<title>登録確認画面</title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
  <p>これでよろしいですか？</p>

  <c:if test="${not empty errMsg}">
    <p class="error">${fn:escapeXml(errMsg)}</p>
  </c:if>

  <form:form action="insert" method="post" modelAttribute="insertForm">
    <fieldset class="label-130">
      <div>
        <label>ID</label>
        <form:input path="loginId" readonly="true" />
      </div>
      <div>
        <label>名前</label>
        <form:input path="userName" readonly="true" />
      </div>
      <div>
        <label>TEL</label>
        <form:input path="tel" readonly="true" />
      </div>
      <div>
        <label>権限</label>
        <form:input path="roleName" readonly="true" />
      </div>
      <div>
        <label>PASS（再入力）</label>
        <form:password path="confirmPassword" />
      </div>
    </fieldset>
      <form:button name="insert">
        <fmt:message key="btn.insert" />
      </form:button>
      <form:button name="back">
        <fmt:message key="btn.back" />
      </form:button>
  </form:form>
  <div>
    <a href="menu">メニューに戻る</a>
  </div>
</body>
</html>
