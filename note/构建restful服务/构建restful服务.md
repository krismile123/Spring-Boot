[toc]
---
# 构建restful服务

REST是一种web软件架构风格，匹配或兼容这种架构风格的网络服务称为REST服务，spring boot提供了自动化配置方案

**1.创建项目**

首先创建一个 Spring Boot 工程，引入 Web 、 Jpa 、 MySQL 、 RestRepositories 依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-rest</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
```

**2.配置数据库、jpa**

```properties
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/jparestful
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database=mysql
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
spring.jpa.show-sql=true
```

- 前面五行配置了数据库的基本信息，包括数据库连接池、数据库用户名、数据库密码、数据库连接地址以及数据库驱动名称。

- 接下来的五行配置了 JPA 的基本信息，分别表示生成 SQL 的方言、打印出生成的 SQL 、每次启动项目时根据实际情况选择是否更新表、数据库平台是 MySQL。

**3.构建实体类**

```java
@Entity(name = "t_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
```
```java
public interface BookRepository extends JpaRepository<Book, Integer> {
}
```

- 这里一个是配置了一个实体类 Book，另一个则是配置了一个 BookRepository ，项目启动成功后，框架会根据 Book 类的定义，在数据库中自动创建相应的表，BookRepository 接口则是继承自 JpaRepository ，JpaRepository 中自带了一些基本的增删改查方法。

**4.测试**

启动项目，使用浏览器插件postman测试：

向数据库中添加一条数据：

![](img/1.png)

查询测试：

更换请求为GET，http://localhost:8080/books/1

![](img/2.png)

## 自定义请求路径

通常情况下，请求路径都是实体类名小写加s，如果想对请求路径进行重定义，通过@RepositoryRestResource注解实现。

```java
@RepositoryRestResource(path = "bs",collectionResourceRel = "bs",itemResourceRel = "b")
```
- path表示将所有请求路径中的books都改为bs，如：http://localhost:/bs
- collectionResourceRel表示返回的json集合中的book集合的key值修改为bs
- itemResourceRel表示将返回json集合中的单个book的key修改为b

## 自定义查询方法

默认的排序方法支持分页查询、排序查询以及按id查询，如果想要按照某个属性进行查询，只需在BookRepository中定义相关方法并暴露出去即可。

```java
@RestResource(path = "author",rel = "author")
    List<Book> findByAuthorContains(@Param("author") String author);
    @RestResource(path = "name",rel = "name")
    Book findByNameEquals(@Param("name") String name);
```

- 自定义查询只需要在BookRepository定义相关查询方法即可，方法定义好之后可以不添加@RestResource注解，默认路径名就是方法名，如上，如果不添加@RestResource注解，则默认该方法的调用路径为：http://localhost:8080/bs/search/findByAuthorContains?author=鲁迅，若果要自定义查询路径，只需要添加@RestResource注解即可，path表示属性最新的路径，以`@RestResource(path = "author",rel = "author")`为例，添加该注解后的查询路径为：http://localhost:8080/bs/search/author?author=鲁迅
- 用户可以直接访问http://localhost:8080/bs/search查看该实体类暴露出了那些查询方法

## 隐藏方法

默认情况下，凡是继承了Repository接口的类都会暴露出来，如果继承了Repository接口又不想暴露相关操作，组如下配置即可：

```java
@RepositoryRestRecourse(exported=false,)
```

将@RepositoryRestRecourse中的属性exported设置为FALSE后，所有的接口方法会失效，若只是不想暴露某个特定的方法，例如想屏蔽DELETE接口：

```java
    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);
```

- @RestResource的exported属性默认为TRUE，上设置为FALSE即可

## 配置CORS

CORS的两种配置方法：

- 直接在方法上使用@CorssOrigin注解
- 全局配置
默认的restful工程不需要自己提供controller，因此添加在controller上的注解可以添加到BookRepository上

```java
@CrossOrigin
@RepositoryRestResource(path = "bs",collectionResourceRel = "bs",itemResourceRel = "b")
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @RestResource(path = "author",rel = "author")
    List<Book> findByAuthorContains(@Param("author") String author);
    @RestResource(path = "name",rel = "name")
    Book findByNameEquals(@Param("name") String name);
}
```

这时，BookRepository中的所有方法都支持跨域，如果只想让某一个方法跨域，那么只需要将@CrossOrigin直接添加到这个方法上即可

## 其他配置

也可以在application.properties中配置一些常用属性

```properties
##每页默认记录数，缺省值为20
#spring.data.rest.default-page-size=2
##分页查询页码参数名，缺省值为page
#spring.data.rest.page-param-name=page
##分页查询记录数参数名，缺省值为size
#spring.data.rest.limit-param-name=size
##分页查询排序参数名，缺省值为sort
#spring.data.rest.sort-param-name=sort
##base-path表示给所有请求路径都加上前缀
#spring.data.rest.base-path=/api
##添加成功时是否返回添加内容
#spring.data.rest.return-body-on-create=true
##更新成功时是否返回更新内容
#spring.data.rest.return-body-on-update=true
```

这些xml配置也可以在java代码中进行配置，并且代码中配置的优先级高于application.properties

```java
@Configuration
public class RestConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setDefaultPageSize(2)
                .setPageParamName("page")
                .setLimitParamName("size")
                .setSortParamName("sort")
                .setBasePath("/api")
                .setReturnBodyOnCreate(true)
                .setReturnBodyOnUpdate(true);
    }
}
```

# MongoDB实现rest

**1.创建项目**

创建spring boot项目添加如下依赖：

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```

**2.创建实体类**

```java
public class Book {
    private Integer id;
    private String name;
    private String author;
}
```

**3.创建BookRepository**

```java
public interface BookRepository extends MongoRepository<Book,Integer> {
}
```

项目启动成功后，测试过程和jpa一致