# spring boot整合NoSQL

## spring boot整合redis

redis是一个key-value存储系统,支持存储的value类型相对更多，包括string(字符串)、list(链表)、set(集合)、zset(sorted set --有序集合)和hash（哈希类型）。这些数据类型都支持push/pop、add/remove及取交集并集和差集及更丰富的操作，而且这些操作都是原子性的。

在 Spring Boot 中，默认集成的 Redis 就是 Spring Data Redis，默认底层的连接池使用了 lettuce ，Spring Data Redis 针对 Redis 提供了非常方便的操作模板 RedisTemplate 。

spring boot整合redis步骤如下：

**1.创建spring boot项目**

创建spring boot项目，添加如下依赖：

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
```

主要就是引入了 Spring Data Redis + 连接池

**2.配置redis**

```properties
spring.redis.database=0
spring.redis.password=
spring.redis.port=6379
spring.redis.host=192.168.152.154
spring.redis.lettuce.pool.min-idle=5
spring.redis.lettuce.pool.max-idle=10
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-wait=1ms
spring.redis.lettuce.shutdown-timeout=100ms
```

到这里自动化配置已经生效

自动化配置中提供了两个bean，RedisTemplate 和 StringRedisTemplate ，其中 StringRedisTemplate 是 RedisTemplate 的子类，两个的方法基本一致，不同之处主要体现在操作的数据类型不同，RedisTemplate 中的两个泛型都是 Object ，意味者存储的 key 和 value 都可以是一个对象，而 StringRedisTemplate 的 两个泛型都是 String ，意味者 StringRedisTemplate 的 key 和 value 都只能是字符串。

**controller**

```java
@RestController
public class BookController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @GetMapping("/test1")
    public void test1(){
//        StringRedisTemplate中的两个泛型都是String,意味着存储的key和value只能是String
//        ValueOperations<String,String> ops1 = stringRedisTemplate.opsForValue();
//        ops1.set("name","三国演义");
//        String name = ops1.get("name");
//        System.out.println(name);

//        redisTemplatel中的两个泛型都是object，意味着存储的key和value都可以是一个对象
        ValueOperations ops2 = redisTemplate.opsForValue();
        Book b1 = new Book();
        b1.setId(1);
        b1.setName("红楼梦");
        b1.setAuthor("曹雪芹");
        ops2.set("b1", b1);
        Book book = (Book) ops2.get("b1");
        System.out.println(book);
    }
}
```

要缓存的 Java 对象必须实现 Serializable 接口，因为 Spring 会将对象先序列化再存入 Redis,所以我这里的一个解决办法是将实体类继承Serializable

```java
public class Book implements Serializable {
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

**测试**

启动项目，访问http://localhost:8080/test1

![](img/1.png)