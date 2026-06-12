package com.demo.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * MyBatis SqlSession 工具类
 * 封装 SqlSessionFactory 的创建和 SqlSession 的获取，避免重复代码
 */
public class SqlSessionUtil {

    private static final SqlSessionFactory sqlSessionFactory;

    // 静态代码块：类加载时初始化 SqlSessionFactory（全局唯一）
    static {
        try {
            String resource = "mybatis.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("初始化 SqlSessionFactory 失败", e);
        }
    }

    /**
     * 获取 SqlSession（自动提交事务）
     *
     * @return SqlSession 对象
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
}