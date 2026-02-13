package jp.co.axiz.studentmanage.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.axiz.studentmanage.entity.Major;
import jp.co.axiz.studentmanage.entity.SessionInfo;
import jp.co.axiz.studentmanage.entity.User;
import jp.co.axiz.studentmanage.form.LoginForm;
import jp.co.axiz.studentmanage.service.UserService;
import jp.co.axiz.studentmanage.util.ParamUtil;

/*
 * 認証処理関連コントローラ
 */
@Controller
public class AuthController {

    /*
     * messages_ja.propertiesのメッセージ取得用
     */
    @Autowired
    MessageSource messageSource;

    /*
     * セッション情報
     */
    @Autowired
    HttpSession session;

    /*
     * ユーザーサービス
     */
    @Autowired
    private UserService userInfoService;

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
    public String auth(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult,
            Model model) {

        // エラーメッセージ
        String errMsg = messageSource.getMessage("login.error", null, Locale.getDefault());

        // 入力値チェック
        if (bindingResult.hasErrors()) {
            return "/index";
        }

        // ユーザー情報取得
        User user = userInfoService.authentication(form.getUserName(), form.getPassword());

        if (user == null) {
            // ログイン失敗
            model.addAttribute("errMsg", errMsg);
            return "/index";
        } else {
            // セッション情報取得
            SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

            // ログインユーザ情報をセット
            sessionInfo.setLoginUser(user);
            if (user.getRoleId() == 1) {
                session.setAttribute("canDelete", true);
            } else {
                session.setAttribute("canDelete", false);
            }
            List<Major> majors = userInfoService.majors();
            sessionInfo.setMajorList(majors);

            // セッションに保存
            session.setAttribute("sessionInfo", sessionInfo);

            return "/students/menu";
        }
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
