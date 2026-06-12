package com.demo.dao.impl;

import com.demo.dao.CartItemDao;
import com.demo.domain.Cart_item;
import com.demo.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 购物车商品数据访问对象实现类
 * 实现购物车商品的数据库操作方法
 */
public class CartItemDaoImpl implements CartItemDao {
    
    private SqlSession sqlSession;
    
    public CartItemDaoImpl() {
        this.sqlSession = SqlSessionUtil.getSqlSession();
    }
    
    @Override
    public void addCart_item(Cart_item cart_item) {
        sqlSession.insert("com.demo.mapper.Cart_itemMapper.addCart_item", cart_item);
    }
    
    @Override
    public Cart_item selectByProductId(int product_id) {
        return sqlSession.selectOne("com.demo.mapper.Cart_itemMapper.selectByProductId", product_id);
    }
    
    @Override
    public void updateCart_item(Cart_item cart_item) {
        sqlSession.update("com.demo.mapper.Cart_itemMapper.updateCart_item", cart_item);
    }
    
    @Override
    public List<Cart_item> selectAll() {
        return sqlSession.selectList("com.demo.mapper.Cart_itemMapper.selectAll");
    }
}
