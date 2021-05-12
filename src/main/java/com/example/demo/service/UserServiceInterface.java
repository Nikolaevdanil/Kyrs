package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.models.Worker;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserServiceInterface {
    void addUser(User user);
    List<User> getAllUser();
    void deleteUserById(Long id);
    User findUser(String name);
    UserDetails loadUserByUsername(String name);
    List<User> filterByName();
    List<User> filterBySurname();
    List<User> filterByWorId();
    String printUser(List<User> list);
}