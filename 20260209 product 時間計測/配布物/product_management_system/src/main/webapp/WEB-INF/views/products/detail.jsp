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
                <label class="">
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
              <div>
                <label>
                  登録者
                </label>
                <pre>${fn:escapeXml(product.userDispName)}</pre>
              </div>
            </fieldset>
            <form action="/products/delete" method="post">
              <button name="back">
                <fmt:message key="btn.back" />
              </button>
              <c:if test="${canDelete}">
                <button name="delete">
                  削除
                </button>
              </c:if>
            </form>
          </body>

          </html>