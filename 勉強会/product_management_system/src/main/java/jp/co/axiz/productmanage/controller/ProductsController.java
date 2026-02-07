package jp.co.axiz.productmanage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jakarta.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.axiz.productmanage.entity.Category;
import jp.co.axiz.productmanage.entity.Product;
import jp.co.axiz.productmanage.entity.SessionInfo;
import jp.co.axiz.productmanage.form.InsertForm;
import jp.co.axiz.productmanage.form.LoginForm;
import jp.co.axiz.productmanage.form.SearchForm;
import jp.co.axiz.productmanage.service.ProductService;
import jp.co.axiz.productmanage.util.ParamUtil;

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
	 * 商品サービス
	 */
	private ProductService productService;
	private CategoryService categoryService;

	public ProductsController(MessageSource messageSource, HttpSession session, ProductService productService,
			CategoryService categoryService) {
		super();
		this.messageSource = messageSource;
		this.session = session;
		this.productService = productService;
		this.categoryService = categoryService;
	}

	/*
	 * 商品初期画面
	 */
	@GetMapping("/products/menu")
	public String index(@ModelAttribute("loginForm") LoginForm form, Model model) {
		// セッション情報を取得
		SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

		if (sessionInfo.getLoginUser() == null) {
			// ログインしていない場合はトップに戻る
			return "/index";
		}

		return "/products/menu";
	}

	/*
	 * 検索画面
	 */
	@GetMapping("/products/search")
	public String search(@ModelAttribute("searchForm") SearchForm searchForm,
			@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
		// セッション情報を取得
		SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

		if (sessionInfo.getLoginUser() == null) {
			// ログインしていない場合はトップに戻る
			return "/index";
		}

		List<Category> categoryList = categoryService.findCategories();

		List<Category> resultList = new ArrayList<>();
		resultList.add(new Category(null, "すべて"));

		resultList.addAll(categoryList);

		sessionInfo.setCategoryList(resultList);

		return "/products/search";
	}

	/*
	 * 検索結果画面
	 */
	@GetMapping("/products/searchResult")
	public String searchResult(@ModelAttribute("searchForm") SearchForm searchForm,
			@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
		// セッション情報を取得
		SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

		if (sessionInfo.getLoginUser() == null) {
			// ログインしていない場合はトップに戻る
			return "/index";
		}

		// 検索条件をEntityにセット
		Product product = new Product();
		product.setProductName(searchForm.getProductName());
		product.setCategoryId(searchForm.getCategoryId());

		// 検索処理
		List<Product> resultList = productService.find(product);

		if (resultList.isEmpty()) {
			// 検索結果が無い場合
			String errMsg = messageSource.getMessage("search.error", null, Locale.getDefault());
			model.addAttribute("errMsg", errMsg);
			return "/products/search";
		} else {
			// セッションに検索結果を保存
			sessionInfo.setProductSearchResult(resultList);
			return "/products/searchResult";
		}
	}

	/*
	 * 詳細画面
	 */
	@GetMapping("/products/detail")
	public String detail(Integer productId, @ModelAttribute("loginForm") LoginForm loginForm, Model model) {
		// セッション情報を取得
		SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

		if (sessionInfo.getLoginUser() == null) {
			// ログインしていない場合はトップに戻る
			return "/index";
		}

		// 対象データ取得
		Product product = productService.findByProductId(productId);

		// データセット
		model.addAttribute("product", product);

		return "/products/detail";
	}

	@GetMapping("/products/insert")
	public String insert(@ModelAttribute("insertForm") InsertForm insertForm,
			@ModelAttribute("loginForm") LoginForm loginForm,
			Model model) {
		// セッション情報を取得
		SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

		if (sessionInfo.getLoginUser() == null) {
			// ログインしていない場合はトップに戻る
			return "/index";
		}

		List<Category> categoryList = categoryService.findCategories();
		model.addAttribute("categoryList", categoryList);

		return "/products/insert";
	}

	@PostMapping("/products/insertExec")
	public String insertExec(@Validated @ModelAttribute("insertForm") InsertForm insertForm,
			BindingResult bindingResult,
			@ModelAttribute("loginForm") LoginForm loginForm,
			Model model) {
		// セッション情報を取得
		SessionInfo sessionInfo = ParamUtil.getSessionInfo(session);

		if (sessionInfo.getLoginUser() == null) {
			// ログインしていない場合はトップに戻る
			return "/index";
		}

		if (bindingResult.hasErrors()) {
			return "/products/insert";
		}

		productService.insert(insertForm, sessionInfo.getLoginUser().getUserId());

		return "/products/menu";
	}

	/*
	 * 検索結果画面へ戻る (詳細画面の「戻る」ボタン押下時)
	 */
	@PostMapping(value = "/products/delete", params = "back")
	public String searchResultBack(Model model) {
		return "/products/searchResult";
	}

}
