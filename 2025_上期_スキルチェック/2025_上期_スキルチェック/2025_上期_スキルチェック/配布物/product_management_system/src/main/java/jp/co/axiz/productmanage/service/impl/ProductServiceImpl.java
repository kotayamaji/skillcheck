package jp.co.axiz.productmanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.productmanage.dao.ProductDao;
import jp.co.axiz.productmanage.entity.Product;
import jp.co.axiz.productmanage.service.ProductService;

/*
 * productsテーブル用サービス実装クラス
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public List<Product> find(Product product) {
        return productDao.find(product);
    }

    @Override
    public Product findByProductId(Integer productId) {
        // 検索条件をセット
        Product product = new Product();
        product.setProductId(productId);

        // データ取得
        Product result = productDao.findbyId(product);
        return result;
    }

    @Override
    public boolean findUser(Integer productId) {
        Product product = new Product();
        product.setProductId(productId);

                // データ取得
        Integer diff = productDao.finduser(productId);

        if (productId == diff){
            return true;
        } else{
            return false;
        }
        
    }

    @Override
    public void insert(Product product) {
        productDao.insert(product);
    }

}
