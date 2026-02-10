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
                            登録
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
                            <fmt:message key="title.products.search" />
                        </h1>

                        <c:if test="${not empty errMsg}">
                            <p class="error">${fn:escapeXml(errMsg)}</p>
                        </c:if>

                        <form:form action="/products/searchResult" modelAttribute="registerForm" method="get">
                            <fieldset>
                                <div>
                                    <label>
                                        商品名
                                    </label>
                                    <form:input path="productName" />
                                </div>
                                <div>
                                    <label>
                                        価格
                                    </label>
                                    <form:input path="price" />
                                </div>
                                <div>
                                    <label>分類</label>
                                    <form:select path="categoryId">
                                        <form:options items="${sessionInfo.categories}" itemLabel="categoryName"
                                            itemValue="categoryId" />
                                    </form:select>
                                </div>
                                <div>
                                    <label>
                                        備考
                                        <form:textarea path="description" />
                                    </label>
                                </div>
                            </fieldset>
                            <form:button>
                                <fmt:message key="btn.search" />
                            </form:button>
                        </form:form>
                    </body>

                    </html>