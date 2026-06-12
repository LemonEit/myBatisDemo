package com.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 购物车商品实体类
 * 用于封装购物车中商品的信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart_item {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 数量
     */
    private Integer amount;
    /**
     * 小计
     */
    private BigDecimal subtotal;
}
