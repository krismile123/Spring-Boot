# Spring-Boot

**note目录下是spring boot学习笔记**

**demo目录下是spring boot的一些demo**

*大神github：https://github.com/lenve/javaboy-code-samples*

## 目录
- **note**
    - [spring boot入门](./note/spring_boot入门/spring_boot入门.md)
        - spring boot简介
        - spring boot程序
            - 创建maven工程
            - 项目构建
            - 项目启动
        - 简便创建spring boot
            - IntelliJ IDEA 创建
    - [spring boot基础配置](./note/spring_boot基础配置/spring_boot基础配置.md)
        - 不使用spring-boot-starter-parent
        - @spring bootapplication注解
        - 定制banner
        - web容器配置
            - tomcat配置
            - jetty配置
            - Undertow配置
            - properties配置
            - 类型安全配置属性
            - YAML配置
            - 复杂配置
            - profile配置
    - [spring boot整合视图层技术](./note/spring_boot整合视图层技术/spring_boot整合视图层技术.md)
        - 整合Thymeleaf
        - 整合FreeMaker
    - [spring boot整合web开发](./note/spring_boot整合web开发/spring_boot整合web开发.md)
        - 返回json数据
            - 默认实现
            - 自定义转换器
                - Gson
                - fastjson
        - 静态资源访问
        - 文件上传
            - 单文件上传
            - 多文件上传
        - @ControllorAdvice
            - 全局异常处理
            - 添加全局数据
            - 请求参数预处理
        - 自定义错误页
        - cors支持
        - 配置类与xml配置
        - 注册拦截器
        - 启动系统任务
            - CommandLineRunner
            - ApplicationRunner
        - 整合Servlet、Filter、Listener
        - 路径映射
        - 配置AOP
            - AOP是什么
            - AOP基本概念
            - spring boot支持
        - 其他
            - 自定义欢迎页
            - 自定义favion
            - 除去某个自动配置
    - [spring boot整合持久层技术](./note/spring_boot整合持久层技术/spring_boot整合持久层技术.md)
        - 整合JdbcTemplate
        - 整合mybatis
        - 整合spring data JPA
        - 多数据源
            - jdbctemplate多数据源整合
            - mybatis多数据源整合
            - jpa多数据源整合
    - [构建restful服务](./note/构建restful服务/构建restful服务.md)
        - jpa实现rest
        - mongodb实现rest
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