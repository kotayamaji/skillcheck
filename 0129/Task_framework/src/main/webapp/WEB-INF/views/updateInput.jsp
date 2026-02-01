<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新内容入力画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <p>
    １箇所以上の項目を変更してください<br>
  </p>

  <c:if test="${not empty errMsg}">
    <p class="error">${fn:escapeXml(errMsg)}</p>
  </c:if>

  <form:form action="updateConfirm" modelAttribute="updateForm" method="post">
         <fieldset>
      <div>
        <label>ID</label>
        <form:input path="loginId" />
        <form:errors path="loginId" class="error" />
      </div>
      <div>
        <label>名前</label>
        <form:input path="userName" />
        <form:errors path="userName" class="error" />
      </div>
      <div>
        <label>TEL</label>
        <form:input path="tel" />
        <form:errors path="tel" class="error" />
      </div>
      <div>
        <label>権限</label> 
        <form:select path="roleId">
          <form:options items="${sessionInfo.roleList}"
            itemLabel="roleName" itemValue="roleId" />
        </form:select>
      </div>
      <div>
        <label>PASS</label>
        <form:input path="password" />
        <form:errors path="password" class="error" />
      </div>
    </fieldset>
    <div>
      <form:button name="confirm">確認</form:button>
      <form:button name="back">戻る</form:button>
    </div>
  </form:form>
  <div>
    <a href="menu">メニューに戻る</a>
  </div>
</body>
</html>
