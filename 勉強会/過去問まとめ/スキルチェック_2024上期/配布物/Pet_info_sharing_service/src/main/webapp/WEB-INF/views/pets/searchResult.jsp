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
<title><fmt:message key="title.pets.search.result" /></title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
  <header>
    <nav>
      <ul class="header-flex">
        <li>
          <span><fmt:message key="title.system" /></span>
        </li>
        <li>
          <a href="/pets/menu"><fmt:message key="btn.menu" /></a>
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
  
  <h1><fmt:message key="title.pets.search.result" /></h1>

  <table>
    <thead>
      <tr>
        <th><fmt:message key="lbl.post.name" /></th>
        <th><fmt:message key="lbl.pet.name" /></th>
        <th><fmt:message key="lbl.animal.type" /></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${sessionInfo.petSearchResult}" var="pet">
        <tr>
          <td>xxx</td>
          <td><a href="/pets/detail?id=${pet.id}">${fn:escapeXml(pet.name)}</a></td>
          <td>xxx</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  <div>
    <a href="/pets/search"><fmt:message key="btn.back" /></a>
  </div>
</body>
</html>
