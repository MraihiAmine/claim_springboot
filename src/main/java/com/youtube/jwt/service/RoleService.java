package com.youtube.jwt.service;

import com.youtube.jwt.dao.RoleDao;
import com.youtube.jwt.entity.Role;
import com.youtube.jwt.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> createNewRole(Role role) {
        roleDao.save(role);
        Iterable<Role> rolesIterable = roleDao.findAll();
        return StreamSupport.stream(rolesIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Role> getRoles() {
        Iterable<Role> rolesIterable = roleDao.findAll();
        return StreamSupport.stream(rolesIterable.spliterator(), false)
                .collect(Collectors.toList());

    }

    public Iterable<Role> updateRole(Role role) {
        Optional<Role> roleTmp = roleDao.findById(role.getRoleName());
        if (roleTmp.isPresent()) {
            Role existingRole = roleTmp.get();
            existingRole.setRoleDescription(role.getRoleDescription());

            roleDao.save(existingRole);
            return roleDao.findAll();
        } else {

            return null;
        }
    }
}
