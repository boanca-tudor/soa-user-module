package com.ubb.userModule.user.repo;

import com.ubb.userModule.user.entity.ApplicationUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByEmail(String email);

    @Query("select u from ApplicationUser u JOIN FETCH u.roles WHERE u.email = :email")
    Optional<ApplicationUser> findForReplicationByEmail(@Param("email") String email);
}
