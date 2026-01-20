<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
</head>
<body>
  <h2>検索結果</h2>

  <table border="1">
    <tr>
      <th width="40"><fmt:message key="lbl.product.id" /></th>
      <th width="160"><fmt:message key="lbl.product.name" /></th>
      <th width="80"><fmt:message key="lbl.price" /></th>
    </tr>
    <c:forEach var="product" items="${productList}">
      <tr>
        <td>${fn:escapeXml(product.productId)}</td>
        <td>${fn:escapeXml(product.productName)}</td>
        <td>${fn:escapeXml(product.price)}</td>
      </tr>
    </c:forEach>
  </table>
  <br>
  <a href="top"><fmt:message key="btn.back" /></a>

</body>