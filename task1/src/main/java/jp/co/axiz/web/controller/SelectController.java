package jp.co.axiz.web.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.axiz.web.entity.SessionInfo;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.from.SelectResultForm;
import jp.co.axiz.web.from.Selectform;
import jp.co.axiz.web.from.UpdateForm;
import jp.co.axiz.web.service.UserInfoService;
import jp.co.axiz.web.util.ParamUtil;

@Controller
public class SelectController {
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
    private UserInfoService userInfoService;

    @GetMapping("/select")
    public String select(@ModelAttribute("selectForm")Selectform selectform,Model Model) {
        return "select";
    }

    @GetMapping("/list")
    public String insert(@Validated @ModelAttribute("selectForm")Selectform selectform,BindingResult bindingResult,Model model) {
        //セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);
        if(sessionInfo.getLoginUser() == null){
            //ログインしてない場合はトップに戻る
            return "index";
        }

        if(bindingResult.hasErrors()){
            return "select";
        }

        UserInfo condition = new UserInfo(
                null,
                null,
                selectform.getName(),
                selectform.getTel(),
                null,
                null);

        List<UserInfo> resultList = userInfoService.select(condition);

        if(resultList.isEmpty()){
            String errMsg = messageSource.getMessage("select.error", null , Locale.getDefault());
            model.addAttribute("errMsg",errMsg);
            return "select";
        } else{
            model.addAttribute("userList",resultList);
            return "selectResult";

        }
    }

    @PostMapping(value="/seletctResult",params="update") 
    public String edit(@ModelAttribute("SelectResult") SelectResultForm form,@ModelAttribute("updateForm") UpdateForm updForm,Model model){
        Integer[] userIds = form.getUserId();

        String errMsg = null;
        if(userIds == null || userIds.length == 0){
            errMsg = messageSource.getMessage("edit.nosel.error", null,Locale.getDefault());
        } else if (userIds.length > 1) {
            errMsg = messageSource.getMessage("edit.sel.error", null, Locale.getDefault());
        }

        if(errMsg != null ){
            model.addAttribute("errMsg", errMsg);
            return "selectResult";
        }

        UserInfo userInfo = userInfoService.findByUserId(userIds[0]);

        UserInfo updateUser = new UserInfo(userInfo.getUserId(), userInfo.getLoginId(), userInfo.getUserName(),
                userInfo.getTelephone(), userInfo.getPassword(), userInfo.getRoleId(), userInfo.getRoleName());

        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        sessionInfo.setPrevUpdateUser(userInfo);

        sessionInfo.setUpdateUser(updateUser);

        updForm.setLoginId(userInfo.getLoginId());
        updForm.setUserName(userInfo.getUserName());
        updForm.setTel(userInfo.getTelephone());
        updForm.setPassword(userInfo.getPassword());
        updForm.setRoleId(userInfo.getRoleId());
        updForm.setRoleName(userInfo.getRoleName());

        session.setAttribute("updInpBackUrl", "selectResult");

        return "updateInput";

    }
    
}
