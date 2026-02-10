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
              <fmt:message key="title.products.search.result" />
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
                    <a href="/products/menu">
                      <fmt:message key="btn.menu" />
                    </a>
                  </li>
                  <c:if test="${sessionInfo.loginUser.isAdmin()}">
                    <li>
                      <span>${fn:escapeXml(sessionInfo.loginUser.roleName)}</span>
                    </li>
                  </c:if>
                  <li>
                    <span>${fn:escapeXml(sessionInfo.loginUser.dispName)}</span>
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
              <fmt:message key="title.products.search.result" />
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
                <c:forEach items="${sessionInfo.productSearchResult}" var="product">
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
            <div>
              <a href="/products/search">
                <fmt:message key="btn.back" />
              </a>
            </div>
          </body>

          </html>