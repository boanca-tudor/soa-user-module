package com.ubb.userModule.user.repo;

import com.ubb.userModule.user.entity.Role;
import com.ubb.userModule.user.role.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByAuthority(RoleType authority);
}
