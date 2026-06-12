package com.demo;

import com.demo.controller.CartController;
import com.demo.controller.ProductController;

import java.util.Scanner;

/**
 * 京东商城主程序
 * 提供商品管理和购物车功能的控制台交互界面
 */
public class main {
    static ProductController productController = new ProductController();
    static CartController cartController = new CartController();
    static Scanner scr = new Scanner(System.in);

    /**
     * 程序入口，显示主菜单并处理用户选择
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {


        while (true) {
            shouMenu();
            int choice = scr.nextInt();
            switch (choice) {
                case 1:
                    productController.addProduct();
                    break;
                case 2:
                    productController.showAllProducts();
                    break;
                case 3:
                    productController.findProductById();
                    break;
                case 4:
                    cartController.addToCart();
                    break;
                case 5:
                    cartController.showCart();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }


    }

    /**
     * 显示主菜单
     */
    static void shouMenu() {
        System.out.println("");
        System.out.println("*****欢迎进入京东商城*****");
        System.out.println("\t1.添加商品");
        System.out.println("\t2.查看所有商品");
        System.out.println("\t3.查找指定编号商品");
        System.out.println("\t4.添加商品到购物车");
        System.out.println("\t5.查看购物车");
        System.out.println("\t6.退出");

        System.out.println("***************************");
        System.out.print("请选择菜单：");


    }
}