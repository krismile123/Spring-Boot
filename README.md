<p align="center">
    <img src="./assets/logo.png">
</p>

<p align="center">
    <img src="https://img.shields.io/badge/Category-knowledge-red.svg">
    <img src="https://img.shields.io/static/v1?label=language&message=java&color=green">
    <img src="https://img.shields.io/static/v1?label=javaEE&message=spring boot&color=yellow">
</p>

---

**note目录下是spring boot学习笔记**

**demo目录下是spring boot的一些demo**

*大神github：https://github.com/lenve/javaboy-code-samples*

---

## 目录
- **note**
    - [spring boot入门](./note/spring_boot入门/spring_boot入门.md)
        - [spring boot简介](./note/spring_boot入门/spring_boot入门.md/##springboot简介)
        - [spring boot程序](./note/spring_boot入门/spring_boot入门.md/##springboot程序)
            - [创建maven工程](./note/spring_boot入门/spring_boot入门.md/###创建maven工程)
            - [项目构建](./note/spring_boot入门/spring_boot入门.md/###项目构建)
            - [项目启动](./note/spring_boot入门/spring_boot入门.md/###项目启动)
        - [简便创建spring boot](./note/spring_boot入门/spring_boot入门.md/##简便创建springboot)
            - [IntelliJ IDEA 创建](./note/spring_boot入门/spring_boot入门.md/###IntelliJIDEA创建)
    - [spring boot基础配置](./note/spring_boot基础配置/spring_boot基础配置.md)
        - [不使用spring-boot-starter-parent](./note/spring_boot基础配置/spring_boot基础配置.md/##不使用spring-boot-starter-parent)
        - [@spring bootapplication注解](./note/spring_boot基础配置/spring_boot基础配置.md/##@springbootapplication注解)
        - [定制banner](./note/spring_boot基础配置/spring_boot基础配置.md/##定制banner)
        - [web容器配置](./note/spring_boot基础配置/spring_boot基础配置.md/##web容器配置)
            - [tomcat配置](./note/spring_boot基础配置/spring_boot基础配置.md/###tomcat配置)
            - [jetty配置](./note/spring_boot基础配置/spring_boot基础配置.md/###jetty配置)
            - [Undertow配置](./note/spring_boot基础配置/spring_boot基础配置.md/###Undertow配置)
            - [properties配置](./note/spring_boot基础配置/spring_boot基础配置.md/###properties配置)
            - [类型安全配置属性](./note/spring_boot基础配置/spring_boot基础配置.md/###类型安全配置属性)
            - [YAML配置](./note/spring_boot基础配置/spring_boot基础配置.md/###YAML配置)
            - [复杂配置](./note/spring_boot基础配置/spring_boot基础配置.md/###复杂配置)
            - [profile配置](./note/spring_boot基础配置/spring_boot基础配置.md/###profile配置)
    - [spring boot整合视图层技术](./note/spring_boot整合视图层技术/spring_boot整合视图层技术.md)
        - [整合Thymeleaf](./note/spring_boot整合视图层技术/spring_boot整合视图层技术.md/##整合Thymeleaf)
        - [整合FreeMaker](./note/spring_boot整合视图层技术/spring_boot整合视图层技术.md/##整合FreeMaker)
    - [spring boot整合web开发](./note/spring_boot整合web开发/spring_boot整合web开发.md)
        - [返回json数据](./note/spring_boot整合web开发/spring_boot整合web开发.md/##返回json数据)
            - [默认实现](./note/spring_boot整合web开发/spring_boot整合web开发.md/###默认实现)
            - [自定义转换器](./note/spring_boot整合web开发/spring_boot整合web开发.md/###自定义转换器)
                - [Gson](./note/spring_boot整合web开发/spring_boot整合web开发.md/####Gson)
                - [fastjson](./note/spring_boot整合web开发/spring_boot整合web开发.md/####fastjson)
        - [静态资源访问](./note/spring_boot整合web开发/spring_boot整合web开发.md/##静态资源访问)
        - [文件上传](./note/spring_boot整合web开发/spring_boot整合web开发.md/##文件上传)
            - [单文件上传](./note/spring_boot整合web开发/spring_boot整合web开发.md/###单文件上传)
            - [多文件上传](./note/spring_boot整合web开发/spring_boot整合web开发.md/###多文件上传)
        - [@ControllorAdvice](./note/spring_boot整合web开发/spring_boot整合web开发.md/##@ControllorAdvice)
            - [全局异常处理](./note/spring_boot整合web开发/spring_boot整合web开发.md/###全局异常处理)
            - [添加全局数据](./note/spring_boot整合web开发/spring_boot整合web开发.md/###添加全局数据)
            - [请求参数预处理](./note/spring_boot整合web开发/spring_boot整合web开发.md/###请求参数预处理)
        - [自定义错误页](./note/spring_boot整合web开发/spring_boot整合web开发.md/##自定义错误页)
        - [cors支持](./note/spring_boot整合web开发/spring_boot整合web开发.md/##cors支持)
        - [配置类与xml配置](./note/spring_boot整合web开发/spring_boot整合web开发.md/##配置类与xml配置)
        - [注册拦截器](./note/spring_boot整合web开发/spring_boot整合web开发.md/##注册拦截器)
        - [启动系统任务](./note/spring_boot整合web开发/spring_boot整合web开发.md/##启动系统任务)
            - [CommandLineRunner](./note/spring_boot整合web开发/spring_boot整合web开发.md/###CommandLineRunner)
            - [ApplicationRunner](./note/spring_boot整合web开发/spring_boot整合web开发.md/###ApplicationRunner)
        - [整合Servlet、Filter、Listener](./note/spring_boot整合web开发/spring_boot整合web开发.md/##整合Servlet、Filter、Listener)
        - [路径映射](./note/spring_boot整合web开发/spring_boot整合web开发.md/##路径映射)
        - [配置AOP](./note/spring_boot整合web开发/spring_boot整合web开发.md/##配置AOP)
            - [AOP是什么](./note/spring_boot整合web开发/spring_boot整合web开发.md/###AOP是什么)
            - [AOP基本概念](./note/spring_boot整合web开发/spring_boot整合web开发.md/###AOP基本概念)
            - [springboot支持](./note/spring_boot整合web开发/spring_boot整合web开发.md/###springboot支持)
        - [其他](./note/spring_boot整合web开发/spring_boot整合web开发.md/##其他)
            - [自定义欢迎页](./note/spring_boot整合web开发/spring_boot整合web开发.md/###自定义欢迎页)
            - [自定义favion](./note/spring_boot整合web开发/spring_boot整合web开发.md/###自定义favion)
            - [除去某个自动配置](./note/spring_boot整合web开发/spring_boot整合web开发.md/###除去某个自动配置)
    - [spring boot整合持久层技术](./note/spring_boot整合持久层技术/spring_boot整合持久层技术.md)
        - [整合JdbcTemplate](./note/spring_boot整合持久层技术/spring_boot整合持久层技术.md/##整合JdbcTemplate)
        - [整合mybatis](./note/spring_boot整合持久层技术/spring_boot整合持久层技术.md/##整合mybatis)
        - [整合spring data JPA](./note/spring_boot整合持久层技术/spring_boot整合持久层技术.md/##整合springdataJPA)
        - [多数据源](./note/spring_boot整合持久层技术/spring_boot整合持久层技术.md/##多数据源)
            - [jdbctemplate多数据源整合](./note/spring_boot整合持久层技术/spring_boot整合持久层技术.md/###jdbctemplate多数据源整合)
            - [mybatis多数据源整合](./note/spring_boot整合持久层技术/spring_boot整合持久层技术.md/###mybatis多数据源整合)
            - [jpa多数据源整合](./note/spring_boot整合持久层技术/spring_boot整合持久层技术.md/###jpa多数据源整合)
    - [spring_boot整合NoSQL](./note/spring_boot整合NoSQL/spring_boot整合NoSQL.md)
        - [spring boot整合redis](./note/spring_boot整合NoSQL/spring_boot整合NoSQL.md/##springboot整合redis)
        - [spring boot整合MongoDB](./note/spring_boot整合NoSQL/spring_boot整合NoSQL.md/##springboot整合MongoDB)
    - [构建restful服务](./note/构建restful服务/构建restful服务.md)
        - [jpa实现rest](./note/构建restful服务/构建restful服务.md/#jpa实现rest)
        - [mongodb实现rest](./note/构建restful服务/构建restful服务.md/#mongodb实现rest)
    - [spring boot缓存](./note/spring_boot缓存/spring_boot缓存.md)
        - [Ehcache 2.x缓存](./note/spring_boot缓存/##Ehcache2.x缓存)
    - [安全管理](./note/安全管理/安全管理.md)
        - [spring boot整合shiro](./note/安全管理/##springboot整合shiro) 
- **demo**
    - [aop](./demo/aop)
    - [commandlinerunner](./demo/commandlinerunner)
    - [servlet_filter_listener](./demo/servlet_filter_listener)
    - [JdbcTemplate](./demo/JdbcTemplate)
    - [mybatis](./demo/mybatis)
    - [spring-data-JPA](./demo/spring-data-JPA)
    - [mutiljdbc](./demo/jdbctemplates)
    - [mutilmybatis](./demo/mutilbatis1)
    - [mutiljpa](./demo/mutiljpa)
    - [jpa-restful](./demo/restful)
    - [mongodb-restful](./demo/mongodb_restful)
    - [redis](./demo/redis)
    - [mongodb](./demo/mongodb)
    - [Ehcache](./demo/ehcache)
    - [shiro-basic](./demo/shiro)
    - [shiro-starter](./demo/shiro-starter)

---