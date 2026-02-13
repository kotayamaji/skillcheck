package jp.co.axiz.axizmarket.controller;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.axiz.axizmarket.entity.Category;
import jp.co.axiz.axizmarket.entity.Product;
import jp.co.axiz.axizmarket.entity.SessionInfo;
import jp.co.axiz.axizmarket.form.LoginForm;
import jp.co.axiz.axizmarket.form.SearchForm;
import jp.co.axiz.axizmarket.service.CategoryService;
import jp.co.axiz.axizmarket.service.ProductService;
import jp.co.axiz.axizmarket.util.ParamUtil;

/*
 * 商品関連コントローラ
 */
@Controller
public class ProductsController {

    /*
     * messages_ja.propertiesのメッセージ取得用
     */
    MessageSource messageSource;

    /*
     * セッション情報
     */
    HttpSession session;

    /*
     * 分類サービス
     */
    private CategoryService categoryService;

    /*
     * 商品サービス
     */
    private ProductService productService;

    public ProductsController(MessageSource messageSource, HttpSession session, CategoryService categoryService,
            ProductService productService) {
        this.messageSource = messageSource;
        this.session = session;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    /*
     * トップ画面
     */
    @GetMapping("/")
    public String index(@ModelAttribute("searchForm") SearchForm searchForm, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        // 分類一覧を取得、セット
        List<Category> categoryList = categoryService.findAll();
        sessionInfo.setCategoryList(categoryList);

        // 検索条件をEntityにセット
        Product product = new Product();
        product.setProductName(searchForm.getProductName());
        product.setCategoryId(searchForm.getCategoryId());

        // 検索処理
        List<Product> resultList = productService.find(product);
        model.addAttribute("productSearchResult", resultList);

        // セッションに保存
        session.setAttribute("sessionInfo", sessionInfo);

        return "/index";
    }

    /*
     * 詳細画面
     */
    @GetMapping("/products/detail")
    public String detail(Integer productId, @ModelAttribute("loginForm") LoginForm loginForm,
            @ModelAttribute("searchForm") SearchForm searchform, Model model) {
        // 対象データ取得
        Product product = productService.findByProductId(productId);

        // データセット
        model.addAttribute("product", product);

        return "/products/detail";
    }

    @PostMapping(value = "/products/detail", params = "book")
    public String book(Integer productId, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        productService.insert(productId, sessionInfo.getLoginUser());

        return "redirect:/";
    }

    @GetMapping("/book")
    public String book(@ModelAttribute("searchForm") SearchForm searchForm, Model model) {

        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);
        if (sessionInfo.getLoginUser() == null) {
            // ログインしていない場合はトップに戻る
            return "redirect:/";
        }

        // 分類一覧を取得、セット
        List<Category> categoryList = categoryService.findAll();
        sessionInfo.setCategoryList(categoryList);

        // // 検索条件をEntityにセット
        // Product product = new Product();
        // product.setProductName(searchForm.getProductName());
        // product.setCategoryId(searchForm.getCategoryId());

        // 検索処理
        List<Product> resultList = productService.findBook(sessionInfo.getLoginUser());
        model.addAttribute("productSearchResult", resultList);

        // セッションに保存
        session.setAttribute("sessionInfo", sessionInfo);

        return "redirect:/";
    }

    @GetMapping("/bookoff")
    public String bookoff(Integer productId, @ModelAttribute("searchForm") SearchForm searchForm, Model model) {
        // セッション情報を取得
        SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

        // 分類一覧を取得、セット
        List<Category> categoryList = categoryService.findAll();
        sessionInfo.setCategoryList(categoryList);

        // // 検索条件をEntityにセット
        // Product product = new Product();
        // product.setProductName(searchForm.getProductName());
        // product.setCategoryId(searchForm.getCategoryId());

        // 検索処理
        productService.delete(sessionInfo.getLoginUser(), productId);
        // model.addAttribute("productSearchResult", resultList);

        // セッションに保存
        session.setAttribute("sessionInfo", sessionInfo);

        return "/products/book";
    }

}
