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
<title><fmt:message key="title.index" /></title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
  <header>
    <nav>
      <ul class="header-flex">
        <li>
          <span><fmt:message key="title.system" /></span>
        </li>
      </ul>
    </nav>
  </header>

  <form:form action="/auth" method="post" modelAttribute="loginForm">
    <fieldset class="label-100">
      <div>
        <label>
          <fmt:message key="lbl.user.name" />
        </label>
        <form:input path="userName" autocomplete="username" />
      </div>
      <div>
        <label>
          <fmt:message key="lbl.pass" />
        </label>
        <form:password path="password" autocomplete="current-password" />
      </div>
    </fieldset>
    <form:button>
      <fmt:message key="btn.login" />
    </form:button>
  </form:form>
</body>
</html>
