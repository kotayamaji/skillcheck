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
              <fmt:message key="title.pets.menu" />
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
                    <a href="/pets/menu">
                      <fmt:message key="btn.menu" />
                    </a>
                  </li>
                  <li>
                    ${fn:escapeXml(sessionInfo.userInfo.dispName)}
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

            <c:if test="${not empty errMsg}">
              <p class="error">${fn:escapeXml(errMsg)}</p>
            </c:if>
            <h1>
              <fmt:message key="title.pets.menu" />
            </h1>

            <p>
              <a href="/pets/search">
                <fmt:message key="btn.search" />
              </a>
            </p>
            <p>
              <a href="/pets/register">
                登録
              </a>
            </p>
          </body>

          </html>