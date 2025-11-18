package com.ubb.userModule.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubb.userModule.user.dto.RoleDto;
import com.ubb.userModule.user.entity.Role;
import com.ubb.userModule.user.repo.RoleRepository;
import com.ubb.userModule.user.role.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleCrudService {
    @Autowired
    protected RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role updateRole(RoleDto input) throws JsonProcessingException {
        Role role = Role.builder()
                .authority(input.getAuthority())
                .build();

        return roleRepository.save(role);
    }

    public void deleteRole(RoleType authority) {
        Optional<Role> role = roleRepository.findByAuthority(authority);
        role.ifPresent(value -> roleRepository.delete(value));
    }
}
