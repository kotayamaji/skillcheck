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
              <fmt:message key="title.products.detail" />
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

            <h1>
              <fmt:message key="title.products.detail" />
            </h1>

            <fieldset class="label">
              <div>
                <label>
                  <fmt:message key="lbl.product.name" />
                  <fmt:message key="lbl.separator" />
                </label>
                <span>${fn:escapeXml(product.productName)}</span>
              </div>
              <div>
                <label>
                  <fmt:message key="lbl.price" />
                  <fmt:message key="lbl.separator" />
                </label>
                <span>${fn:escapeXml(product.price)}</span>
              </div>
              <div>
                <label>
                  分類
                  <fmt:message key="lbl.separator" />
                </label>
                <span>${fn:escapeXml(product.categoryName)}</span>
              </div>
              <div>
                <label>
                  <fmt:message key="lbl.remarks" />
                  <fmt:message key="lbl.separator" />
                </label>
                <pre>${fn:escapeXml(product.remarks)}</pre>
              </div>
              <form action="/products/detail" method="post">
                <c:if test="${not empty sessionInfo.loginUser}">
                  <button name="book" value="${product.productId}">
                    ブックマークに登録
                  </button>
                </c:if>
              </form>
            </fieldset>
          </body>

          </html>