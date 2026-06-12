package com.demo.dao;

import com.demo.domain.Cart_item;

import java.util.List;

/**
 * 购物车商品数据访问对象接口
 * 定义购物车商品的数据库操作方法
 */
public interface CartItemDao {
    /**
     * 购物车添加商品，如果商品已存在，则增加数量，否则插入新记录
     */
    void addCart_item(Cart_item cart_item);

    /**
     * 根据商品编号查询购物车商品信息
     */
    Cart_item selectByProductId(int product_id);

    /**
     * 更新购物车商品信息商品数量和小计
     */
    void updateCart_item(Cart_item cart_item);

    /**
     * 查询所有购物车商品
     * 
     * @return 购物车商品列表
     */
    List<Cart_item> selectAll();
}
