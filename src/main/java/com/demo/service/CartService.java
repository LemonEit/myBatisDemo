package com.demo.service;

import com.demo.domain.Cart_item;

import java.util.List;

/**
 * 购物车业务逻辑接口
 * 定义购物车相关的业务方法
 */
public interface CartService {
    /**
     * 添加商品到购物车
     * 如果商品已存在则增加数量，否则新增商品
     *
     * @param productId 商品编号
     * @param quantity  数量
     * @return 是否添加成功
     */
    boolean addToCart(int productId, int quantity);

    /**
     * 获取购物车所有商品
     *
     * @return 购物车商品列表
     */
    List<Cart_item> getCartItems();

    /**
     * 根据商品编号查询购物车商品
     *
     * @param productId 商品编号
     * @return 购物车商品对象
     */
    Cart_item getCartItemByProductId(int productId);

    /**
     * 更新购物车商品数量和小计
     *
     * @param cartItem 购物车商品对象
     * @return 是否更新成功
     */
    boolean updateCartItem(Cart_item cartItem);
}
