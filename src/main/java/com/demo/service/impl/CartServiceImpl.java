package com.demo.service.impl;

import com.demo.dao.CartItemDao;
import com.demo.dao.ProductDao;
import com.demo.dao.impl.CartItemDaoImpl;
import com.demo.dao.impl.ProductDaoImpl;
import com.demo.domain.Cart_item;
import com.demo.domain.Product;
import com.demo.service.CartService;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车业务逻辑实现类
 * 实现购物车相关的业务方法
 */
public class CartServiceImpl implements CartService {
    
    private CartItemDao cartItemDao;
    private ProductDao productDao;
    
    public CartServiceImpl() {
        this.cartItemDao = new CartItemDaoImpl();
        this.productDao = new ProductDaoImpl();
    }
    
    @Override
    public boolean addToCart(int productId, int quantity) {
        // 验证商品是否存在
        Product product = productDao.selectById(productId);
        if (product == null) {
            return false;
        }
        
        // 检查购物车中是否已存在该商品
        Cart_item existingItem = cartItemDao.selectByProductId(productId);
        
        if (existingItem == null) {
            // 购物车中不存在该商品，创建新的购物车商品
            Cart_item newItem = new Cart_item();
            newItem.setId(product.getId());
            newItem.setName(product.getName());
            newItem.setPrice(product.getPrice());
            newItem.setAmount(quantity);
            newItem.setSubtotal(product.getPrice().multiply(new BigDecimal(quantity)));
            
            cartItemDao.addCart_item(newItem);
        } else {
            // 购物车中已存在该商品，更新数量和小计
            existingItem.setAmount(existingItem.getAmount() + quantity);
            existingItem.setSubtotal(existingItem.getPrice().multiply(new BigDecimal(existingItem.getAmount())));
            
            cartItemDao.updateCart_item(existingItem);
        }
        
        return true;
    }
    
    @Override
    public List<Cart_item> getCartItems() {
        return cartItemDao.selectAll();
    }
    
    @Override
    public Cart_item getCartItemByProductId(int productId) {
        return cartItemDao.selectByProductId(productId);
    }
    
    @Override
    public boolean updateCartItem(Cart_item cartItem) {
        if (cartItem == null) {
            return false;
        }
        
        cartItemDao.updateCart_item(cartItem);
        return true;
    }
}
