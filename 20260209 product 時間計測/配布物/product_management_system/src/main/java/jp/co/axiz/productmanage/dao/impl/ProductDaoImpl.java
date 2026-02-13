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

    private static final String SELECT = "SELECT users.user_name, users.password, users.disp_name userDispName , users.role_id, users.is_active, roles.role_name, products.product_id, products.product_name, products.price, product_management_system.products.remarks, product_management_system.products.user_id, product_management_system.products.is_deleted, product_management_system.categories.category_name, product_management_system.products.category_id FROM users INNER JOIN roles ON roles.role_id = users.role_id INNER JOIN product_management_system.products ON product_management_system.products.user_id = users.user_id INNER JOIN product_management_system.categories ON product_management_system.products.category_id = product_management_system.categories.category_id ";

    private static final String ORDER_BY = " ORDER BY product_id";
    private static final String INSERT = "INSERT INTO product_management_system.products(product_name, price, category_id, remarks, user_id ) VALUES (:product_name, :price, :category_id, :remarks , :userId )";

    private static final String DELETE = "UPDATE product_management_system.products SET is_deleted = '1' WHERE product_id = :product_id";

    public ProductDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 全件取得
     */
    @Override
    public List<Product> findAll() {
        List<Product> resultList = jdbcTemplate.query(SELECT + " WHERE is_deleted = 0 " + ORDER_BY,
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

        if (categoryId != null) {
            if (categoryId != 0) {
                condition.add("products.category_id = :categoryId");
                param.addValue("categoryId", categoryId);
            }
        }

        // WHERE句の文字列生成
        String whereString = String.join(" AND ", condition.toArray(new String[] {}));

        // SQL文生成
        String sql = SELECT + "WHERE " + whereString + " AND is_deleted = 0 " + ORDER_BY;

        // SQL文実行
        List<Product> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));

        return resultList;
    }

    @Override
    public void insert(Product student) {
        // :product_name, :price, :category_id, :remarks :userId
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("product_name", student.getProductName());
        param.addValue("price", student.getPrice());
        param.addValue("category_id", student.getCategoryId());
        param.addValue("remarks", student.getRemarks());
        param.addValue("userId", student.getUserId());
        jdbcTemplate.update(INSERT, param);
    }

    @Override
    public void delete(Integer attribute) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("product_id", attribute);

        jdbcTemplate.update(DELETE, param);

    }

}
