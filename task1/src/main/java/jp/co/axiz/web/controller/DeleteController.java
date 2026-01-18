
package jp.co.axiz.web.controller;

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
import jp.co.axiz.web.entity.SessionInfo;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.from.DeleteForm;
import jp.co.axiz.web.from.UpdateForm;
import jp.co.axiz.web.from.ValidationGroupUpdate;
import jp.co.axiz.web.service.UserInfoService;
import jp.co.axiz.web.util.ParamUtil;

/*
 * 更新画面コントローラ
 */
@Controller
public class DeleteController {

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
     * ユーザ情報サービス
     */
    @Autowired
    private UserInfoService userInfoService;

    /*
     * 更新画面表示
     */
    @GetMapping("/delete")
    public String delete(@ModelAttribute("deleteForm") DeleteForm form, Model model) {
        return "delete";
    }

    /*
     * 更新内容確認画面遷移 (更新内容入力画面の確認ボタン押下時)
     */
    @PostMapping("/deleteConfirm")
    public String deleteConfirm(@Validated @ModelAttribute("deleteForm") DeleteForm form, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "updateInput";
        }

        // 入力値取得
        String loginId = form.getLoginId();

        // セッション情報取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (loginId.equals(sessionInfo.getLoginUser().getLoginId())) {
            // ログインユーザを指定した場合
            String errMsg = messageSource.getMessage("delete.login.user", null, Locale.getDefault());
            model.addAttribute("errMsg", errMsg);
            return "delete";
        }

        // 入力したIDに紐づくユーザを検索
        UserInfo user = userInfoService.findByLoginId(loginId);

        if (user == null) {
            // 該当するデータが存在しない場合
            String errMsg = messageSource.getMessage("id.not.found.error", null, Locale.getDefault());
            model.addAttribute("errMsg", errMsg);
            return "delete";
        }

        form.setUserId(user.getUserId());
        form.setLoginId(user.getLoginId());
        form.setUserName(user.getUserName());
        form.setTel(user.getTelephone());
        form.setRoleName(user.getRoleName());

        return "deleteConfirm";

    }

    /*
     * 削除処理
     */
    @PostMapping(value = "/delete", params = "delete")
    public String delete(@Validated @ModelAttribute("deleteForm") DeleteForm form, BindingResult bindingResult,
            Model model) {

        int id = form.getUserId();

        userInfoService.delete(id);

        return "deleteResult";
    }


    /*
     * 削除画面へ戻る (削除確認画面の「戻る」ボタン押下時)
     */
    @PostMapping(value = "/delete", params = "back")
    public String deleteBack(@ModelAttribute("deleteForm") DeleteForm form, Model model) {
        return "delete";
    }

}
