package com.demo.controller;

import com.demo.domain.Cart_item;
import com.demo.service.CartService;
import com.demo.service.impl.CartServiceImpl;

import java.util.List;
import java.util.Scanner;

/**
 * 购物车控制器
 * 处理购物车相关的用户交互
 */
public class CartController {
    
    private CartService cartService;
    private Scanner scanner;
    
    public CartController() {
        this.cartService = new CartServiceImpl();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 添加商品到购物车
     */
    public void addToCart() {
        System.out.println("请输入商品编号：");
        int productId = scanner.nextInt();
        System.out.println("请输入商品数量：");
        int quantity = scanner.nextInt();
        
        boolean success = cartService.addToCart(productId, quantity);
        if (success) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败，商品不存在");
        }
    }
    
    /**
     * 显示购物车内容
     */
    public void showCart() {
        List<Cart_item> cartItems = cartService.getCartItems();
        
        if (cartItems.isEmpty()) {
            System.out.println("购物车为空");
        } else {
            System.out.println("购物车商品列表：");
            for (Cart_item item : cartItems) {
                System.out.println(item);
            }
        }
    }
}
