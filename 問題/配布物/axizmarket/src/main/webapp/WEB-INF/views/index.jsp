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

            <h1>
              <fmt:message key="title.index" />
            </h1>

            <table>
              <thead>
                <tr>
                  <th>
                    <fmt:message key="lbl.product.name" />
                  </th>
                  <th>
                    <fmt:message key="lbl.price" />
                  </th>
                  <th>
                    分類
                  </th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${productSearchResult}" var="product">
                  <tr>
                    <td><a
                        href="/products/detail?productId=${product.productId}">${fn:escapeXml(product.productName)}</a>
                    </td>
                    <td>${fn:escapeXml(product.price)}</td>
                    <td>${fn:escapeXml(product.categoryName)}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </body>

          </html>