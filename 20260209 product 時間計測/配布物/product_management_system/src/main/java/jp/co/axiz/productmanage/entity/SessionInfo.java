package jp.co.axiz.productmanage.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * セッション情報を纏めて管理するためのクラス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionInfo {
    User loginUser; // ログインユーザ情報
    List<Product> productSearchResult; // 商品検索結果
    List<Category> categories;

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
