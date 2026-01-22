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

    private static final String SELECT = "SELECT * " + 
            "FROM products p " + 
            "JOIN categories c on p.category_id = c.category_id " +
            "JOIN users u on p.user_id = u.user_id " ;
    private static final String FINDBY = "SELECT user_id FROM products WHERE product_id = :productId ";

    private static final String INSERT = "INSERT INTO products (product_name, price , category_id , remarks ) VALUES (:productName , :price , :categoryId , : remarks );";

    private static final String ORDER_BY = " ORDER BY product_id ";

    private static final String SELECTBYID = "SELECT * " + 
            "FROM products p " + 
            "JOIN categories c on p.category_id = c.category_id " +
            "JOIN users u on p.user_id = u.user_id " +
            "WHERE p.product_id = :productId " ;

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

        // 検索条件の有無に応じて、sqlのWHERE句に指定する条件文、
        // Parameterをストックしていく。
        List<String> condition = new ArrayList<String>();
        MapSqlParameterSource param = new MapSqlParameterSource();

        Integer productid = product.getProductId();
        String productName = product.getProductName();
        Integer categoryId =  product.getCategoryId();
   /**
        if (productid != null) {
            condition.add("product_id = :productId");
            param.addValue("productId", productid);
        }
     */
        if (ParamUtil.isNullOrEmpty(productName) || categoryId == 0) {
            // 検索条件が無い場合は全検索
            return findAll();
        }

        if (categoryId != 0) {
            condition.add("p.category_Id = :categoryId ");
            param.addValue("categoryId", categoryId);
        }

        
        if (!ParamUtil.isNullOrEmpty(productName)) {
            condition.add("product_name LIKE :productName ");
            param.addValue("productName", "%" + productName + "%");
        }



        // WHERE句の文字列生成
        String whereString = String.join(" AND ", condition.toArray(new String[] {}));

        String deleted = " AND is_deleted = 0 ";

        // SQL文生成
        String sql = SELECT + "WHERE " + whereString + deleted + ORDER_BY;

        // SQL文実行
        List<Product> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));

        return resultList;
    }

    @Override
    public void insert(Product product) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("productName", product.getProductName());
        param.addValue("price", product.getPrice());
        param.addValue("categoryId", product.getCategoryId());
        param.addValue("remarks", product.getRemarks());

        jdbcTemplate.update(INSERT, param);
        
    }

    @Override
    public Integer finduser(Integer productId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("productId", productId);
        List<Product> product =  jdbcTemplate.query(FINDBY, param, new BeanPropertyRowMapper<Product>(Product.class));
        Integer result = product.get(0).getUserId();

        return result;
    }

    @Override
    public Product findbyId(Product product) {
        MapSqlParameterSource param = new MapSqlParameterSource();

        String deleted = " AND is_deleted = 0 ";

        param.addValue("productId",product.getProductId());

        // SQL文生成
        String sql = SELECTBYID + deleted + ORDER_BY;

        // SQL文実行
        Product result = jdbcTemplate.queryForObject(sql, param, new BeanPropertyRowMapper<Product>(Product.class));

        return result;
    }

    

}
