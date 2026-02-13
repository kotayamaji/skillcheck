<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8">
            <title>
              <fmt:message key="title.index" />
            </title>
            <link href="/css/commons.css" rel="stylesheet">
          </head>

          <body>
            <header>
              <nav>
                <ul class="header-flex">
                  <li>
                    <span>
                      <fmt:message key="title.system" />
                    </span>
                  </li>
                  <li>
                    <form:form action="/" modelAttribute="searchForm" method="get">
                      <div>
                        <form:select path="categoryId">
                          <form:option value="0">すべて</form:option>
                          <form:options items="${sessionInfo.categoryList}" itemLabel="categoryName"
                            itemValue="categoryId" />
                        </form:select>
                      </div>
                      <form:input path="productName" />
                      <button type="submit">
                        <fmt:message key="btn.search" />
                      </button>
                    </form:form>
                  </li>
                  <c:if test="${empty sessionInfo.loginUser}">
                    <li>
                      <a href="/login">
                        <fmt:message key="btn.login" />
                      </a>
                    </li>
                  </c:if>
                  <c:if test="${not empty sessionInfo.loginUser}">
                    <li>
                      <span>${fn:escapeXml(sessionInfo.loginUser.dispName)}</span>
                    </li>
                    <form action="/book" method="get">
                      <button type="submit">
                        ブックマーク
                      </button>
                    </form>
                    <li>
                      <form action="/logout" method="post">
                        <button type="submit">
                          <fmt:message key="btn.logout" />
                        </button>
                      </form>
                    </li>
                  </c:if>
                </ul>
              </nav>
            </header>

            <c:if test="${not empty errMsg}">
              <p class="error">${fn:escapeXml(errMsg)}</p>
            </c:if>

            <form:form action="/auth" method="post" modelAttribute="loginForm">
              <fieldset class="label-100">
                <div>
                  <label>
                    <fmt:message key="lbl.user.name" />
                  </label>
                  <form:input path="userName" autocomplete="username" />
                  <form:errors path="userName" class="error" />
                </div>
                <div>
                  <label>
                    <fmt:message key="lbl.pass" />
                  </label>
                  <form:password path="password" autocomplete="current-password" />
                  <form:errors path="password" class="error" />
                </div>
              </fieldset>
              <form:button>
                <fmt:message key="btn.login" />
              </form:button>
            </form:form>
          </body>

          </html>