import com.demo.domain.Cart_item;
import com.demo.domain.Product;
import com.demo.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * MyBatis测试类
 * 用于测试商品和购物车的CRUD操作
 */
public class demo_test {
  /* 自动加载SqlSession sqlSession = SqlSessionUtil.getSqlSession();*/
  SqlSession sqlSession = SqlSessionUtil.getSqlSession();
  Scanner scr = new Scanner(System.in);

  /**
   * 测试查询所有商品
   */
  @Test
  public void test1() {
    /*测试查询所有商品*/
    List<Product> products = sqlSession.selectList("com.demo.mapper.ProductMapper.selectAll");

    for (Product product : products) {
      System.out.println(product);
    }

  }

  /**
   * 测试根据ID查询商品
   */
  @Test
  public void test2() {
    /*测试查询Id为1的商品*/
    Product product = sqlSession.selectOne("com.demo.mapper.ProductMapper.selectById", 1);
    System.out.println(product);
  }

  /**
   * 测试添加商品
   */
  @Test
  public void test3() {
    Product product = new Product();
    product.setName("测试商品");
    product.setPrice(new BigDecimal(100.0));
    product.setColor("红色");
    product.setSize("M");
    product.setStock(10);
    int result = sqlSession.insert("com.demo.mapper.ProductMapper.addProduct", product);
    System.out.println("添加成功，商品信息为：" + product);
  }

  /**
   * 测试购物车添加商品
   */
  @Test
  public void test4() {
    /**
     * 测试购物车添加商品
     */
    Cart_item product1 = new Cart_item();

    /**
     * 假设要加入购物车的商品编号为1，数量为2
     */
    int id0 = 1, number0 = 2;

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
   * 测试展示购物车
   */
  @Test
  public void test5() {
    /**
     * 测试展示购物车
     */
    List<Cart_item> cart_items = sqlSession.selectList("com.demo.mapper.Cart_itemMapper.selectAll");
    for (Cart_item cart_item : cart_items) {
      System.out.println(cart_item);
    }
  }

}
