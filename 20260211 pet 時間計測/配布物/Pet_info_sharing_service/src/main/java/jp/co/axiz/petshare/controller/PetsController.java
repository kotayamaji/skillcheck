package jp.co.axiz.petshare.controller;

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
import jp.co.axiz.petshare.entity.Pet;
import jp.co.axiz.petshare.entity.SessionInfo;
import jp.co.axiz.petshare.form.LoginForm;
import jp.co.axiz.petshare.form.RegisterForm;
import jp.co.axiz.petshare.form.SearchForm;
import jp.co.axiz.petshare.service.PetService;
import jp.co.axiz.petshare.util.ParamUtil;

/*
 * ペット関連コントローラ
 */
@Controller
public class PetsController {

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
     * ペットサービス
     */
    @Autowired
    private PetService petService;

    /*
     * ペット初期画面
     */
    @GetMapping("/pets/menu")
    public String index(@ModelAttribute("loginForm") LoginForm form, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }
        return "/pets/menu";
    }

    /*
     * 検索画面
     */
    @GetMapping("/pets/search")
    public String search(@ModelAttribute("searchForm") SearchForm searchForm,
            @ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }
        return "/pets/search";
    }

    /*
     * 検索結果画面
     */
    @GetMapping("/pets/searchResult")
    public String searchResult(@ModelAttribute("searchForm") SearchForm searchForm,
            @ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }

        // 検索条件をEntityにセット
        Pet pet = new Pet();
        pet.setName(searchForm.getName());
        pet.setAnimalTypeId(searchForm.getAnimalTypeId());

        // 検索処理
        List<Pet> resultList = petService.find(pet);

        if (resultList.isEmpty()) {
            // 検索結果が無い場合
            String errMsg = messageSource.getMessage("search.error", null, Locale.getDefault());
            model.addAttribute("errMsg", errMsg);
            return "/pets/search";
        } else {
            // セッションに検索結果を保存
            sessionInfo.setPetSearchResult(resultList);
            return "/pets/searchResult";
        }
    }

    /*
     * 詳細画面
     */
    @GetMapping("/pets/detail")
    public String detail(Integer id, @ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }
        // 対象データ取得
        Pet pet = petService.findById(id);

        // データセット
        model.addAttribute("pet", pet);

        if (pet.getUserId() == sessionInfo.getLoginUser().getId()) {
            session.setAttribute("canDelete", true);
        } else {
            session.setAttribute("canDelete", false);
        }
        session.setAttribute("deleteId", id);

        return "/pets/detail";
    }

    @GetMapping("/pets/register")
    public String register(@ModelAttribute("registerForm") RegisterForm registerForm,
            @ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }

        return "/pets/register";
    }

    @PostMapping("/pets/register")
    public String registerExecute(@Validated @ModelAttribute("registerForm") RegisterForm form,
            BindingResult bindingResult,
            @ModelAttribute("loginForm") LoginForm loginForm,
            Model model) {

        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }

        // 入力値チェック
        if (bindingResult.hasErrors()) {
            return "/pets/register";
        }

        // 入力値をEntityにセット
        Pet student = new Pet();
        student.setAnimalTypeId(form.getAnimalTypeId());
        student.setDescription(form.getDescription());
        student.setName(form.getName());
        student.setUserId(sessionInfo.getLoginUser().getId());

        // 登録処理
        petService.insert(student);

        return "/pets/menu";
    }

    @PostMapping(value = "/pets/delete", params = "back")
    public String searchResultBack(Model model) {
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }
        return "/pets/searchResult";
    }

    @PostMapping(value = "/pets/delete", params = "delete")
    public String delete(@ModelAttribute("registerForm") RegisterForm form) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }
        petService.delete((Integer) session.getAttribute("deleteId"));
        session.setAttribute("deleteId", null);
        return "/pets/menu";
    }
}
