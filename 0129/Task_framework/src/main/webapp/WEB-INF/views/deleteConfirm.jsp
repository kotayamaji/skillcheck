<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <p>これでよろしいですか？</p>

  <form action="deleteResult.html" method="post">
    <fieldset>
      <div>
        <label>ID</label>
        <input type="text" name="loginId" value="sato" readonly>
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
      <input type="hidden" name="userId" value="101" />
    </fieldset>
    <div>
      <button type="submit">削除</button>
      <button type="submit"
        onclick="location.href='delete.html'; return false;">戻る</button>
    </div>
  </form>
  <div>
    <a href="menu.html">メニューに戻る</a>
  </div>
</body>
</html>
