<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8">
            <title>削除画面</title>
            <link href="css/commons.css" rel="stylesheet">
          </head>

          <body>
            <p>
              削除するIDを入力してください<br> <span class="required"></span>は必須です
            </p>

            <p class="error">エラーメッセージ</p>

            <form action="deleteConfirm.html" method="post">
              <fieldset>
                <div>
                  <label class="required">ID</label>
                  <input type="text" name="loginId">
                  <span class="error">エラーメッセージ</span>
                </div>
              </fieldset>
              <button type="submit">確認</button>
            </form>
            <div>
              <a href="menu.html">メニューに戻る</a>
            </div>
          </body>

          </html>