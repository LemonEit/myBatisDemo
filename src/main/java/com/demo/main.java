package com.demo;

import com.demo.domain.Cart_item;
import com.demo.domain.Product;
import com.demo.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * 京东商城主程序
 * 提供商品管理和购物车功能的控制台交互界面
 */
public class main {
    static SqlSession sqlSession = SqlSessionUtil.getSqlSession();
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
                    addProduct();
                    break;
                case 2:

                    showAllProducts();
                    break;
                case 3:

                    findProductById();
                    break;
                case 4:

                    addToCart();
                    break;
                case 5:
                    showCart();
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

    /**
     * 添加商品功能
     * 从控制台读取商品信息并保存到数据库
     */
    static void addProduct() {
        System.out.println("请输入商品名称：");
        String name = scr.next();
        System.out.println("请输入商品价格：");
        double price = scr.nextDouble();
        System.out.println("请输入商品颜色：");
        String color = scr.next();
        System.out.println("请输入商品尺码：");
        String size = scr.next();
        System.out.println("请输入商品库存：");
        int stock = scr.nextInt();

        Product product = new Product();
        product.setName(name);
        product.setPrice(new BigDecimal(price));
        product.setColor(color);
        product.setSize(size);
        product.setStock(stock);
        int result = sqlSession.insert("com.demo.mapper.ProductMapper.addProduct", product);
        System.out.println("添加成功，商品信息为：" + product);

    }

    /**
     * 显示所有商品
     * 从数据库查询所有商品并打印输出
     */
    static void showAllProducts() {
        /*测试查询所有商品*/
        List<Product> products = sqlSession.selectList("com.demo.mapper.ProductMapper.selectAll");

        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * 根据编号查找商品
     * 从控制台读取商品编号并查询对应商品
     */
    static void findProductById() {
        System.out.println("请输入商品编号：");
        int id = scr.nextInt();
        Product product = sqlSession.selectOne("com.demo.mapper.ProductMapper.selectById", id);
        System.out.println(product);
    }

    /**
     * 添加商品到购物车
     * 如果商品不存在则新增，如果已存在则增加数量
     */
    static void addToCart() {

        System.out.println("请输入商品编号：");
        int id0 = scr.nextInt();
        System.out.println("请输入商品数量：");
        int number0 = scr.nextInt();

        /**
         * 在商品表中查询商品编号为1的商品信息，如果存在则返回该商品信息，否则返回null
         */
        Product product = sqlSession.selectOne("com.demo.mapper.ProductMapper.selectById", id0);
        /**
         * 如果商品存在，并且购物车中不存在该商品，则创建一个购物车商品对象，并设置商品信息和数量
         * 如果商品存在，并且购物车中已存在该商品，则更新购物车商品对象的数量和小计
         * 如果商品不存在，则输出错误信息
         */
        if (product != null) {
            /*判断购物车中是否存在该商品*/
            Cart_item cart_item = sqlSession.selectOne("com.demo.mapper.Cart_itemMapper.selectByProductId", product.getId());
            if (cart_item == null) {
                /*购物车中不存在该商品，创建一个购物车商品对象，并设置商品信息和数量*/
                Cart_item product1 = new Cart_item();
                product1.setId(product.getId());
                product1.setName(product.getName());
                product1.setPrice(product.getPrice());
                product1.setAmount(number0);
                product1.setSubtotal(product.getPrice().multiply(new BigDecimal(number0)));
                /*将购物车商品对象添加到购物车表中*/
                int result = sqlSession.insert("com.demo.mapper.Cart_itemMapper.addCart_item", product1);
                System.out.println("添加成功，购物车信息为：" + product1);
            } else {
                /*购物车中已存在该商品，更新购物车商品对象的数量和小计*/
                cart_item.setAmount(cart_item.getAmount() + number0);
                cart_item.setSubtotal(cart_item.getPrice().multiply(new BigDecimal(cart_item.getAmount())));
                /*将更新后的购物车商品对象保存到购物车表中*/
                int result = sqlSession.update("com.demo.mapper.Cart_itemMapper.updateCart_item", cart_item);
                System.out.println("更新成功，购物车信息为：" + cart_item);
            }
        } else {
            System.out.println("商品不存在，无法添加到购物车");
        }

    }

    /**
     * 显示购物车内容
     * 从数据库查询购物车所有商品并打印输出
     */
    static void showCart() {
        List<Cart_item> cartItems = sqlSession.selectList("com.demo.mapper.Cart_itemMapper.selectAll");
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
