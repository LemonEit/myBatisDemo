package com.demo.service;

import com.demo.domain.Product;

import java.util.List;

/**
 * 商品业务逻辑接口
 * 定义商品相关的业务方法
 */
public interface ProductService {
    /**
     * 查询所有商品
     *
     * @return 商品列表
     */
    List<Product> getAllProducts();

    /**
     * 根据id查询商品
     *
     * @param id 商品编号
     * @return 商品对象
     */
    Product getProductById(int id);

    /**
     * 添加商品
     *
     * @param product 商品对象
     * @return 影响行数
     */
    int addProduct(Product product);
}
