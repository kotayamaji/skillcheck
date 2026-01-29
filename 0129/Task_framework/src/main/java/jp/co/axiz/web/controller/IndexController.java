package jp.co.axiz.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.axiz.web.entity.SessionInfo;
import jp.co.axiz.web.util.ParamUtil;

/*
 * トップ画面周りコントローラ
 */
@Controller
public class IndexController {

    /*
     * セッション情報
     */
    @Autowired
    HttpSession session;

    /*
     * トップ画面表示
     */
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    /*
     * メニュー画面表示 (各画面の「メニューに戻る」ボタン押下)
     */
    @GetMapping("/menu")
    public String menu(Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "index";
        }

        return "menu";
    }

}
