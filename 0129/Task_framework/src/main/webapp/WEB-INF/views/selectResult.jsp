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
<title><fmt:message key="title.search.result" /></title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <table>
    <caption>検索結果</caption>
    <thead>
      <tr>
        <th><fmt:message key="lbl.id" /></th>
        <th><fmt:message key="lbl.user.name" /></th>
        <th><fmt:message key="lbl.tel" /></th>
        <th><fmt:message key="lbl.role" /></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${userlist}" var="user">
        <tr>
          <td>${fn:escapeXml(user.loginId)}</td>
          <td>${fn:escapeXml(user.userName)}</td>
          <td>${fn:escapeXml(user.telephone)}</td>
          <td>${fn:escapeXml(user.roleName)}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  <div>
    <a href="menu"><fmt:message key="btn.menu" /></a>
  </div>
</body>
</html>
