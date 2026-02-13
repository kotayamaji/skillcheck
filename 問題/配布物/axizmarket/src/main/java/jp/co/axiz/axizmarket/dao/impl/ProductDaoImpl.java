package jp.co.axiz.axizmarket.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.axizmarket.dao.ProductDao;
import jp.co.axiz.axizmarket.entity.Bookmark;
import jp.co.axiz.axizmarket.entity.Product;
import jp.co.axiz.axizmarket.entity.User;
import jp.co.axiz.axizmarket.util.ParamUtil;

/*
 * productsテーブル用DAO
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SELECT = "SELECT products.product_id, products.product_name, products.price, products.remarks, categories.category_name, products.category_id FROM products INNER JOIN categories ON products.category_id = categories.category_id "
            +
            "WHERE products.is_deleted = FALSE ";

    private static final String ORDER_BY = " ORDER BY products.product_id";

    private static final String INSERT = " INSERT INTO bookmarks(user_id, product_id) VALUES (:userId, :product_id) ";

    private static final String CONFIRM = " SELECT user_id, product_id FROM bookmarks WHERE user_id = :userId AND product_id = :product_id ";

    private static final String BOOK = " SELECT bookmarks.product_id, bookmarks.user_id, products.price, products.product_name, products.remarks, products.category_id, products.is_deleted, categories.category_name FROM bookmarks INNER JOIN products ON bookmarks.product_id = products.product_id INNER JOIN categories ON categories.category_id = products.category_id WHERE bookmarks.user_id = :bookmarks.user_id AND products.is_deleted = 0 ";

    private static final String DELETE = "DELETE FROM bookmarks WHERE user_id = :user_id and product_id = :product_id ";

    public ProductDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 全件取得
     */
    @Override
    public List<Product> findAll() {
        List<Product> resultList = jdbcTemplate.query(SELECT + ORDER_BY,
                new BeanPropertyRowMapper<Product>(Product.class));

        return resultList;
    }

    /**
     * 条件を指定した検索
     */
    @Override
    public List<Product> find(Product product) {

        if (product.getCategoryId() == null) {
            return findAll();
        }

        if (product.getCategoryId() == 0 && product.getProductName() == "") {
            // 検索条件が無い場合は全検索
            return findAll();
        }

        // 検索条件の有無に応じて、sqlのWHERE句に指定する条件文、
        // Parameterをストックしていく。
        List<String> condition = new ArrayList<String>();
        MapSqlParameterSource param = new MapSqlParameterSource();

        Integer productid = product.getProductId();
        String productName = product.getProductName();

        if (productid != null) {
            condition.add("products.product_id = :productId");
            param.addValue("productId", productid);
        }

        if (!ParamUtil.isNullOrEmpty(productName)) {
            condition.add("products.product_name LIKE :productName");
            param.addValue("productName", "%" + productName + "%");
        }
        if (product.getCategoryId() != null) {
            if (product.getCategoryId() != 0) {
                condition.add("products.category_id = :categoryId");
                param.addValue("categoryId", product.getCategoryId());
            }
        }

        // WHERE句の文字列生成
        String whereString = String.join(" AND ", condition.toArray(new String[] {}));

        // SQL文生成
        String sql = SELECT + " AND " + whereString + ORDER_BY;

        // SQL文実行
        List<Product> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));

        return resultList;
    }

    @Override
    public void insert(Integer book, User user) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        // :user_id, :product_id

        param.addValue("userId", user.getUserId());
        param.addValue("product_id", book);

        jdbcTemplate.update(INSERT, param);
    }

    @Override
    public Bookmark confirm(Integer book, User user) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        // :user_id, :product_id

        param.addValue("product_id", book);
        param.addValue("userId", user.getUserId());

        List<Bookmark> test;
        test = jdbcTemplate.query(CONFIRM, param,
                new BeanPropertyRowMapper<Bookmark>(Bookmark.class));

        // private static final String CONFIRM = " SELECT user_id, product_id FROM
        // bookmarks WHERE user_id = :user_id and product_id = :product_id ";

        return test.isEmpty() ? null : test.get(0);

    }

    @Override
    public List<Product> findBook(User user) {
        // TODO Auto-generated method stub
        MapSqlParameterSource param = new MapSqlParameterSource();
        // :user_id, :product_id
        List<Bookmark> test;
        param.addValue("bookmarks.user_id", user.getUserId());

        // SQL文実行
        List<Product> resultList = jdbcTemplate.query(BOOK, param, new BeanPropertyRowMapper<Product>(Product.class));

        return resultList;

    }

    @Override
    public void delete(User loginUser, Integer productId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        // :user_id, :product_id

        param.addValue("user_id", loginUser.getUserId());
        param.addValue("product_id", productId);

        jdbcTemplate.update(DELETE, param);
    }

}
