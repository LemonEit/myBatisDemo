package com.demo.controller;

import com.demo.domain.Product;
import com.demo.service.ProductService;
import com.demo.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * 商品控制器
 * 处理商品相关的用户交互
 */
public class ProductController {
    
    private ProductService productService;
    private Scanner scanner;
    
    public ProductController() {
        this.productService = new ProductServiceImpl();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 添加商品
     */
    public void addProduct() {
        System.out.println("请输入商品名称：");
        String name = scanner.next();
        System.out.println("请输入商品价格：");
        double price = scanner.nextDouble();
        System.out.println("请输入商品颜色：");
        String color = scanner.next();
        System.out.println("请输入商品尺码：");
        String size = scanner.next();
        System.out.println("请输入商品库存：");
        int stock = scanner.nextInt();
        
        Product product = new Product();
        product.setName(name);
        product.setPrice(new BigDecimal(price));
        product.setColor(color);
        product.setSize(size);
        product.setStock(stock);
        
        try {
            int result = productService.addProduct(product);
            System.out.println("添加成功，商品信息为：" + product);
        } catch (IllegalArgumentException e) {
            System.out.println("添加失败：" + e.getMessage());
        }
    }
    
    /**
     * 显示所有商品
     */
    public void showAllProducts() {
        List<Product> products = productService.getAllProducts();
        
        if (products.isEmpty()) {
            System.out.println("暂无商品");
        } else {
            System.out.println("商品列表：");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
    
    /**
     * 根据编号查找商品
     */
    public void findProductById() {
        System.out.println("请输入商品编号：");
        int id = scanner.nextInt();
        
        Product product = productService.getProductById(id);
        if (product != null) {
            System.out.println("商品信息：" + product);
        } else {
            System.out.println("未找到编号为 " + id + " 的商品");
        }
    }
}
