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
import jp.co.axiz.studentmanage.entity.SessionInfo;
import jp.co.axiz.studentmanage.entity.Student;
import jp.co.axiz.studentmanage.form.LoginForm;
import jp.co.axiz.studentmanage.form.RegisterForm;
import jp.co.axiz.studentmanage.form.SearchForm;
import jp.co.axiz.studentmanage.service.StudentService;
import jp.co.axiz.studentmanage.util.ParamUtil;

/*
 * 学生関連コントローラ
 */
@Controller
public class StudentsController {

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
     * 学生サービス
     */
    @Autowired
    private StudentService studentService;

    /*
     * 学生初期画面
     */
    @GetMapping("/students/menu")
    public String index(@ModelAttribute("loginForm") LoginForm form, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }

        return "/students/menu";
    }

    /*
     * 検索画面
     */
    @GetMapping("/students/search")
    public String search(@ModelAttribute("searchForm") SearchForm searchForm,
            @ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }

        return "/students/search";
    }

    /*
     * 検索結果画面
     */
    @GetMapping("/students/searchResult")
    public String searchResult(@Validated @ModelAttribute("searchForm") SearchForm searchForm,
            BindingResult bindingResult,
            @ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }

        if (bindingResult.hasErrors()) {
            return "/students/search";
        }

        // 検索条件をEntityにセット
        Student student = new Student();
        student.setStudentName(searchForm.getStudentName());
        student.setGrade(searchForm.getGrade());

        // 検索処理
        List<Student> resultList = studentService.find(student);

        if (resultList.isEmpty()) {
            // 検索結果が無い場合
            String errMsg = messageSource.getMessage("search.error", null, Locale.getDefault());
            model.addAttribute("errMsg", errMsg);
            return "/students/search";
        } else {
            // セッションに検索結果を保存
            sessionInfo.setStudentSearchResult(resultList);
            return "/students/searchResult";
        }
    }

    /*
     * 削除処理
     */
    @PostMapping(value = "/students/searchResult", params = "deleteStudentId")
    public String deleteExecute(Integer deleteStudentId, Model model) {
        // 削除処理
        studentService.delete(deleteStudentId);

        return "/students/menu";
    }

    /*
     * 更新画面
     */
    @PostMapping(value = "/students/searchResult", params = "updateStudentId")
    public String updateExecute(Integer updateStudentId, @ModelAttribute("registerForm") RegisterForm form,
            Model model) {
        // 更新画面に遷移
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }

        Student student = studentService.findById(updateStudentId);

        form.setStudentName(student.getStudentName());
        form.setGrade(student.getGrade());
        form.setHometown(student.getHometown());
        form.setMajorId(student.getMajorId());

        session.setAttribute("updateStudentId", updateStudentId);

        return "/students/update";
    }

    /*
     * 更新処理
     */
    @PostMapping(value = "/students/update", params = "edit")
    public String updateExecute(@Validated @ModelAttribute("registerForm") RegisterForm form,
            @ModelAttribute("searchForm") SearchForm searchForm,
            @ModelAttribute("loginForm") LoginForm loginForm,
            BindingResult bindingResult, Model model) {

        // 入力値チェック
        if (bindingResult.hasErrors()) {
            return "/students/update";
        }

        // 入力値をEntityにセット
        Student student = new Student();
        student.setStudentName(form.getStudentName());
        student.setGrade(form.getGrade());
        student.setHometown(form.getHometown());
        student.setMajorId(form.getMajorId());
        student.setStudentId((Integer) session.getAttribute("updateStudentId"));

        // 登録処理
        studentService.update(student);

        return "/students/menu";
    }

    /*
     * 編集画面から検索画面へ戻る
     */
    @PostMapping(value = "/students/updateResult", params = "back")
    public String back(Integer updateStudentId, @ModelAttribute("registerForm") RegisterForm form,
            @ModelAttribute("searchForm") SearchForm searchForm, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }

        return "/students/search";
    }

    /*
     * 登録画面
     */
    @GetMapping("/students/register")
    public String register(@ModelAttribute("registerForm") RegisterForm registerForm,
            @ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "/index";
        }

        return "/students/register";
    }

    /*
     * 登録処理
     */
    @PostMapping("/students/register")
    public String registerExecute(@Validated @ModelAttribute("registerForm") RegisterForm form,
            BindingResult bindingResult, Model model) {

        // 入力値チェック
        if (bindingResult.hasErrors()) {
            return "/students/register";
        }

        // 入力値をEntityにセット
        Student student = new Student();
        student.setStudentName(form.getStudentName());
        student.setGrade(form.getGrade());
        student.setHometown(form.getHometown());
        student.setMajorId(form.getMajorId());

        // 登録処理
        studentService.insert(student);

        return "/students/menu";
    }

}
