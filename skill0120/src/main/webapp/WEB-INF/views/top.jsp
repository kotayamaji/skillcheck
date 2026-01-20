<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ</title>
</head>
<body>
  <h2>検索条件または登録情報を入力してください</h2>
  <c:if test="${not empty msg}">
    <p>${msg}</p>
  </c:if>
  <form:form action="execute" modelAttribute="product" method="post">
    <fmt:message key="lbl.product.name" />
    <fmt:message key="lbl.separator" />
    <form:input path="productName" />
    <c:if test="${empty msg}">
      <form:errors path="productName" cssStyle="color: red" />
    </c:if>
    <br>
    <fmt:message key="lbl.price" />
    <fmt:message key="lbl.separator" />
    <form:input path="price" />
    <c:if test="${empty msg}">
      <form:errors path="price" cssStyle="color: red" />
    </c:if>
    <br>
    <form:button name="search">
      <fmt:message key="btn.search" />
    </form:button>
    <form:button name="insert">
      <fmt:message key="btn.insert" />
    </form:button>
  </form:form>
</body>