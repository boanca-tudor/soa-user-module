package com.ubb.userModule.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ubb.userModule.user.dto.UserDataDto;
import com.ubb.userModule.user.entity.ApplicationUser;
import com.ubb.userModule.user.entity.Role;
import com.ubb.userModule.user.repo.ApplicationUserRepository;
import com.ubb.userModule.user.repo.RoleRepository;
import com.ubb.userModule.user.role.RoleType;
import com.ubb.userModule.util.FormatUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCrudService {
    @Autowired
    protected ApplicationUserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    public ApplicationUser updateUser(UserDataDto input) throws JsonProcessingException {
        Optional<ApplicationUser> existingUser = userRepository.findForReplicationByEmail(input.getEmail());
        ApplicationUser user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
            if (!FormatUtils.isNullOrEmpty(input.getEmail())) {
                user.setEmail(input.getEmail());
            }
            if (!FormatUtils.isNullOrEmpty(input.getFullName())) {
                user.setFullName(input.getFullName());
            }
            if (!FormatUtils.isNullOrEmpty(input.getPassword())) {
                user.setPassword(passwordEncoder.encode(input.getPassword()));
            }
        } else {
            user = ApplicationUser.builder()
                    .fullName(input.getFullName())
                    .email(input.getEmail())
                    .password(passwordEncoder.encode(input.getPassword()))
                    .build();
        }

        return userRepository.save(user);
    }

    @Transactional
    public ApplicationUser addRoleToUser(String email, RoleType roleType) throws JsonProcessingException {
        ApplicationUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        Role role = roleRepository.findByAuthority(roleType)
                .orElseThrow(() -> new RuntimeException("Role not found with type: " + roleType));

        user.getRoles().add(role);
        role.getUsers().add(user);

        return userRepository.save(user);
    }
}
