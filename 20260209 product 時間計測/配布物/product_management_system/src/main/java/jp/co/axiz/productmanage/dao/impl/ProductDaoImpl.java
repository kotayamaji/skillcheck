package jp.co.axiz.productmanage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.productmanage.dao.ProductDao;
import jp.co.axiz.productmanage.entity.Product;
import jp.co.axiz.productmanage.util.ParamUtil;

/*
 * user_infoテーブル用DAO
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SELECT = "SELECT users.user_name, users.password, users.disp_name, users.role_id, users.is_active, roles.role_name, products.product_id, products.product_name, products.price, product_management_system.products.remarks, product_management_system.products.user_id, product_management_system.products.is_deleted, product_management_system.categories.category_name, product_management_system.products.category_id FROM users INNER JOIN roles ON roles.role_id = users.role_id INNER JOIN product_management_system.products ON product_management_system.products.user_id = users.user_id INNER JOIN product_management_system.categories ON product_management_system.products.category_id = product_management_system.categories.category_id ";

    private static final String ORDER_BY = " ORDER BY product_id";

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
        if (product.getCategoryId() != null) {
            if (product.getCategoryId() == 0) {

                if (product.getCategoryId() == 0 && product.getProductName() == "") {
                    return findAll();
                }

            }
        }

        // if (product == null || product.isEmptyCondition()) {
        // // 検索条件が無い場合は全検索
        // return findAll();
        // }

        // 検索条件の有無に応じて、sqlのWHERE句に指定する条件文、
        // Parameterをストックしていく。
        List<String> condition = new ArrayList<String>();
        MapSqlParameterSource param = new MapSqlParameterSource();

        Integer productid = product.getProductId();
        String productName = product.getProductName();
        Integer categoryId = product.getCategoryId();

        if (productid != null) {
            condition.add("products.product_id = :productId");
            param.addValue("productId", productid);
        }

        if (!ParamUtil.isNullOrEmpty(productName)) {
            condition.add("products.product_name LIKE :productName");
            param.addValue("productName", "%" + productName + "%");
        }

        if (categoryId != 0) {
            condition.add("products.category_id = :categoryId");
            param.addValue("categoryId", categoryId);
        }

        // WHERE句の文字列生成
        String whereString = String.join(" AND ", condition.toArray(new String[] {}));

        // SQL文生成
        String sql = SELECT + "WHERE " + whereString + ORDER_BY;

        // SQL文実行
        List<Product> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));

        return resultList;
    }

}
