package com.demo.dao;

import com.demo.domain.Product;

import java.util.List;

/**
 * 商品数据访问对象接口
 * 定义商品的数据库操作方法
 */
public interface ProductDao {
    /**
     * 查询所有商品
     *
     * @return 商品列表
     */
    List<Product> selectAll();

    /**
     * 根据id查询商品
     *
     * @param id 商品编号
     * @return 商品对象
     */
    Product selectById(int id);

    /**
     * 添加商品
     *
     * @param product 商品对象
     * @return 影响行数
     */
    int addProduct(Product product);
}
