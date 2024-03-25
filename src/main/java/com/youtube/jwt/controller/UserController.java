package com.youtube.jwt.controller;

import com.youtube.jwt.entity.User;
import com.youtube.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({ "/registerNewUser" })
    public List<User> registerNewUser(@RequestBody User user) {

        Iterable<User> users = userService.registerNewUser(user);
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        return userList;
    }

    @PutMapping("/updateUser")
    public List<User> updateUser(@RequestBody User user) {
        Iterable<User> updatedUsers = userService.updateUser(user);
        List<User> userList = new ArrayList<>();
        updatedUsers.forEach(userList::add);
        return userList;
    }

    @GetMapping({ "/forAdmin" })
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "This URL is only accessible to the admin";
    }

    @GetMapping({ "/forUser" })
    @PreAuthorize("hasRole('User')")
    public String forUser() {
        return "This URL is only accessible to the user";
    }

    @GetMapping({ "/users" })
    public List<User> getUsers() {
        return this.userService.getAllUsers();
    }
}
