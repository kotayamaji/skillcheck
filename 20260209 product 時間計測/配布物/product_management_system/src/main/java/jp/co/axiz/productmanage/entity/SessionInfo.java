package jp.co.axiz.productmanage.entity;

import java.util.List;

/**
 * セッション情報を纏めて管理するためのクラス
 */
public class SessionInfo {
    User loginUser; // ログインユーザ情報
    List<Product> productSearchResult; // 商品検索結果

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public List<Product> getProductSearchResult() {
        return productSearchResult;
    }

    public void setProductSearchResult(List<Product> productSearchResult) {
        this.productSearchResult = productSearchResult;
    }
}
