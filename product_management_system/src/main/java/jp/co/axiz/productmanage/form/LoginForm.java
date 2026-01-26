package jp.co.axiz.productmanage.form;

import jakarta.validation.constraints.NotBlank;

/*
 * ログイン画面用フォーム
 */
public class LoginForm {
	@NotBlank(message = "ユーザー名は必須です")
	private String userName;
	@NotBlank(message = "パスワードは必須です")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String _password) {
		this.password = _password;
	}
}
