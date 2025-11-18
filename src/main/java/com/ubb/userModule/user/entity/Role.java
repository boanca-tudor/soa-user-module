package com.ubb.userModule.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ubb.userModule.user.role.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    protected RoleType authority;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    protected Set<ApplicationUser> users;

    @Override
    public String getAuthority() {
        return authority.name();
    }

    public RoleType getAuthorityAsType() {
        return authority;
    }
}
