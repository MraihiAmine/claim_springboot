package com.youtube.jwt.service;

import com.youtube.jwt.dao.RoleDao;
import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.entity.Role;
import com.youtube.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setEmail("chiraz@mail.com");
        adminUser.setActive(1L);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);
    }

    public List<User> getAllUsers() {
        Iterable<User> usersIterable = userDao.findAll();
        return StreamSupport.stream(usersIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Iterable<User> registerNewUser(User user) {
        user.setRole(user.getRole());
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        userDao.save(user);
        return userDao.findAll();
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Iterable<User> updateUser(User user) {
        Optional<User> userTmp = userDao.findById(user.getUserName());
        if (userTmp.isPresent()) {
            User existingUser = userTmp.get();
            existingUser.setRole(user.getRole());
            existingUser.setActive(user.getActive());
            existingUser.setEmail(user.getEmail());
            existingUser.setUserFirstName(user.getUserFirstName());
            existingUser.setUserLastName((user.getUserLastName()));
            existingUser.setUserName(user.getUserName());

            userDao.save(existingUser);
            return userDao.findAll();
        } else {

            return null;
        }
    }
}
