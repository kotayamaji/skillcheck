package jp.co.axiz.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import jp.co.axiz.web.entity.SessionInfo;
import jp.co.axiz.web.util.ParamUtil;

import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class IndexController {
    @Autowired
    MessageSource messageSource;

    @Autowired
    HttpSession session;

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }
    
    @GetMapping("/menu")
    public String menu(Model model) {
        
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);
        if(sessionInfo.getLoginUser() == null ){
            return "index";
        }

        return "menu";
    }
    

}
