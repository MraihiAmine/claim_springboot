package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Role;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.service.RoleService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping({ "/addRole" })
    public List<Role> createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }

    @PutMapping("/updateRole")
    public List<Role> updateRole(@RequestBody Role role) {
        Iterable<Role> updatedRoles = roleService.updateRole(role);
        List<Role> roleList = new ArrayList<>();
        updatedRoles.forEach(roleList::add);
        return roleList;
    }

    @GetMapping({ "/roles" })
    public List<Role> getRoles() {
        return roleService.getRoles();
    }
}
