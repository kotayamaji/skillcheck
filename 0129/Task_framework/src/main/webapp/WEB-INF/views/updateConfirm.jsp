<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新内容確認画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <p>これでよろしいですか？</p>

  <p class="error">エラーメッセージ</p>

  <form action="updateResult.html" method="post">
    <fieldset class="label-130">
      <div>
        <label>ID</label>
        <input type="text" name="loginId" value="n_alice" readonly>
      </div>
      <div>
        <label>名前</label>
        <input type="text" name="userName" value="alice" readonly>
      </div>
      <div>
        <label>TEL</label>
        <input type="text" name="tel" value="19980406" readonly>
      </div>
      <div>
        <label>権限</label>
        <input type="text" name="roleName" value="一般" readonly>
      </div>
      <div>
        <label>PASS（再入力）</label>
        <input type="password" name="rePass">
      </div>
    </fieldset>
    <div>
      <button type="submit">更新</button>
      <button type="submit"
        onclick="location.href='updateInput.html'; return false;">戻る</button>
    </div>
  </form>
  <div>
    <a href="menu.html">メニューに戻る</a>
  </div>
</body>
</html>
