package com.example.demo.repozitory;


import com.example.demo.models.User;
import com.example.demo.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepozitory extends JpaRepository<User, Long> {
    User findByLogin(String login);
    List<User> findByOrderByName();
    List<User> findByOrderBySurname();
    List<User> findByOrderById();
}
