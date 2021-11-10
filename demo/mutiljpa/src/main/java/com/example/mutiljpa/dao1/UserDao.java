package com.example.mutiljpa.dao1;


import com.example.mutiljpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
}
