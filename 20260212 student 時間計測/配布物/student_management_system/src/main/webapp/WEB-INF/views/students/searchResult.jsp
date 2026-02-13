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
              <fmt:message key="title.students.search.result" />
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
                    <a href="/students/menu">
                      <fmt:message key="btn.menu" />
                    </a>
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

            <h1>
              <fmt:message key="title.students.search.result" />
            </h1>

            <form action="/students/searchResult" method="post">
              <table>
                <thead>
                  <tr>
                    <th>
                      <fmt:message key="lbl.student.name" />
                    </th>
                    <th>
                      <fmt:message key="lbl.grade" />
                    </th>
                    <c:if test="${canDelete}">
                      <th></th>
                    </c:if>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${sessionInfo.studentSearchResult}" var="student">
                    <tr>
                      <td>${fn:escapeXml(student.studentName)}</td>
                      <td>${student.grade}</td>
                      <c:if test="${canDelete}">
                        <td>
                          <button name="deleteStudentId" value="${student.studentId}">
                            <fmt:message key="btn.delete" />
                          </button>
                        </td>
                      </c:if>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </form>

            <div>
              <a href="/students/search">
                <fmt:message key="btn.back" />
              </a>
            </div>
          </body>

          </html>