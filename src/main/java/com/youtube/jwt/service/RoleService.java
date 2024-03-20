package com.youtube.jwt.service;

import com.youtube.jwt.dao.RoleDao;
import com.youtube.jwt.entity.Role;
import com.youtube.jwt.entity.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }

    public List<Role> getRoles() {
        Iterable<Role> rolesIterable = roleDao.findAll();
        return StreamSupport.stream(rolesIterable.spliterator(), false)
                .collect(Collectors.toList());

    }
}
