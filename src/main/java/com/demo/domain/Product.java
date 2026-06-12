package com.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商品实体类
 * 用于封装商品的基本信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 颜色
     */
    private String color;
    /**
     * 尺寸
     */
    private String size;
    /**
     * 库存
     */
    private Integer stock;
}
