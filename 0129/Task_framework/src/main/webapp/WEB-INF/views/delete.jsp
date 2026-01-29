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
