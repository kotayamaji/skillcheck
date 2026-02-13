package jp.co.axiz.axizmarket.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.axiz.axizmarket.entity.SessionInfo;
import jp.co.axiz.axizmarket.entity.User;
import jp.co.axiz.axizmarket.form.LoginForm;
import jp.co.axiz.axizmarket.form.SearchForm;
import jp.co.axiz.axizmarket.service.UserService;
import jp.co.axiz.axizmarket.util.ParamUtil;

/*
 * 認証処理関連コントローラ
 */
@Controller
public class AuthController {

    /*
     * messages_ja.propertiesのメッセージ取得用
     */
    MessageSource messageSource;

    /*
     * セッション情報
     */
    HttpSession session;

    /*
     * ユーザーサービス
     */
    private UserService userInfoService;

    public AuthController(MessageSource messageSource, HttpSession session,
            UserService userInfoService) {
        this.messageSource = messageSource;
        this.session = session;
        this.userInfoService = userInfoService;
    }

    /*
     * ログイン画面
     */
    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm form,
            @ModelAttribute("searchForm") SearchForm searchform, Model model) {
        return "/login";
    }

    /*
     * ログイン処理
     */
    @PostMapping("/auth")
    public String auth(@Validated @ModelAttribute("loginForm") LoginForm loginform, BindingResult bindingResult,
            @ModelAttribute("searchForm") SearchForm searchform, Model model) {

        if (bindingResult.hasErrors()) {
            return "/login";
        }

        // エラーメッセージ
        String errMsg = messageSource.getMessage("login.error", null, Locale.getDefault());

        // ユーザー情報取得
        User user = userInfoService.authentication(loginform.getUserName(), loginform.getPassword());

        // 入力値チェック

        if (user == null) {
            // ログイン失敗
            model.addAttribute("errMsg", errMsg);
            return "/login";
        } else {
            // セッション情報取得
            SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

            // ログインユーザ情報をセット
            sessionInfo.setLoginUser(user);

            // セッションに保存
            session.setAttribute("sessionInfo", sessionInfo);

            return "redirect:/";
        }
    }

    /*
     * ログアウト
     */
    @PostMapping("/logout")
    public String logout(Model model) {
        session.invalidate();
        return "redirect:/";
    }
}
