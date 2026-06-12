package com.demo.mapper;

import com.demo.domain.Cart_item;

/**
 * 购物车商品Mapper接口
 * 定义购物车商品的数据库操作方法
 */
public interface Cart_itemMapper {
    /**
     * 购物车添加商品，如果商品已存在，则增加数量，否则插入新记录
     */
    public void addCart_item(Cart_item cart_item);

    /**
     * 根据商品编号查询购物车商品信息
     */
    public Cart_item selectByProductId(int product_id);

    /**
     * 更新购物车商品信息商品数量和小计
     */
    public void updateCart_item(Cart_item cart_item);

    /***
     * 展示购物车
     */
    public Cart_item selectAll();


}
