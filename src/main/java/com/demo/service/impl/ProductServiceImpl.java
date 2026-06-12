package com.demo.service.impl;

import com.demo.dao.ProductDao;
import com.demo.dao.impl.ProductDaoImpl;
import com.demo.domain.Product;
import com.demo.service.ProductService;

import java.util.List;

/**
 * 商品业务逻辑实现类
 * 实现商品相关的业务方法
 */
public class ProductServiceImpl implements ProductService {
    
    private ProductDao productDao;
    
    public ProductServiceImpl() {
        this.productDao = new ProductDaoImpl();
    }
    
    @Override
    public List<Product> getAllProducts() {
        return productDao.selectAll();
    }
    
    @Override
    public Product getProductById(int id) {
        return productDao.selectById(id);
    }
    
    @Override
    public int addProduct(Product product) {
        // 可以在这里添加业务逻辑，比如验证商品信息
        if (product == null) {
            throw new IllegalArgumentException("商品信息不能为空");
        }
        if (product.getPrice() == null || product.getPrice().doubleValue() <= 0) {
            throw new IllegalArgumentException("商品价格必须大于0");
        }
        if (product.getStock() == null || product.getStock() < 0) {
            throw new IllegalArgumentException("商品库存不能为负数");
        }
        
        return productDao.addProduct(product);
    }
}
