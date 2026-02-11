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
                            登録
                        </h1>
                        <p>
                            <span style="color: red">※</span>は必須です。
                        </p>

                        <c:if test="${not empty errMsg}">
                            <p class="error">${fn:escapeXml(errMsg)}</p>
                        </c:if>

                        <form:form action="/products/register" modelAttribute="registerForm" method="post">
                            <fieldset>
                                <div>
                                    <label class="required">
                                        商品名
                                    </label>
                                    <form:input path="productName" />
                                    <form:errors path="productName" class="error" />

                                </div>
                                <div>
                                    <label class="required">
                                        価格
                                    </label>
                                    <form:input path="price" />
                                    <form:errors path="price" class="error" />
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
                                        <form:textarea path="remarks" />
                                    </label>
                                </div>
                            </fieldset>
                            <form:button>
                                登録
                            </form:button>
                        </form:form>
                    </body>

                    </html>