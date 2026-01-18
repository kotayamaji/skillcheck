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
import jp.co.axiz.web.from.UpdateForm;
import jp.co.axiz.web.from.ValidationGroupUpdate;
import jp.co.axiz.web.service.UserInfoService;
import jp.co.axiz.web.util.ParamUtil;

/*
 * 更新画面コントローラ
 */
@Controller
public class UpdateController {

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
    @GetMapping("/update")
    public String update(@ModelAttribute("updateForm") UpdateForm form, Model model) {
        return "update";
    }

    /*
     * 更新内容入力画面遷移 (更新画面の確認ボタン押下時)
     */
    @PostMapping("/updateInput")
    public String updateInput(@Validated(ValidationGroupUpdate.class) @ModelAttribute("updateForm") UpdateForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "update";
        }


        UserInfo userInfo = userInfoService.findByLoginId(form.getLoginId());

        if (userInfo == null) {
            String errMsg = messageSource.getMessage("id.not.found.error", null, Locale.getDefault());
            model.addAttribute("errMsg", errMsg);
            return "update";
        }

        
        UserInfo updateUser = new UserInfo(
                userInfo.getUserId(),
                userInfo.getLoginId(),
                userInfo.getUserName(),
                userInfo.getTelephone(),
                userInfo.getPassword(),
                userInfo.getRoleId(),
                userInfo.getRoleName());

        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        sessionInfo.setPrevUpdateUser(userInfo);

        sessionInfo.setUpdateUser(updateUser);

        // 次画面の入力フォーム用にデータをセット
        form.setLoginId(userInfo.getLoginId());
        form.setUserName(userInfo.getUserName());
        form.setTel(userInfo.getTelephone());
        form.setPassword(userInfo.getPassword());
        form.setRoleId(userInfo.getRoleId());
        form.setRoleName(userInfo.getRoleName());

        return "updateInput";
    }

    /*
     * 更新内容確認画面遷移 (更新内容入力画面の確認ボタン押下時)
     */
    @PostMapping(value = "/updateConfirm", params = "confirm")
    public String updateConfirm(@Validated @ModelAttribute("updateForm") UpdateForm form, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "updateInput";
        }

        // セッション情報取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        // 選択したroleIdに対応するroleNameをセット
        Integer roleId = form.getRoleId();
        String roleName = ParamUtil.getRoleNameByRoleId(roleId, sessionInfo.getRoleList());

        // 変更後データを入力値で更新する
        UserInfo updateUser = sessionInfo.getUpdateUser();

        updateUser.setLoginId(form.getLoginId());
        updateUser.setUserName(form.getUserName());
        updateUser.setTelephone(form.getTel());
        updateUser.setPassword(form.getPassword());
        updateUser.setRoleId(roleId);
        updateUser.setRoleName(roleName);

        // 変更前データを取得
        UserInfo prevUser = sessionInfo.getPrevUpdateUser();

        if (prevUser.equals(updateUser)) {
            // 入力値を変更していない場合
            String errMsg = messageSource.getMessage("required.change", null, Locale.getDefault());
            model.addAttribute("errMsg", errMsg);
            return "updateInput";
        }

        // ID重複チェック
        if (userInfoService.existsUserByLoginIdExcludingUserId(updateUser.getLoginId(), updateUser.getUserId())) {
            // login_idが重複していた場合
            String errMsg = messageSource.getMessage("id.duplicate.error", null, Locale.getDefault());
            model.addAttribute("errMsg", errMsg);
            return "updateInput";
        }

        if (prevUser.getPassword().equals(updateUser.getPassword())) {
            // パスワードを変更していない場合
            // (更新内容確認画面で初期値としてセットするため、リクエストスコープに保存)
            form.setConfirmPassword(updateUser.getPassword());
        }

        // 次画面の入力フォーム用にroleNameをセット
        form.setRoleName(roleName);

        return "updateConfirm";
    }

    /*
     * 更新画面へ戻る (更新内容入力画面の「戻る」ボタン押下時)
     */
    @PostMapping(value = "/updateConfirm", params = "back")
    public String updateBack(@Validated @ModelAttribute("updateForm") UpdateForm form, BindingResult bindingResult,
            Model model) {
        return "update";
    }

    /*
     * 更新処理 (更新内容確認画面の更新ボタン押下)
     */
    @PostMapping(value = "/update", params = "update")
    public String updateExecute(@Validated @ModelAttribute("updateForm") UpdateForm form, BindingResult bindingResult,
            Model model) {

        // セッション情報取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        // 変更後のユーザ情報をセッションから取得
        UserInfo updateUser = sessionInfo.getUpdateUser();

        // パスワードが一致しているかチェック
        if (!updateUser.getPassword().equals(form.getConfirmPassword())) {
            String errMsg = messageSource.getMessage("password.not.match.error", null, Locale.getDefault());
            model.addAttribute("errMsg", errMsg);

            form.setConfirmPassword("");

            return "updateConfirm";
        }

        // 更新処理
        userInfoService.update(updateUser);

        // 更新前/後データをセッションから破棄
        sessionInfo.setPrevUpdateUser(null);
        sessionInfo.setUpdateUser(null);

        return "updateResult";
    }

    /*
     * 更新内容入力画面へ戻る (更新内容確認画面の「戻る」ボタン押下時)
     */
    @PostMapping(value = "/update", params = "back")
    public String updateInputBack(@ModelAttribute("updateForm") UpdateForm form, Model model) {
        // セッション情報取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        // セッションに保存したユーザ情報を取得し、フォームにセット
        UserInfo updateUser = sessionInfo.getUpdateUser();

        form.setRoleId(updateUser.getRoleId());
        form.setPassword(updateUser.getPassword());

        return "updateInput";
    }
}
