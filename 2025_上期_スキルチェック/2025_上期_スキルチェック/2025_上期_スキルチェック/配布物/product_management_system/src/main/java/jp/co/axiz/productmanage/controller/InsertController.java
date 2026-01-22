package jp.co.axiz.productmanage.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.axiz.productmanage.entity.Product;
import jp.co.axiz.productmanage.entity.SessionInfo;
import jp.co.axiz.productmanage.entity.User;
import jp.co.axiz.productmanage.entity.categories;
import jp.co.axiz.productmanage.form.InsertForm;
import jp.co.axiz.productmanage.form.LoginForm;
import jp.co.axiz.productmanage.service.ProductService;
import jp.co.axiz.productmanage.service.UserService;
import jp.co.axiz.productmanage.util.ParamUtil;

/*
 * 認証処理関連コントローラ
 */
@Controller
public class InsertController {

    /*
     * messages_ja.propertiesのメッセージ取得用
     */
    private final MessageSource messageSource;

    /*
     * セッション情報
     */
    HttpSession session;

    /*
     * ユーザーサービス
     */

    private ProductService productService;

    public InsertController(MessageSource messageSource,HttpSession session, ProductService productService) {
        this.messageSource = messageSource;
        this.session = session;
        this.productService = productService;
    }

    /*
     * 登録画面表示
     */
    @GetMapping("/products/insert")
    public String index(@ModelAttribute("insertForm") InsertForm form, Model model) {
        return "/products/insert";
    }

    /*
     * ログイン処理
     */
    @PostMapping(value = "/insert" , params ="insert")
    public String insert(@Validated @ModelAttribute("insertForm") InsertForm form,BindingResult bindingResult, Model model) {

        // エラーメッセージ
        String errMsg = messageSource.getMessage("login.error", null, Locale.getDefault());

        if (bindingResult.hasErrors()) {
            return "insert";
        }

        Product product = new Product(form.getProductName(),form.getPrice(),form.getCategoryId(),form.getRemarks());
        productService.insert(product);

        return "menu";

    }


}

