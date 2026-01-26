<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <p>
    検索したいデータ情報を入力してください<br> ※全て空白の場合は全検索を行います
  </p>

  <p class="error">エラーメッセージ</p>

  <form action="selectResult.html">
    <fieldset>
      <div>
        <label>名前</label>
        <input type="text" name="userName">
      </div>
      <div>
        <label>TEL</label>
        <input type="text" name="tel">
      </div>
    </fieldset>
    <button type="submit">検索</button>
  </form>
  <div>
    <a href="menu.html">メニューに戻る</a>
  </div>
</body>
</html>
