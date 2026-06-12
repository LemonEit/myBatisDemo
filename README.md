# MyBatis 京东商城项目

基于 MyBatis 框架开发的京东商城控制台应用程序，采用三层架构设计，实现了商品管理和购物车功能。

## 项目概述

这是一个使用 MyBatis 持久层框架开发的 Java 控制台应用，模拟京东商城的购物流程。项目采用标准的三层架构（Controller-Service-DAO），实现了商品的增删改查和购物车管理功能。

## 技术栈

- **Java**: JDK 17
- **持久层框架**: MyBatis 3.5.2
- **数据库**: MySQL 8.0
- **构建工具**: Maven
- **连接池**: HikariCP 4.0.3
- **日志**: Log4j 1.2.17
- **其他**: Lombok, PageHelper

## 项目结构

```
day01/
├── src/
│   ├── main/
│   │   ├── java/com/demo/
│   │   │   ├── controller/          # 表现层（Controller层）
│   │   │   │   ├── ProductController.java    # 商品控制器
│   │   │   │   └── CartController.java       # 购物车控制器
│   │   │   ├── service/             # 业务逻辑层（Service层）
│   │   │   │   ├── ProductService.java       # 商品服务接口
│   │   │   │   ├── CartService.java          # 购物车服务接口
│   │   │   │   └── impl/
│   │   │   │       ├── ProductServiceImpl.java  # 商品服务实现
│   │   │   │       └── CartServiceImpl.java     # 购物车服务实现
│   │   │   ├── dao/                 # 数据访问层（DAO层）
│   │   │   │   ├── ProductDao.java           # 商品DAO接口
│   │   │   │   ├── CartItemDao.java          # 购物车DAO接口
│   │   │   │   └── impl/
│   │   │   │       ├── ProductDaoImpl.java   # 商品DAO实现
│   │   │   │       └── CartItemDaoImpl.java  # 购物车DAO实现
│   │   │   ├── domain/              # 实体类
│   │   │   │   ├── Product.java              # 商品实体
│   │   │   │   └── Cart_item.java            # 购物车商品实体
│   │   │   ├── mapper/              # MyBatis映射接口
│   │   │   │   ├── ProductMapper.java        # 商品映射接口
│   │   │   │   └── Cart_itemMapper.java      # 购物车映射接口
│   │   │   ├── utils/               # 工具类
│   │   │   │   └── SqlSessionUtil.java       # SqlSession工具类
│   │   │   └── main.java            # 主程序入口
│   │   └── resources/
│   │       ├── mapper/              # MyBatis映射文件
│   │       │   ├── ProductMapper.xml         # 商品SQL映射
│   │       │   └── Cart_itemMapper.xml       # 购物车SQL映射
│   │       ├── mybatis.xml          # MyBatis配置文件
│   │       ├── db.properties        # 数据库配置
│   │       └── log4j.properties     # 日志配置
│   └── test/                        # 测试代码
├── pom.xml                          # Maven配置文件
└── README.md                        # 项目说明文档
```

## 功能特性

### 1. 商品管理
- **添加商品**: 输入商品名称、价格、颜色、尺码、库存信息
- **查看所有商品**: 显示商品列表
- **根据编号查找商品**: 按商品ID查询商品详情

### 2. 购物车管理
- **添加商品到购物车**: 选择商品和数量，自动计算小计
- **查看购物车**: 显示购物车中所有商品及总计
- **智能合并**: 相同商品自动合并数量

## 数据库设计

### product 表（商品表）
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | INT | 主键，自增 |
| name | VARCHAR | 商品名称 |
| price | DECIMAL | 商品价格 |
| color | VARCHAR | 商品颜色 |
| size | VARCHAR | 商品尺码 |
| stock | INT | 库存数量 |

### cart_item 表（购物车商品表）
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | INT | 主键（商品ID） |
| name | VARCHAR | 商品名称 |
| price | DECIMAL | 商品单价 |
| amount | INT | 购买数量 |
| subtotal | DECIMAL | 小计金额 |

## 运行环境要求

- JDK 17 或更高版本
- MySQL 8.0 数据库
- Maven 3.6 或更高版本

## 安装和运行

### 1. 数据库准备

创建数据库和表：

```sql
-- 创建数据库
CREATE DATABASE IF NOT EXISTS test DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE test;

-- 创建商品表
CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    color VARCHAR(50),
    size VARCHAR(20),
    stock INT DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建购物车商品表
CREATE TABLE cart_item (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    amount INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id) REFERENCES product(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### 2. 修改数据库配置

编辑 `src/main/resources/db.properties` 文件，修改数据库连接信息：

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false
jdbc.username=your_username
jdbc.password=your_password
```

### 3. 编译项目

```bash
mvn clean compile
```

### 4. 运行程序

```bash
mvn exec:java -Dexec.mainClass="com.demo.main"
```

或者使用IDE直接运行 `com.demo.main` 类。

## 使用说明

程序启动后，显示主菜单：

```
*****欢迎进入京东商城*****
	1.添加商品
	2.查看所有商品
	3.查找指定编号商品
	4.添加商品到购物车
	5.查看购物车
	6.退出
***************************
请选择菜单：
```

输入对应的数字选择功能：

1. **添加商品**: 按提示输入商品信息
2. **查看所有商品**: 显示所有商品列表
3. **查找指定编号商品**: 输入商品编号查询
4. **添加商品到购物车**: 输入商品编号和数量
5. **查看购物车**: 显示购物车内容
6. **退出**: 退出程序

## 三层架构说明

### Controller层（表现层）
- 负责接收用户输入
- 调用Service层处理业务逻辑
- 显示处理结果

### Service层（业务逻辑层）
- 实现核心业务逻辑
- 数据验证和业务规则处理
- 调用DAO层进行数据操作

### DAO层（数据访问层）
- 封装数据库操作
- 使用MyBatis进行ORM映射
- 提供数据持久化服务

## 开发规范

1. **命名规范**
   - 类名：大驼峰命名法（如：ProductController）
   - 方法名：小驼峰命名法（如：addProduct）
   - 常量：全大写下划线分隔（如：MAX_COUNT）

2. **注释规范**
   - 类和接口必须添加JavaDoc注释
   - 方法必须添加功能说明
   - 复杂业务逻辑添加实现说明

3. **代码分层**
   - 严格遵循三层架构
   - 避免跨层调用
   - 使用接口进行解耦

## 常见问题

### 1. 数据库连接失败
检查 `db.properties` 中的数据库配置是否正确，确保MySQL服务已启动。

### 2. 编译错误
确保JDK版本为17或更高，Maven依赖已正确下载。

### 3. 运行时异常
检查数据库表是否已创建，表结构是否与实体类匹配。

## 扩展功能

可以考虑添加以下功能：
- 商品分类管理
- 用户登录注册
- 订单管理
- 支付功能
- 商品搜索
- 分页显示

## 许可证

本项目仅供学习和参考使用。

## 联系方式

如有问题或建议，请通过以下方式联系：
- 提交Issue到项目仓库
- 发送邮件至项目维护者

---

**注意**: 这是一个学习项目，展示了MyBatis框架的基本使用和三层架构的设计思想。在实际生产环境中，还需要考虑安全性、性能优化、异常处理等方面。