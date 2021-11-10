package com.example.mutiljpa.dao2;

import com.example.mutiljpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao2 extends JpaRepository<User,Integer> {
}
