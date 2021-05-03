package com.example.demo.repozitory;


import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepozitory extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
