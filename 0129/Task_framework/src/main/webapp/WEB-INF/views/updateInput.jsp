<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新内容入力画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <p>
    １箇所以上の項目を変更してください<br>
  </p>

  <p class="error">エラーメッセージ</p>

  <form action="updateConfirm.html" method="post">
    <fieldset>
      <div>
        <label>ID</label>
        <input type="text" name="loginId" value="n_alice">
        <span class="error">エラーメッセージ</span>
      </div>
      <div>
        <label>名前</label>
        <input type="text" name="userName" value="alice">
        <span class="error">エラーメッセージ</span>
      </div>
      <div>
        <label>TEL</label>
        <input type="text" name="tel" value="19980406">
        <span class="error">エラーメッセージ</span>
      </div>
      <div>
        <label>権限</label> <select name="roleId">
          <option value="1">管理者</option>
          <option value="2" selected>一般</option>
        </select>
      </div>
      <div>
        <label>PASS</label>
        <input type="password" name="pass" value="pw12345">
        <span class="error">エラーメッセージ</span>
      </div>
    </fieldset>
    <div>
      <button type="submit">確認</button>
      <button type="submit"
        onclick="location.href='update.html'; return false;">戻る</button>
    </div>
  </form>
  <div>
    <a href="menu.html">メニューに戻る</a>
  </div>
</body>
</html>
