package com.ubb.userModule.user.dto;

import com.ubb.userModule.user.role.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleToUserDto {
    protected String email;

    protected RoleType authority;
}
