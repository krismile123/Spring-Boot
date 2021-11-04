[toc]
---
# spring boot整合持久层技术

持久层是javaEE中访问数据库的核心操作，spring boot中对常见的持久层框架都提供了自动化配置。

## 整合JdbcTemplate

JdbcTemplate是spring提供的一套JDBC框架，利用AOP技术来解决直接使用JDBC时大量重复代码的问题，JdbcTemplate没有mybatis灵活，但是比较方便。spring boot中对JdbcTemplate的使用提供了自动化配置类JdbcTemplateAutoConfiguration

具体操作步骤：

**1.创建数据库和表**

创建JdbcTemplate数据库，创建JdbcTemplate，添加字段

![](img/2.png)

```
不要忘记给主键id设置自增，否则会抛出异常，
```

**创建项目，添加依赖**

创建spring boot项目，添加如下依赖：
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid</artifactId>
			<version>1.1.9</version>
		</dependency>
```

spring-boot-starter-jdbc提供了spring-jdbc，另外还加入了数据库驱动依赖和数据库连接池依赖

**3.数据库配置**

在application.properties中配置数据库的基本连接信息

```
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/JdbcTemplate
spring.datasource.username=root
spring.datasource.password=root
```

**4.创建实体类**
创建book实体类：
```java
    private Integer id;
    private String name;
    private String author;
    //省略getter/setter

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
```

**5.创建数据库访问层**

创建BookDao：
```java
@Repository
public class BookDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public int addBook(Book book) {
        return jdbcTemplate.update("INSERT INTO book(name,author) VALUES (?,?)",
                book.getName(), book.getAuthor());
    }
    public int updateBook(Book book) {
        return jdbcTemplate.update("UPDATE book SET name=?,author=? WHERE id=?",
                book.getName(), book.getAuthor(), book.getId());
    }
    public int deleteBookById(Integer id) {
        return jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
    public Book getBookById(Integer id) {
        return jdbcTemplate.queryForObject("select * from book where id=?",
                new BeanPropertyRowMapper<>(Book.class), id);
    }
    public List<Book> getAllBooks() {
        return jdbcTemplate.query("select * from book",
                new BeanPropertyRowMapper<>(Book.class));
    }
}
```

- 创建BookDao，注入JdbcTemplate，已经添加了spring-jdbc的相关依赖，所以直接注入JdbcTemplate使用
- 在JdbcTemplate中，增删改三中类型的操作主要是用update和batchUpdate方法来完成，query和queryForObject方法主要用来完成查询功能，另外还有execute可以用来执行任意的SQL，call方法用来调用存储过程。
- 在执行查询操作时，需要有一个RowMapper将查询出来的列和实体类中的属性一一对应起来，如果列名和属性名是相同的，那么可以直接使用BeanPropertyRowMapper；反之，则需要自己定义RowMapper接口，来将列和实体类属性一一对应起来。

**6.创建Service和Controller**
```java
// service.java
@Service
public class BookService {
    @Autowired
    BookDao bookDao;
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }
    public int deleteBookById(Integer id) {
        return bookDao.deleteBookById(id);
    }
    public Book getBookById(Integer id) {
        return bookDao.getBookById(id);
    }
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }
}
```
```java
// bookcontroller.java
@RestController
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping("/book")
    public void bookOps() {
        Book b1 = new Book();
        b1.setId(99);
        b1.setName("西厢记");
        b1.setAuthor("王实甫");
        int i = bookService.addBook(b1);
        System.out.println("addBook>>>" + i);
        Book b2 = new Book();
        b2.setId(1);
        b2.setName("朝花夕拾");
        b2.setAuthor("鲁迅");
        int updateBook = bookService.updateBook(b2);
        System.out.println("updateBook>>>"+updateBook);
        Book b3 = bookService.getBookById(1);
        System.out.println("getBookById>>>"+b3);
        int delete = bookService.deleteBookById(2);
        System.out.println("deleteBookById>>>"+delete);
        List<Book> allBooks = bookService.getAllBooks();
        System.out.println("getAllBooks>>>"+allBooks);
    }
}
```
最后在浏览器中访问http://localhost:8080/book，观察控制台输出。

![](img/3.png)

表中的数据也被更新了

![](img/4.png)

## 整合mybatis

mybatis是一款优秀的持久层框架，mybatis提供了一套开箱即用的自动化配置方案，可以做到mybatis开箱即用。

**1.创建项目**

创建spring boot项目，添加mybatis依赖、数据库驱动依赖，以及数据库连接池依赖。
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.2</version>
		</dependency>
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid</artifactId>
			<version>1.1.9</version>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
```

**2.创建数据库、表、实体类**

![](img/5.png)

```java
// Book.java
public class Book {
    private Integer id;
    private String name;
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
```

application.properties
```xml
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/mybatis
spring.datasource.username=root
spring.datasource.password=root
```

**3.创建数据库访问层**

创建BookMapper：
```java
@Mapper
public interface BookMapper {
    int addBook(Book book);
    int deleteBookById(Integer id);
    int updateBookById(Book book);
    Book getBookById(Integer id);
    List<Book> getAllBooks();
}
```

- @Mapper注解指明类，或者在配置类上添加@MapperScan("com.exsample.exsample.mapper")注解，表示扫描com.exsample.exsample.mapper包下的所有接口作为Mapper

**4.创建BookMapper.xml**

在mapper包中创建BookMapper.xml文件
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.mybatis.mapper.BookMapper">
    <insert id="addBook" parameterType="com.example.mybatis.model.Book">
        INSERT INTO mybatis(name,author) VALUES (#{name},#{author})
    </insert>
    <delete id="deleteBookById" parameterType="int">
        DELETE FROM mybatis WHERE id=#{id}
    </delete>
    <update id="updateBookById" parameterType="com.example.mybatis.model.Book">
        UPDATE mybatis set name=#{name},author=#{author} WHERE id=#{id}
    </update>
    <select id="getBookById" parameterType="int" resultType="org.sang.model.Book">
        SELECT * FROM mybatis WHERE id=#{id}
    </select>
    <select id="getAllBooks" resultType="com.example.mybatis.model.Book">
        SELECT * FROM mybatis
    </select>
</mapper>
```

- 针对BookMapper接口中的每一个方法都在BookMapper.xml中列出了实现
- #{}用来替代接口中的参数

**5.创建service、controller**
```java
// BookService.java
@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }
    public int updateBook(Book book) {
        return bookMapper.updateBookById(book);
    }
    public int deleteBookById(Integer id) {
        return bookMapper.deleteBookById(id);
    }
    public Book getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }
}
```
```java
// BookController.java
@RestController
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping("/book")
    public void bookOps() {
        Book b1 = new Book();
        b1.setName("西厢记");
        b1.setAuthor("王实甫");
        int i = bookService.addBook(b1);
        System.out.println("addBook>>>" + i);
        Book b2 = new Book();
        b2.setId(1);
        b2.setName("朝花夕拾");
        b2.setAuthor("鲁迅");
        int updateBook = bookService.updateBook(b2);
        System.out.println("updateBook>>>"+updateBook);
        Book b3 = bookService.getBookById(1);
        System.out.println("getBookById>>>"+b3);
        int delete = bookService.deleteBookById(2);
        System.out.println("deleteBookById>>>"+delete);
        List<Book> allBooks = bookService.getAllBooks();
        System.out.println("getAllBooks>>>"+allBooks);
    }
}
```
**6.配置pom.xml**
```xml
		<resources>
			<resource>
				<directory>src/main/java</directory>
				<includes>
					<include>**/*.xml</include>
				</includes>
			</resource>
			<resource>
				<directory>src/main/resources</directory>
			</resource>
		</resources>
```

最后在浏览器中访问http://localhost:8080/book，观察控制台输出。

![](img/6.png)

