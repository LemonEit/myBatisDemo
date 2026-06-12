package com.demo.dao.impl;

import com.demo.dao.ProductDao;
import com.demo.domain.Product;
import com.demo.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 商品数据访问对象实现类
 * 实现商品的数据库操作方法
 */
public class ProductDaoImpl implements ProductDao {
    
    private SqlSession sqlSession;
    
    public ProductDaoImpl() {
        this.sqlSession = SqlSessionUtil.getSqlSession();
    }
    
    @Override
    public List<Product> selectAll() {
        return sqlSession.selectList("com.demo.mapper.ProductMapper.selectAll");
    }
    
    @Override
    public Product selectById(int id) {
        return sqlSession.selectOne("com.demo.mapper.ProductMapper.selectById", id);
    }
    
    @Override
    public int addProduct(Product product) {
        return sqlSession.insert("com.demo.mapper.ProductMapper.addProduct", product);
    }
}
