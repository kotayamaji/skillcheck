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
import jp.co.axiz.web.form.InsertForm;
import jp.co.axiz.web.form.UpdateForm;
import jp.co.axiz.web.service.UserInfoService;
import jp.co.axiz.web.util.ParamUtil;

/*
 * 登録画面コントローラ
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
    public String update(@ModelAttribute("updateForm") UpdateForm form,
            Model model) {

        return "update";
    }

    /*
     * 登録確認画面遷移 (登録画面の確認ボタン押下時)
     */
    @PostMapping("/updateInput")
    public String update(@Validated @ModelAttribute("updateForm") UpdateForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "update";
        }

        UserInfo user = userInfoService.findByLoginId(form.getLoginId());
        if (user == null) {
            model.addAttribute("errMsg", "入力されたデータは存在しません");
            return "update";
        }

        // 更新ユーザ情報をEntityにセット
        UserInfo userup = new UserInfo(
                user.getUserId(),
                user.getLoginId(),
                user.getUserName(),
                user.getTelephone(),
                user.getPassword(),
                user.getRoleId(),
                user.getRoleName());

        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        //更新前情報保存
        sessionInfo.setPrevUpdateUser(user);

        //更新後
        sessionInfo.setUpdateUser(userup);

        form.setLoginId(user.getLoginId());
        form.setUserName(user.getUserName());
        form.setTel(user.getTelephone());
        form.setPassword(user.getPassword());
        form.setRoleId(user.getRoleId());
        form.setRoleName(user.getRoleName());

        return "updateInput";
    }



    /*
     * 確認ボタン押下時
     */
     @PostMapping(value = "/updateConfirm", params = "confirm")
    public String insertExecute(@Validated @ModelAttribute("updateForm") UpdateForm form, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "updateInput";
        }

        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        Integer roleId = form.getRoleId();
        String roleName = ParamUtil.getRoleNameByRoleId(roleId, sessionInfo.getRoleList());

        UserInfo updateUser = sessionInfo.getUpdateUser();

        updateUser.setLoginId(form.getLoginId());
        updateUser.setUserName(form.getUserName());
        updateUser.setTelephone(form.getTel());
        updateUser.setPassword(form.getPassword());
        updateUser.setRoleId(roleId);
        updateUser.setRoleName(roleName);
        
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
     * 登録画面へ戻る (登録確認画面の「戻る」ボタン押下時)
     */
    @PostMapping(value = "/updateb", params = "back")
    public String insertBack(@ModelAttribute("insertForm") InsertForm form, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        // セッションに保存したユーザ情報を取得し、フォームにセット
        UserInfo user = sessionInfo.getRegisterUser();

        form.setRoleId(user.getRoleId());
        form.setPassword(user.getPassword());

        return "insert";
    }
}
