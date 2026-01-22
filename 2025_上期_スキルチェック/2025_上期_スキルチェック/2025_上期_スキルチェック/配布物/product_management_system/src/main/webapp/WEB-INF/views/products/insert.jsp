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
<title><fmt:message key="title.products.search" /></title>
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
          <a href="/products/menu"><fmt:message key="btn.menu" /></a>
        </li>
        <c:if test="${sessionInfo.loginUser.isAdmin()}">
          <li>
            <span>${fn:escapeXml(sessionInfo.loginUser.roleName)}</span>
          </li>
        </c:if>
        <li>
          <span>${fn:escapeXml(sessionInfo.loginUser.dispName)}</span>
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
  
  <h1><fmt:message key="title.products.insert" /></h1>

  <c:if test="${not empty errMsg}">
    <p class="error">${fn:escapeXml(errMsg)}</p>
  </c:if>

  <form:form action="/insert" modelAttribute="insertForm" method="get">
    <fieldset>
      <div>
        <label>
          <fmt:message key="lbl.product.name" />
        </label>
        <form:input path="productName" />
      </div>
    　<div>
        <label>
          <fmt:message key="lbl.price" />
        </label>
        <form:input path="price" />
      </div>
      <div>
        <label>
          <fmt:message key="lbl.categoryName" />
        </label>
        <form:select path="categoryId">
          <form:options items="${sessionInfo.categoryList}"
            itemLabel="categoryName" itemValue="categoryId" />
        </form:select>
      </div>
     <div>
        <label>
          <fmt:message key="lbl.remarks" />
        </label>
        <form:textarea path="remarks" />
      </div>
    </fieldset>
    <form:button>
      <fmt:message key="btn.insert" />
    </form:button>
  </form:form>
</body>
</html>
