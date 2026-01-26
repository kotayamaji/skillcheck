package jp.co.example.frame1.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import jakarta.servlet.http.HttpSession;
import jp.co.example.frame1.from.Loginform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {
    @Autowired
    MessageSource messageSource;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RoleService roleService;

    @Autowired
    HttpSession session;

    @GetMapping("login")
    public String login(@ModelAttribute("loginForm") Loginform loginform, Model Model) {
        return "login";
    }

    @PostMapping("login")
    public String login(@Validated @ModelAttribute("loginForm") Loginform loginform, BindingResult bindingResult,
            Model Model) {
        String errMsg = messageSource.getMessage("login.error", null, Locale.getDefault());

        // 入力値チェック
        if (bindingResult.hasErrors()) {
            return "/login";
        }

        return entity;
    }

}
