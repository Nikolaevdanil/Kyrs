package com.example.demo.service;

import com.example.demo.models.Manufacture;
import com.example.demo.models.User;
import com.example.demo.models.Worker;
import com.example.demo.repozitory.UserRepozitory;
import com.example.demo.service.DTO.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Component
@Transactional
public class UserService implements UserServiceInterface, UserDetailsService {

    UserRepozitory userRepozitory;
    @Autowired
    public UserService(UserRepozitory userRepo){
        this.userRepozitory = userRepo;
    }

    @Override
    public void addUser(User user) {
        if(findUser(user.getLogin()) != null){
            throw new UsernameNotFoundException("Exist");
        }
        userRepozitory.save(user);
    }
    @Override
    public List<User> getAllUser() {
        List<User> users = userRepozitory.findAll();
        return users;
    }
    @Override
    public void deleteUserById(Long id) {
        userRepozitory.deleteById(id);
    }
    @Override
    public User findUser(String name) {
        return userRepozitory.findByLogin(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = findUser(name);
        if (user == null)
        {
            throw new UsernameNotFoundException("Not found");
        }
        return new UserDTO(user);
    }
    @Override
    public List<User> filterByName() {

        return userRepozitory.findByOrderByName();
    }
    @Override
    public List<User> filterBySurname() {

        return userRepozitory.findByOrderBySurname();
    }
    @Override
    public List<User> filterByWorId() {

        return userRepozitory.findByOrderById();
    }

    @Override
    public String printUser(List<User> list) {
        String buff = "<h>Users</h><br/>";
        int i = 0;
        for (User item: list) {
            buff += "<tr><td>"+item.getId()+" - " + item.getName() + "  " + item.getSurname() + "<br/></td></tr>";
            i++;
        }
        if (i == 0)
        {
            buff+= "<tr><td>User list is empty</td></tr>";
        }
        i = 0;
        return buff;
    }
}