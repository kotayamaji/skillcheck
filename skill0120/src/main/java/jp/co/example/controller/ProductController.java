package jp.co.example.controller;

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

import jp.co.example.controller.form.ProductForm;
import jp.co.example.entity.Product;
import jp.co.example.service.ProductService;

/**
 * コントローラ
 */
@Controller
public class ProductController {

    /**
     * メッセージリソース用
     */
    @Autowired
    MessageSource messageSource;

    /**
     * productsテーブル用サービス
     */
    @Autowired
    ProductService productService;

    /**
     * トップ画面表示
     */
    @GetMapping("/top")
    public String index(@ModelAttribute("product") ProductForm form, Model model) {
        return "top";
    }

    /**
     * 検索結果画面遷移
     */
    @PostMapping(value = "/execute", params = "search")
    public String search(@Validated @ModelAttribute("product") ProductForm form, BindingResult bindingResult, Model model) {
        Product product = new Product(form.getProductId(), form.getProductName(), form.getPrice());

        List<Product> list = productService.find(product);

        if (list.isEmpty()) {
            // メッセージリソースファイルから、メッセージを取得
            String errMsg = messageSource.getMessage("select.error", null, Locale.getDefault());
            model.addAttribute("msg", errMsg);

            return "top";
        } else {
            model.addAttribute("productList", list);

            return "searchResult";
        }

    }

    /**
     * 登録完了画面遷移
     */
    @PostMapping(value = "/execute", params = "insert")
    public String insert(@Validated @ModelAttribute("product") ProductForm form, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "top";
        }

        Product product = new Product(form.getProductId(), form.getProductName(), form.getPrice());

        productService.insert(product);

        return "insertResult";
    }
}
