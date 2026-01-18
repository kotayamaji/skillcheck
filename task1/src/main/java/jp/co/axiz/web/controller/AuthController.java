package jp.co.axiz.web.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import jakarta.servlet.http.HttpSession;
import jp.co.axiz.web.entity.Role;
import jp.co.axiz.web.entity.SessionInfo;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.from.Loginform;
import jp.co.axiz.web.service.RoleService;
import jp.co.axiz.web.service.UserInfoService;
import jp.co.axiz.web.util.ParamUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {
    /*
     * messages_ja.propertiesのメッセージ取得用
     */
    @Autowired
    MessageSource messageSource;

    /*
     * ユーザーサービス
     */
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RoleService roleService;

    /*
     * セッション情報
     */
    @Autowired
    HttpSession session;

    @GetMapping("login")
    public String login(@ModelAttribute("loginForm") Loginform loginform, Model Model) {
        return "login";
    }

    @PostMapping("login")
    public String login(@Validated @ModelAttribute("loginForm") Loginform loginform, BindingResult bindingresult,
            Model model) {

        String errMsg = messageSource.getMessage("login.error", null, Locale.getDefault());

        if (bindingresult.hasErrors()) {
            return "login";
        }

        UserInfo userInfo = userInfoService.authentication(loginform.getLoginId(), loginform.getPassword());

        if (userInfo == null) {
            // ログイン失敗
            model.addAttribute("errMsg", errMsg);
            return "login";
        } else {
            // ログイン成功

            // roleリスト取得
            List<Role> roleList = roleService.findAll();

            SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);
            sessionInfo.setLoginUser(userInfo);
            sessionInfo.setRoleList(roleList);

            session.setAttribute("sessionInfo", sessionInfo);
            return "menu";
        }
    }

    @PostMapping("logout")
    public String logout(Model model) {
        session.invalidate();
        return "logout";
    }

}
