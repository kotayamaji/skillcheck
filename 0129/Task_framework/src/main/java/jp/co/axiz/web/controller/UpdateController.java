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
    @PostMapping("/update")
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
        user = new UserInfo(
                null,
                form.getLoginId(),
                form.getUserName(),
                form.getTel(),
                form.getPassword(),
                form.getRoleId());

        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        // 選択したroleIdに対応するroleNameをセット
        String roleName = ParamUtil.getRoleNameByRoleId(form.getRoleId(), sessionInfo.getRoleList());
        user.setRoleName(roleName);

        // 入力情報をセッションへ保存
        sessionInfo.setRegisterUser(user);

        // 次画面の入力フォーム用にroleNameをセット
        form.setRoleName(roleName);

        // ID重複チェック
        if (userInfoService.existsUserByLoginId(form.getLoginId())) {
            // login_idが重複していた場合
            String errMsg = messageSource.getMessage("id.duplicate.error", null, Locale.getDefault());
            model.addAttribute("errMsg", errMsg);
            return "insert";
        }

        return "insertConfirm";
    }

    /*
     * 登録処理 (登録確認画面の登録ボタン押下)
     */
    @PostMapping(value = "/updatec", params = "insert")
    public String insertExecute(@Validated @ModelAttribute("insertForm") InsertForm form, BindingResult bindingResult,
            Model model) {

        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        // セッションに保存したユーザ情報を取得
        UserInfo user = sessionInfo.getRegisterUser();

        if (!user.getPassword().equals(form.getConfirmPassword())) {
            // 再入力パスワードが一致しない場合
            String errMsg = messageSource.getMessage("password.not.match.error", null, Locale.getDefault());
            model.addAttribute("errMsg", errMsg);

            form.setConfirmPassword("");

            return "insertConfirm";
        }

        // 登録処理
        userInfoService.insert(user);

        // 登録用データをセッションから破棄
        sessionInfo.setRegisterUser(null);

        return "insertResult";
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
