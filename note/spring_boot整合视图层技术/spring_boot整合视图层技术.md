[toc]
---
# spring boot 整合视图层技术
## 整合Thymeleaf
Thymeleaf是新一代java引擎。Thymeleaf支持HTML原型，spring boot提供了Thymeleaf自动化配置解决方案，spring boot整合Thymeleaf主要通过如下步骤：
**1.创建工程，添加依赖**
创建一个spring boot工程，然后添加spring-boot-starter-web依赖和spring-boot-starter-thymeleaf依赖：
```java
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
**配置thymeleaf**
spring为thymeleaf提供了自动化配置类ThymeleafAutoConfiguration
如果想对默认的Thymeleaf配置参数进行自定义配置，可以直接在application.properties中进行配置，部分常见配置如下：
```
#是否开启缓存，开发时可设置为false，默认为true
spring.thymeleaf.cache=true
#是否检查模板是否存在，默认为true
spring.thymeleaf.check-template=true
#是否检查模板位置是否存在，默认为true
spring.thymeleaf.check-template-location=true
#模板文件编码
spring.thymeleaf.encoding=UTF-8
#模板文件位置
spring.thymeleaf.prefix=classpath:/templates/
#Content-Type配置
spring.thymeleaf.servlet.content-type=text/html
#模板文件后缀
spring.thymeleaf.suffix=.html
```
**3.配置控制器**
创建Book实例，然后再controller中返回ModelAndView，代码如下：
```java
# book
public class Book {
    private Integer id;
    private String name;
    private String author;
    //省略getter/setter

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
# Controller
@Controller
public class BookController {
    @GetMapping("/books")
    public ModelAndView books() {
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        b1.setId(1);
        b1.setAuthor("罗贯中");
        b1.setName("三国演义");
        Book b2 = new Book();
        b2.setId(2);
        b2.setAuthor("曹雪芹");
        b2.setName("红楼梦");
        books.add(b1);
        books.add(b2);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }
}
```
Book实体类，承载返回的数据
controller中开始是构建返回数据，然后创建返回ModelAndView，视图名为books，返回数据为所创建的List集合

**4.创建视图**
在resource目录下的templetes中创建book.html，代码如下：
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>图书列表</title>
</head>
<body>
<table border="1">
    <tr>
        <td>图书编号</td>
        <td>图书名称</td>
        <td>图书作者</td>
    </tr>
    <tr th:each="book:${books}">
        <td th:text="${book.id}"></td>
        <td th:text="${book.name}"></td>
        <td th:text="${book.author}"></td>
    </tr>
</table>
</body>
</html>
```
在开头导入thymeleaf，然后是纯纯html，显示数据
th:each中通过th:each进行集合遍历，通过th:text展示数据。
**5.运行**
浏览器http://localhost:8080/books
关于thymeleaf的更多资料：
- https://www.thymeleaf.com

## 整合FreeMaker
FreeMaker不仅可以用来配置HTML页面模板，也可以作为电子邮件模板、配置文件模板以及源码模板，spring boot对FreeMaker也做了很好的支持，具体步骤如下：
**创建项目，添加依赖**
```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
**配置freemaker**
```xml
#HttpServletRequest的属性是否可以覆盖controller中model的同名项
spring.freemarker.allow-request-override=false
#HttpSession的属性是否可以覆盖controller中model的同名项
spring.freemarker.allow-session-override=true
#是否开启缓存
spring.freemarker.cache=false
#模板文件编码
spring.freemarker.charset=UTF-8
#是否检查模板位置
spring.freemarker.check-template-location=true
#Content-Type的值
spring.freemarker.content-type=text/html
#是否将HttpServletRequest中的属性添加到Model中
spring.freemarker.expose-request-attributes=false
#是否将HttpSession中的属性添加到Model中
spring.freemarker.expose-session-attributes=true
#模板文件后缀
spring.freemarker.suffix=.ftl
#模板文件位置
spring.freemarker.template-loader-path=classpath:/templates/
```
**配置控制器**
```java
@SpringBootApplication
public class FreemarkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreemarkerApplication.class, args);
	}
}
```
