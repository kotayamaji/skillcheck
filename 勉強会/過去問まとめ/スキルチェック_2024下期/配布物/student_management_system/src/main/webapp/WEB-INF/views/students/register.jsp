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
<title><fmt:message key="title.students.register" /></title>
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
          <a href="/students/menu"><fmt:message key="btn.menu" /></a>
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
  
  <h1><fmt:message key="title.students.register" /></h1>

  <p>
    <span class="required"></span>は必須です
  </p>

  <form:form action="/students/register" method="post"
    modelAttribute="registerForm">
    <fieldset class="label-60">
      <div>
        <label class="required"><fmt:message key="lbl.student.name" /></label>
        <form:input path="studentName" />
        <form:errors path="studentName" class="error" />
      </div>
      <div>
        <label class="required"><fmt:message key="lbl.grade" /></label>
        <form:input path="grade" />
        <form:errors path="grade" class="error" />
      </div>
    </fieldset>
    <form:button>
      <fmt:message key="btn.register" />
    </form:button>
  </form:form>
</body>
</html>
