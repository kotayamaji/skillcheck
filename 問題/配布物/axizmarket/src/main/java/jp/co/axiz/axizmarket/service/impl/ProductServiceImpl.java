package jp.co.axiz.axizmarket.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.axizmarket.dao.ProductDao;
import jp.co.axiz.axizmarket.entity.Bookmark;
import jp.co.axiz.axizmarket.entity.Product;
import jp.co.axiz.axizmarket.entity.User;
import jp.co.axiz.axizmarket.service.ProductService;

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
        List<Product> resultList = productDao.find(product);
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public void insert(Integer book, User user) {
        Bookmark test = productDao.confirm(book, user);
        if (test == null) {
            productDao.insert(book, user);
        }

    }

    @Override
    public List<Product> findBook(User user) {
        return productDao.findBook(user);

    }

    @Override
    public void delete(User loginUser, Integer productId) {
        productDao.delete(loginUser, productId);
    }
}
