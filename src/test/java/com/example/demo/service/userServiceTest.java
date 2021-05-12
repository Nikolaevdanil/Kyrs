package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.repozitory.UserRepozitory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class userServiceTest {
    @Mock
    private UserRepozitory userRepozitory;
    @Captor
    ArgumentCaptor<User> captor;
    @Test
    void getUsers() {
        User user = new User();
        user.setLogin("Vasya");
        User user2 = new User();
        user2.setLogin("Dima");
        Mockito.when(userRepozitory.findAll()).thenReturn(List.of(user, user2));
        assertEquals("Vasya", userRepozitory.findAll().get(0).getLogin());
        assertEquals("Dima", userRepozitory.findAll().get(1).getLogin());
    }
    @Test
    void userExists() {
        Long longInt = new Long(1);
        User user = new User();
        user.setLogin("Vasya");
        user.setId(longInt);
        user.setPassword("vasya2!");
        UserService us = new UserService(userRepozitory);
        Mockito.when(userRepozitory.findByLogin("Vasya")).thenReturn(user);
        Assertions.assertEquals("Vasya", us.loadUserByUsername("Vasya").getUsername());
    }
}