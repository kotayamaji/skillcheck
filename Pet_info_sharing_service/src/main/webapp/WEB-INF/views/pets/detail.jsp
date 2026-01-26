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
              <fmt:message key="title.pets.detail" />
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
              <fmt:message key="title.pets.detail" />
            </h1>

            <fieldset class="label-80">
              <div>
                <label>
                  <fmt:message key="lbl.pet.name" />
                  <fmt:message key="lbl.separator" />
                </label>
                <span>${fn:escapeXml(pet.name)}</span>
              </div>
              <div>
                <label>
                  <fmt:message key="lbl.animal.type" />
                  <fmt:message key="lbl.separator" />
                </label>
                <span>${fn:escapeXml(pet.type)}</span>
              </div>
              <div>
                <label>
                  <fmt:message key="lbl.description" />
                  <fmt:message key="lbl.separator" />
                </label>
                <pre>${fn:escapeXml(pet.description)}</pre>
              </div>
              <div>
                <label>
                  <fmt:message key="lbl.post.name" />
                  <fmt:message key="lbl.separator" />
                </label>
                <span>${fn:escapeXml(pet.dispName)}</span>
              </div>
            </fieldset>
            <form:form action="fromDelete" modelAttribute="loginForm">
              <c:if test="${canDelete}">
                <form:button name="delete">削除</form:button>
              </c:if>
              <form:button name="back">戻る</form:button>
            </form:form>
          </body>

          </html>