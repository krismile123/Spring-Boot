package com.example.mutiljpa.controller;

import com.example.mutiljpa.dao1.UserDao;
import com.example.mutiljpa.dao2.UserDao2;
import com.example.mutiljpa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;
    @Autowired
    UserDao2 userDao2;
    @GetMapping("/test1")
    public void test1() {
        User u1 = new User();
        u1.setAge(55);
        u1.setName("鲁迅");
        u1.setGender("男");
        userDao.save(u1);
        User u2 = new User();
        u2.setAge(80);
        u2.setName("泰戈尔");
        u2.setGender("男");
        userDao2.save(u2);
    }
}
