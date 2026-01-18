<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8">
            <title>検索結果画面</title>
            <link href="css/commons.css" rel="stylesheet">
          </head>

          <body>
            <c:if test="${sessionInfo.loginUser.isAdmin()}">
              <p>
                <fmt:message key="edit.sel.msg" />
              </p>
              <c:if test="${not empty errMsg}">
                <p class="error">${fn:escapeXml(errMsg)}</p>
              </c:if>
            </c:if>

            <form:form action="selectResult" modelAttribute="selectResult" method="post">
              <table>
                <caption>検索結果</caption>
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>名前</th>
                    <th>TEL</th>
                    <th>権限</th>
                    <th>登録日</th>
                    <th>更新日時</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${userList}" var="user">
                    <tr>
                      <c:if test="${sessionInfo.loginUser.isAdmin()}">
                        <td class="center"><label>
                            <form:checkbox path="userId" value="${fn:escapeXml(user.userId)}"></form:checkbox>
                          </label></td>
                      </c:if>
                        <td>${fn:escapeXml(user.loginId)}</td>
                        <td>${fn:escapeXml(user.userName)}</td>
                        <td>${fn:escapeXml(user.telephone)}</td>
                        <td>${fn:escapeXml(user.roleName)}</td>
                        <td>${fn:escapeXml(user.createDatetimeFormatDate)}</td>
                        <td>${fn:escapeXml(user.updateDatetimeFormatDate)}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>

              <c:if test="${sessionInfo.loginUser.isAdmin()}">
                <br>
                <form:button name="update">
                  <div>編集</div>
                </form:button>
                <form:button name="delete">
                  <div>削除</div>
                </form:button>
              </c:if>
            </form:form>
            <div>
              <a href="menu">メニューに戻る</a>
            </div>
          </body>

          </html>