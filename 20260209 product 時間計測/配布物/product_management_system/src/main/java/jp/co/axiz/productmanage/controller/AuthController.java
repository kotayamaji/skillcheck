package jp.co.axiz.productmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.axiz.productmanage.entity.SessionInfo;
import jp.co.axiz.productmanage.entity.User;
import jp.co.axiz.productmanage.form.LoginForm;
import jp.co.axiz.productmanage.service.UserService;
import jp.co.axiz.productmanage.util.ParamUtil;

/*
 * 認証処理関連コントローラ
 */
@Controller
public class AuthController {

    /*
     * セッション情報
     */
    HttpSession session;

    /*
     * ユーザーサービス
     */
    private UserService userInfoService;

    public AuthController(HttpSession session, UserService userInfoService) {
        this.session = session;
        this.userInfoService = userInfoService;
    }

    /*
     * トップ画面
     */
    @GetMapping("/")
    public String index(@ModelAttribute("loginForm") LoginForm form, Model model) {
        return "index";
    }

    /*
     * ログイン処理
     */
    @PostMapping("/auth")
    public String auth(@Validated @ModelAttribute("loginForm") LoginForm form,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/index";
        }
        // ユーザー情報取得
        User user = userInfoService.authentication(form.getUserName(), form.getPassword());
        if (user == null) {
            model.addAttribute("errMsg", "IDまたはPASSが間違っています");
            return "/index";
        }

        // セッション情報取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        // ログインユーザ情報をセット
        sessionInfo.setLoginUser(user);

        sessionInfo.setCategories(userInfoService.categorys());

        // セッションに保存
        session.setAttribute("sessionInfo", sessionInfo);

        return "/products/menu";
    }

    /*
     * ログアウト
     */
    @PostMapping("/logout")
    public String logout(@ModelAttribute("loginForm") LoginForm form, Model model) {
        session.invalidate();
        return "/index";
    }
}
